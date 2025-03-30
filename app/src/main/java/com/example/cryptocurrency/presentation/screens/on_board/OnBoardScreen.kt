package com.example.cryptocurrency.presentation.screens.on_board

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.R
import com.example.cryptocurrency.common.Constants.ON_BOARDING_PAGE_COUNT
import com.example.cryptocurrency.domain.model.OnBoardingPage
import com.example.cryptocurrency.navigation.Screen
import com.example.cryptocurrency.presentation.screens.on_board.components.OnBoardViewModel
import com.example.cryptocurrency.presentation.ui.theme.ON_BOARD_CONTENT_PADDING
import com.example.cryptocurrency.presentation.ui.theme.SMALL_PADDING

@Composable
fun OnBoardScreen(
    navController: NavController,
    welcomeViewModel: OnBoardViewModel = hiltViewModel()
) {
    val pages = remember {
        listOf(
            OnBoardingPage.First,
            OnBoardingPage.Second,
            OnBoardingPage.Third
        )
    }
    val pagerState = rememberPagerState(pageCount = { ON_BOARDING_PAGE_COUNT })
    val isCompletedSaved = welcomeViewModel.isCompletedSaved

    LaunchedEffect(isCompletedSaved) {
        if(isCompletedSaved) {
            navController.navigate(Screen.AuthScreen.route) {
                popUpTo(Screen.BoardScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState
        ) { pageId ->
            PagerScreen(onBoardingPage = pages[pageId])
        }
        HorizontalPagerIndicator(pagerState = pagerState)
        OnBoardFinishButton(pagerState = pagerState) {
            welcomeViewModel.saveOnBoardingState(completed = true)
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(0.6f),
            painter = painterResource(onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image),
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            text = onBoardingPage.title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = ON_BOARD_CONTENT_PADDING)
        )
    }
}