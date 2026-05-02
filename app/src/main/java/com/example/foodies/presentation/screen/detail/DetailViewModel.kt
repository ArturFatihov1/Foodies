package com.example.foodies.presentation.screen.detail

import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetDetailProductUseCase
import com.example.foodies.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getDetailProductUseCase: GetDetailProductUseCase,
) : BaseViewModel<DetailState, DetailIntent, DetailLabel>(
    DetailState()
) {

    override fun onIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadProduct -> loadDetail(intent.id)
            DetailIntent.OnBackClick -> navigateToBack()
        }
    }

    private fun loadDetail(id: Long) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    product = null,
                    isLoading = true,
                    messageError = null
                )
            }
            try {
                val product = getDetailProductUseCase.getProductById(id)
                _state.update { it.copy(isLoading = false, product = product) }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, messageError = e.toString())
                }
            }
        }
    }

    private fun navigateToBack() {
        viewModelScope.launch {
            postLabel(DetailLabel.NavigateToBack)
        }
    }
}