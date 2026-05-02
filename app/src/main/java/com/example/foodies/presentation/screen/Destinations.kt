package com.example.foodies.presentation.screen

import kotlinx.serialization.Serializable

@Serializable
object ProductList

@Serializable
data class Detail(val productId: Long)

@Serializable
object Cart

