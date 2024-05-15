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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amadiyawa.feature_auth.R
import com.amadiyawa.feature_base.common.res.Dimen
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleLarge
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    onSignIn: () -> Unit
) {
    val viewModel: AuthViewModel = koinViewModel()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        SetupContent(
            paddingValues = paddingValues,
            onSignIn = onSignIn,
            viewModel = viewModel
        )
    }
}

@Composable
private fun SetupContent(
    paddingValues: PaddingValues,
    onSignIn: () -> Unit,
    viewModel: AuthViewModel
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ){
        HandleUiState(
            onSignIn = onSignIn,
            viewModel = viewModel
        )
    }
}

@Composable
private fun HandleUiState(
    onSignIn: () -> Unit,
    viewModel: AuthViewModel
) {
    val emailSignIn by viewModel.emailSignIn.collectAsStateWithLifecycle()
    val passwordSignIn by viewModel.passwordSignIn.collectAsStateWithLifecycle()

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
                    value = emailSignIn,
                    onValueChange = {  },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = Dimen.Size.extraLarge),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
                )

                OutlinedTextField(
                    value = passwordSignIn,
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
                    enabled = emailSignIn.isNotBlank() && passwordSignIn.isNotBlank(),
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
