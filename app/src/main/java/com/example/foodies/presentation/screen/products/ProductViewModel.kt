package com.example.foodies.presentation.screen.products

import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.GetCategoriesProductUseCase
import com.example.foodies.domain.SearchProductUseCase
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
) : BaseViewModel<ProductsState, ProductsIntent, ProductsLabel>(
    ProductsState()
) {
    init {
        load()
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