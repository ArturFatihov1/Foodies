package com.example.foodies.presentation.screen.products.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodies.R
import com.example.foodies.domain.entities.Product
import com.example.foodies.presentation.screen.products.ProductsIntent
import com.example.foodies.presentation.screen.products.ProductsState

@Composable
fun ProductContent(
    state: ProductsState,
    onIntent: (ProductsIntent) -> Unit,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Header(state, onIntent)

            CategoryTabs(state = state, onIntent = onIntent)

            Box(modifier = Modifier.fillMaxSize()) {
                if (state.products.isEmpty() && !state.loading) {
                    Text(
                        text = "Ничего не найдено",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                } else {
                    ProductGrid(
                        state = state.products,
                        onIntent = onIntent,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

        }
    }
}

@Composable
fun Header(
    state: ProductsState,
    onIntent: (ProductsIntent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (state.header)
            SearchHeader(state, onIntent)
        else {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Image(
                painter = painterResource(R.drawable.products_logo_ic),
                contentDescription = "Foodies Logo",
            )

            IconButton(onClick = { onIntent(ProductsIntent.ChangeHeader(true)) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }
}

@Composable
fun ProductGrid(
    state: List<Product>,
    onIntent: (ProductsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(state) { product ->
            ProductCard(
                product = product,
                onIntent = onIntent,
            )
        }
    }
}