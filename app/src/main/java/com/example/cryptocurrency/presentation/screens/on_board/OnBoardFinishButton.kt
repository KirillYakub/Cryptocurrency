package com.example.cryptocurrency.presentation.screens.on_board

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cryptocurrency.common.Constants.ON_BOARDING_PAGE_COUNT
import com.example.cryptocurrency.presentation.ui.theme.EXTRA_LARGE_PADDING
import com.example.cryptocurrency.presentation.ui.theme.ON_BOARD_CONTENT_PADDING

@Composable
fun ColumnScope.OnBoardFinishButton(
    pagerState: PagerState,
    onCLick: () -> Unit
) {
    Row(
        modifier = Modifier
            .weight(2f)
            .padding(horizontal = EXTRA_LARGE_PADDING)
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == ON_BOARDING_PAGE_COUNT - 1
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ON_BOARD_CONTENT_PADDING),
                onClick = onCLick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}