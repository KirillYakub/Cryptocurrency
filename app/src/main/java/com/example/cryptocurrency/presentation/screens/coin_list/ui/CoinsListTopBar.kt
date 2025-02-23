package com.example.cryptocurrency.presentation.screens.coin_list.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.cryptocurrency.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsListTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.crypto_coins),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}