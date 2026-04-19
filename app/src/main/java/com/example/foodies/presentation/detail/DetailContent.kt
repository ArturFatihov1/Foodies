package com.example.foodies.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodies.domain.entities.Product

@Composable
fun DetailContent(
    product: Product,
    onBackClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Surface(
                color = Color.Companion.White,
                tonalElevation = 8.dp,
                modifier = Modifier.Companion.windowInsetsPadding(WindowInsets.Companion.navigationBars)
            ) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF15412))
                ) {
                    Text(
                        text = "В корзину за ${product.discountPrice} ₽",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Companion.White
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                AsyncImage(
                    model = product.images.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .aspectRatio(1.2f),
                    contentScale = ContentScale.Companion.Crop
                )
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.Companion
                        .padding(16.dp)
                        .shadow(2.dp, CircleShape)
                        .background(Color.Companion.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.Companion.Black
                    )
                }
            }

            Column(modifier = Modifier.Companion.padding(16.dp)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Companion.Bold
                )

                Spacer(modifier = Modifier.Companion.height(8.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Companion.Gray
                )

                Spacer(modifier = Modifier.Companion.height(24.dp))


                // Таблица характеристик
                DetailRow("Вес", "${product.weight} г")
                Text(text = "Размеры", fontWeight = FontWeight.Companion.Bold)

                HorizontalDivider(color = Color.Companion.LightGray.copy(alpha = 0.5f))
                DetailRow("Ширина", product.dimensions.width.toString())
                HorizontalDivider(color = Color.Companion.LightGray.copy(alpha = 0.5f))
                DetailRow("Высота", product.dimensions.height.toString())
                HorizontalDivider(color = Color.Companion.LightGray.copy(alpha = 0.5f))
                DetailRow("Глубина", product.dimensions.depth.toString())
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}