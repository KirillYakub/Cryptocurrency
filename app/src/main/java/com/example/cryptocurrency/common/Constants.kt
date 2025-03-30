package com.example.cryptocurrency.common

object Constants {
    const val CRYPTO_DATASTORE_PREFERENCES = "cryptocurrency_preferences"
    const val PREFERENCES_COMPLETED_KEY = "on_boarding_completed_key"
    const val PREFERENCES_INPUT_WITHOUT_SIGN_IN_KEY = "on_input_without_sign_in_key"

    const val SECONDARY_BUTTONS_DEFAULT_ALPHA = 0.7f

    const val ON_BOARDING_PAGE_COUNT = 3

    const val PASSWORD_MIN_LENGTH = 8
    const val PASSWORD_MAX_LENGTH = 16

    const val BASE_URL = "https://api.coinpaprika.com/"
    const val PARAM_COIN_ID = "coinId"

    const val CONNECTION_TIME_OUT_IN_SECONDS = 10L
    const val READ_TIME_OUT_IN_SECONDS = 10L
}