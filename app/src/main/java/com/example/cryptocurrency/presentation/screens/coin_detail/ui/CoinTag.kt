package com.example.cryptocurrency.presentation.screens.coin_detail.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptocurrency.presentation.ui.theme.COIN_TAG_CONTAINER_CORNER_SHAPE
import com.example.cryptocurrency.presentation.ui.theme.SMALL_PADDING

@Composable
fun CoinTag(tag: String) {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(COIN_TAG_CONTAINER_CORNER_SHAPE)
            )
            .padding(SMALL_PADDING)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}