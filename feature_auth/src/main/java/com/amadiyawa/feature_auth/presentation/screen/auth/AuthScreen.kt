package com.amadiyawa.feature_auth.presentation.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.amadiyawa.feature_auth.R
import com.amadiyawa.feature_base.common.res.Dimen
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleLarge

@Composable
fun AuthScreen(
    onSignIn: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        SetupContent(
            paddingValues = paddingValues,
            onSignIn = onSignIn
        )
    }
}

@Composable
private fun SetupContent(
    paddingValues: PaddingValues,
    onSignIn: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ){
        HandleUiState(onSignIn = onSignIn)
    }
}

@Composable
private fun HandleUiState(
    onSignIn: () -> Unit
) {
    val email = "amadiyawa"
    val password = "amadiyawa@yahoo.fr"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.Padding.screenContent)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(Dimen.Padding.screenContent)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimen.Padding.screenContent)
            ) {
                TextTitleLarge(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.auth_title)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {  },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = Dimen.Size.extraLarge),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {  },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = Dimen.Size.extraLarge),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
                )

                Button(
                    onClick = { onSignIn() },
                    enabled = email.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.Size.extraLarge)
                ) {
                    Text("Sign In")
                }
            }
        }
    }
}
