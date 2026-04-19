package com.example.foodies.navigation

sealed class Screen(
    val route: String
) {
    object ProductScreen : Screen(ROUTE_PRODUCT)
    object DetailScreen : Screen("$ROUTE_DETAIL_PRODUCT/{$KEY_PRODUCT_ID}") {
        fun getRouteWithArgs(productId: Long): String {
            return "$ROUTE_DETAIL_PRODUCT/$productId"
        }
    }

    companion object {
        const val ROUTE_PRODUCT = "product_list"
        const val ROUTE_DETAIL_PRODUCT = "detail_product"
        const val KEY_PRODUCT_ID = "product_id"
    }
}