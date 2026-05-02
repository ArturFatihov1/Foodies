package com.example.foodies.presentation.screen.cart

import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.feature.cart.DecrementCartUseCase
import com.example.foodies.domain.feature.cart.GetAllCartUseCase
import com.example.foodies.domain.feature.cart.IncrementCartUseCase
import com.example.foodies.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val getAllCartUseCase: GetAllCartUseCase,
    private val incrementUseCase: IncrementCartUseCase,
    private val decrementUseCase: DecrementCartUseCase
) : BaseViewModel<CartState, CartIntent, CartLabel>(
    initialState = CartState()
) {
    init {
        observeCart()
    }

    override fun onIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.DecrementQuantity -> decrement(intent.product.id)
            is CartIntent.IncrementQuantity -> increment(intent.product.id)
            CartIntent.NavigateToBack -> navigateToBack()
        }
    }

    private fun observeCart() {
        viewModelScope.launch {
            getAllCartUseCase.getAllCart().collect { list ->
                val products = _state.value.productList
                val totalSum = list.sumOf {
                    it.count * (products.find { p -> p.id == it.id }?.discountPrice ?: 0.0)
                }
                _state.update {
                    it.copy(
                        productList = list,
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

    private fun navigateToBack() {
        viewModelScope.launch {
            postLabel(CartLabel.NavigateToBack)
        }
    }
}