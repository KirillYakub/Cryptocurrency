package com.example.cryptocurrency.presentation.screens.coin_detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.presentation.screens.coin_detail.components.CoinDetailViewModel
import com.example.cryptocurrency.presentation.common.EmptyScreen
import com.example.cryptocurrency.presentation.ui.theme.EXTRA_LARGE_PADDING
import com.example.cryptocurrency.presentation.ui.theme.LARGE_PADDING
import com.example.cryptocurrency.presentation.ui.theme.MEDIUM_PADDING
import com.example.cryptocurrency.presentation.ui.theme.SMALL_PADDING

@Composable
fun CoinDetailScreen(viewModel: CoinDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    strokeWidth = 5.dp
                )
            }
            state.error != null -> {
                EmptyScreen(
                    message = state.error,
                    onRefresh = viewModel::onCoinDetailLoadOrRefresh
                )
            }
            else -> {
                state.coin?.let { coin ->
                    CoinDetails(coin = coin)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetails(coin: CoinDetail) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(EXTRA_LARGE_PADDING)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(7f)
                )
                Text(
                    text = stringResource(if(coin.isActive) R.string.active_coin else R.string.inactive_coin),
                    color = if(coin.isActive) MaterialTheme.colorScheme.primary else Color.Red,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(3f)
                        .align(CenterVertically)
                )
            }
            if(coin.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                Text(
                    text = coin.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if(coin.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(LARGE_PADDING))
                Text(
                    text = stringResource(R.string.coin_tags),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(LARGE_PADDING))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 3,
                    horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                    verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
                ) {
                    coin.tags.forEach { tag ->
                        CoinTag(tag = tag)
                    }
                }
            }
            Spacer(modifier = Modifier.height(LARGE_PADDING))
            Text(
                text = stringResource(if(coin.team.isEmpty()) R.string.team_members_not_provided else R.string.team_members),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
        }
        items(coin.team) { teamMember ->
            TeamMembers(teamMember = teamMember)
            HorizontalDivider()
        }
    }
}