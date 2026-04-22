package com.example.foodies.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetDetailProductUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getDetailProductUseCase: GetDetailProductUseCase,
) : ViewModel() {

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState = _detailState.asStateFlow()

    fun loadDetail(id: Long) {
        _detailState.value = DetailState.Loading
        viewModelScope.launch {
            try {
                val product = getDetailProductUseCase.getProductById(id)
                delay(1500)
                _detailState.value = DetailState.Success(product)
            } catch (e: Exception) {
                _detailState.value = DetailState.Error(message = e.toString())
            }
        }
    }
}