package com.example.cryptocurrency.presentation.screens.coin_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.presentation.ui.theme.EXTRA_LARGE_PADDING

@Composable
fun CoinsListItem(
    coin: Coin,
    onCoinClick: (Coin) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCoinClick(coin) }
            .padding(EXTRA_LARGE_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(7f)
        )
        Text(
            text = stringResource(if(coin.isActive) R.string.active_coin else R.string.inactive_coin),
            color = if(coin.isActive) MaterialTheme.colorScheme.primary else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(3f)
                .align(CenterVertically)
        )
    }
}