package com.example.foodies.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.SearchProductUseCase
import com.example.foodies.presentation.screen.products.ProductState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProductUseCase: SearchProductUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _productState = MutableStateFlow<ProductState>(ProductState.Idle)
    val productState = _productState.asStateFlow()

    init {
        observeSearchQuery()
    }

    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
        if (newQuery.isBlank()) {
            _productState.value = ProductState.Idle
        }
    }

    fun searchProduct(query: String) {
        _productState.value = ProductState.Loading
        viewModelScope.launch {
            try {
                val data = searchProductUseCase.searchProduct(query)
                if (data.isEmpty())
                    _productState.value = ProductState.Error(message = "Ничего не найдено")
                else
                    _productState.value = ProductState.Success(data)
            } catch (e: Exception) {
                _productState.value = ProductState.Error(message = e.toString())
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collect { query ->
                    searchProduct(query)
                }
        }
    }
}