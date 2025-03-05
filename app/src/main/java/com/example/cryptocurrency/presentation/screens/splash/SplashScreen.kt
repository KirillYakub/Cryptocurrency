package com.example.cryptocurrency.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.R
import com.example.cryptocurrency.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val degrees = remember { Animatable(0f) }
    val isOnBoardingCompleted by splashViewModel.isOnBoardingCompleted.collectAsState()

    LaunchedEffect(Unit) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        val routeToNav =
            if(isOnBoardingCompleted) Screen.AuthScreen.route else Screen.BoardScreen.route
        navController.navigate(routeToNav) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }

    SplashIcon(degrees = degrees.value)
}

@Composable
fun SplashIcon(degrees: Float) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.rotate(degrees),
            painter = painterResource(R.drawable.splash_coin),
            contentDescription = stringResource(R.string.splash_icon)
        )
    }
}