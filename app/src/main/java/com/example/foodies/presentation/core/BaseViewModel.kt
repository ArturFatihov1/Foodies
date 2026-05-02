package com.example.foodies.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S, I, L>(
    initialState: S
) : ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _label = Channel<L>()
    val label = _label.receiveAsFlow()

    abstract fun onIntent(intent: I)

    protected suspend fun postLabel(label: L) {
        _label.send(label)
    }
}