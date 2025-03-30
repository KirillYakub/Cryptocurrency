package com.example.cryptocurrency.presentation.screens.auth

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.R
import com.example.cryptocurrency.common.Constants.SECONDARY_BUTTONS_DEFAULT_ALPHA
import com.example.cryptocurrency.domain.model.AuthType
import com.example.cryptocurrency.navigation.Screen
import com.example.cryptocurrency.presentation.screens.auth.components.AuthViewModel
import com.example.cryptocurrency.presentation.screens.auth.components.form.AuthFormEvent
import com.example.cryptocurrency.presentation.ui.theme.AUTH_CONTENT_PADDING
import com.example.cryptocurrency.presentation.ui.theme.EXTRA_LARGE_PADDING
import com.example.cryptocurrency.presentation.ui.theme.EXTRA_SMALL_PADDING
import com.example.cryptocurrency.presentation.ui.theme.MEDIUM_PADDING
import com.example.cryptocurrency.presentation.ui.theme.SMALL_PADDING

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState = viewModel.authResponseState
    val authFormState = viewModel.authFormState

    LaunchedEffect(authState.isSuccess, authFormState.isAnonymousInput) {
        if(authState.isSuccess || authFormState.isAnonymousInput) {
            navController.navigate(Screen.CoinsListScreen.route) {
                popUpTo(Screen.AuthScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (authState.isLoading || authState.isSuccess) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                strokeWidth = 5.dp
            )
        }
        else {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.onPrimaryContainer,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.auth_coin),
                            contentDescription = stringResource(R.string.auth_coin)
                        )
                        Spacer(modifier = Modifier.width(SMALL_PADDING))
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = MEDIUM_PADDING)
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(
                            if (authFormState.authType == AuthType.SIGN_UP) R.string.sign_up
                            else R.string.sign_in
                        ),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(EXTRA_LARGE_PADDING))
                    InputField(
                        text = authFormState.email,
                        label = stringResource(R.string.email),
                        message = authFormState.emailError,
                        onInputChange = { email ->
                            viewModel.onEvent(AuthFormEvent.EmailChanged(email = email))
                        },
                        onInputClear = {
                            viewModel.onEvent(AuthFormEvent.OnEmailClear)
                        }
                    )
                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                    InputField(
                        text = authFormState.password,
                        label = stringResource(R.string.password),
                        message = authFormState.passwordError,
                        onInputChange = { password ->
                            viewModel.onEvent(AuthFormEvent.PasswordChange(password = password))
                        },
                        onInputClear = {
                            viewModel.onEvent(AuthFormEvent.OnPasswordClear)
                        }
                    )
                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                    AuthButton(
                        text = stringResource(
                            if (authFormState.authType == AuthType.SIGN_UP) R.string.sign_up
                            else R.string.sign_in
                        ),
                        onClick = {
                            viewModel.onEvent(AuthFormEvent.Submit)
                        }
                    )
                    Spacer(modifier = Modifier.height(EXTRA_LARGE_PADDING))
                    Row {
                        Text(
                            text = stringResource(
                                if (authFormState.authType == AuthType.SIGN_UP) R.string.already_have_an_account
                                else R.string.create_new_account
                            )
                        )
                        Spacer(modifier = Modifier.width(EXTRA_SMALL_PADDING))
                        Text(
                            text = stringResource(
                                if (authFormState.authType == AuthType.SIGN_UP) R.string.sign_in
                                else R.string.sign_up
                            ),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(
                                    event = AuthFormEvent.AuthTypeChanged(
                                        authType = if (authFormState.authType == AuthType.SIGN_UP) AuthType.SIGN_IN else AuthType.SIGN_UP
                                    )
                                )
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                    Text(
                        text = stringResource(R.string.use_app_anonymously),
                        modifier = Modifier
                            .alpha(SECONDARY_BUTTONS_DEFAULT_ALPHA)
                            .clickable {
                                viewModel.onEvent(AuthFormEvent.AnonymousInput(input = true))
                            }
                    )
                    if(authState.error != null) {
                        Spacer(modifier = Modifier.height(EXTRA_LARGE_PADDING))
                        Text(
                            text = authState.error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}