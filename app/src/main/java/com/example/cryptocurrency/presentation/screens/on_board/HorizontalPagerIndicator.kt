package com.example.cryptocurrency.presentation.screens.on_board

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.cryptocurrency.R
import com.example.cryptocurrency.presentation.ui.theme.INDICATOR_SELECTED_SIZE
import com.example.cryptocurrency.presentation.ui.theme.INDICATOR_UNSELECTED_SIZE
import com.example.cryptocurrency.presentation.ui.theme.SMALL_PADDING

@Composable
fun ColumnScope.HorizontalPagerIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        repeat(pagerState.pageCount) { pageId ->
            val indicatorSize by animateDpAsState(
                targetValue = if (pageId == pagerState.currentPage) INDICATOR_SELECTED_SIZE else INDICATOR_UNSELECTED_SIZE,
                label = stringResource(R.string.pager_indicator_anim)
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(indicatorSize)
                    .background(
                        if (pageId == pagerState.currentPage) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
            )
        }
    }
}