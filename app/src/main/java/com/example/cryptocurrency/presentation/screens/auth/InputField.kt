package com.example.cryptocurrency.presentation.screens.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.cryptocurrency.R
import com.example.cryptocurrency.presentation.ui.theme.AUTH_CONTENT_PADDING
import com.example.cryptocurrency.presentation.ui.theme.EXTRA_SMALL_PADDING

@Composable
fun InputField(
    text: String,
    label: String,
    message: String?,
    onInputChange: (String) -> Unit,
    onInputClear: () -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onInputChange(it) },
        singleLine = true,
        label = {
            Text(text = label)
        },
        trailingIcon = {
            IconButton(onClick = onInputClear) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.clear_field)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AUTH_CONTENT_PADDING)
    )
    if(message != null) {
        Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
        Text(
            text = message,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AUTH_CONTENT_PADDING)
        )
    }
}