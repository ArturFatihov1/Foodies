package com.example.foodies.presentation.screen.products

import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.GetCategoriesProductUseCase
import com.example.foodies.domain.SearchProductUseCase
import com.example.foodies.domain.entities.Product
import com.example.foodies.domain.feature.cart.AddToCartUseCase
import com.example.foodies.domain.feature.cart.DecrementCartUseCase
import com.example.foodies.domain.feature.cart.GetAllCartUseCase
import com.example.foodies.domain.feature.cart.IncrementCartUseCase
import com.example.foodies.presentation.core.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getCategoriesProductUseCase: GetCategoriesProductUseCase,
    private val searchProductUseCase: SearchProductUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getAllCartUseCase: GetAllCartUseCase,
    private val incrementUseCase: IncrementCartUseCase,
    private val decrementUseCase: DecrementCartUseCase
) : BaseViewModel<ProductsState, ProductsIntent, ProductsLabel>(
    ProductsState()
) {
    init {
        load()
        observeCart()
        loadNameCategory()
        observeSearchQuery()
    }

    override fun onIntent(intent: ProductsIntent) {
        when (intent) {
            ProductsIntent.LoadProducts -> load()
            is ProductsIntent.OnProductClick -> navigateToDetail(intent)
            is ProductsIntent.SelectedCategory -> loadCategoryProducts(intent.category)
            is ProductsIntent.ChangeHeader -> changeHeader(intent.choice)
            is ProductsIntent.OnQueryChanged -> onQueryChanged(intent.query)
            is ProductsIntent.AddToCartProduct -> addToCart(intent.product)
            is ProductsIntent.IncrementQuantity -> increment(intent.product.id)
            is ProductsIntent.DecrementQuantity -> decrement(intent.product.id)
            ProductsIntent.NavigateToCart -> navigateToCart()
        }
    }

    private fun navigateToCart() {
        viewModelScope.launch {
            postLabel(ProductsLabel.NavigateToCart)
        }
    }

    private fun changeHeader(choice: Boolean) {
        _state.update { it.copy(header = choice) }
    }

    private fun load() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            try {
                delay(2000)
                val data = getAllProductsUseCase.getAllProducts()
                _state.update { it.copy(loading = false, products = data) }
            } catch (e: Exception) {
                _state.update { it.copy(loading = false, showError = true, error = e.toString()) }
            }
        }
    }


    private fun addToCart(product: Product) {
        viewModelScope.launch {
            addToCartUseCase.addToCart(product)
        }
    }

    private fun observeCart() {
        viewModelScope.launch {
            getAllCartUseCase.getAllCart().collect { cartItems ->
                val products = _state.value.products
                val updateProducts = products.map { product ->
                    val cartItem = cartItems.find { it.id == product.id }

                    product.copy(
                        count = cartItem?.count ?: 0
                    )
                }
                val totalSum = cartItems.sumOf {
                    it.count * (products.find { p -> p.id == it.id }?.discountPrice ?: 0.0)
                }

                _state.update {
                    it.copy(
                        products = updateProducts,
                        totalSum = totalSum
                    )
                }
            }
        }
    }

    private fun increment(id: Long) {
        viewModelScope.launch {
            incrementUseCase.execute(id)
        }
    }

    private fun decrement(id: Long) {
        viewModelScope.launch {
            decrementUseCase.execute(id)
        }
    }

    private fun navigateToDetail(intent: ProductsIntent.OnProductClick) {
        viewModelScope.launch {
            postLabel(label = ProductsLabel.NavigateToDetail(intent.id))
        }
    }

    private fun loadNameCategory() {
        viewModelScope.launch {
            try {
                val data = getCategoriesProductUseCase.getNameCategories()
                _state.update { it.copy(categories = data) }
            } catch (e: Exception) {
                _state.update { it.copy(showError = true, error = e.toString()) }
            }
        }
    }

    private fun loadCategoryProducts(category: String) {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, selectedCategory = category) }
            try {
                val data = getCategoriesProductUseCase.getProductCategories(category)
                _state.update { it.copy(loading = false, products = data) }
            } catch (e: Exception) {
                _state.update { it.copy(loading = false, showError = true, error = e.toString()) }
            }
        }
    }

    private fun onQueryChanged(newQuery: String) {
        _state.update { it.copy(query = newQuery) }
    }

    private fun searchProduct(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            try {
                val data = searchProductUseCase.searchProduct(query)
                if (data.isEmpty())
                    _state.update {
                        it.copy(
                            products = emptyList(),
                            loading = false,
                            error = "Ничего не найдено"
                        )
                    }
                else
                    _state.update { it.copy(loading = false, products = data) }
            } catch (e: Exception) {
                _state.update { it.copy(loading = false, error = e.toString()) }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            state
                .map { it.query }
                .distinctUntilChanged()
                .debounce(500)
                .collect { query ->
                    if (query.isBlank()) {
                        load()
                    } else {
                        searchProduct(query)
                    }
                }
        }
    }
}