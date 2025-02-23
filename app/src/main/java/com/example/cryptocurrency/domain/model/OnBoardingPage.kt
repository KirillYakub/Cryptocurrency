package com.example.cryptocurrency.domain.model

import androidx.annotation.DrawableRes
import com.example.cryptocurrency.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnBoardingPage(
        image = R.drawable.crypto_coin,
        title = "Greetings",
        description = "Are you interested in the crypto industry? If so, then you have come to the right place!"
    )
    data object Second : OnBoardingPage(
        image = R.drawable.crypto_integration,
        title = "Explore",
        description = "Learn how different cryptocurrencies integrated into computer network."
    )
    data object Third : OnBoardingPage(
        image = R.drawable.crypto_dev,
        title = "People",
        description = "Find out who was directly involved in the coins creation."
    )
}