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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amadiyawa.feature_auth.R
import com.amadiyawa.feature_base.common.enum.TextFieldType
import com.amadiyawa.feature_base.common.res.Dimen
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleLarge
import kotlinx.coroutines.flow.StateFlow
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
    val isSignIn = viewModel.isSignIn.collectAsStateWithLifecycle()

    val emailSignIn = viewModel.emailSignIn.collectAsStateWithLifecycle()
    val passwordSignIn = viewModel.passwordSignIn.collectAsStateWithLifecycle()
    val emailSignUp = viewModel.emailSignUp.collectAsStateWithLifecycle()
    val passwordSignUp = viewModel.passwordSignUp.collectAsStateWithLifecycle()

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

                AuthTextField(
                    params = AuthTextFieldParams(
                        isSignIn = isSignIn.value,
                        signInTextField = emailSignIn.value,
                        signUpTextField = emailSignUp.value,
                        textFieldTypeSignIn = TextFieldType.EMAIL_SIGN_IN,
                        textFieldTypeSignUp = TextFieldType.EMAIL_SIGN_UP,
                        label = "Email",
                        viewModel = viewModel,
                        errorMessage = getErrorMessage(
                            if (isSignIn.value) TextFieldType.EMAIL_SIGN_IN else TextFieldType.EMAIL_SIGN_UP,
                            viewModel
                        )
                    )
                )

                AuthTextField(
                    params = AuthTextFieldParams(
                        isSignIn = isSignIn.value,
                        signInTextField = passwordSignIn.value,
                        signUpTextField = passwordSignUp.value,
                        textFieldTypeSignIn = TextFieldType.PASSWORD_SIGN_IN,
                        textFieldTypeSignUp = TextFieldType.PASSWORD_SIGN_UP,
                        label = "Password",
                        viewModel = viewModel,
                        visualTransformation = PasswordVisualTransformation(),
                        errorMessage = getErrorMessage(
                            if (isSignIn.value) TextFieldType.PASSWORD_SIGN_IN else TextFieldType.PASSWORD_SIGN_UP,
                            viewModel
                        )
                    )
                )

                Button(
                    onClick = {
                        viewModel.signInOrSignUp()
                        onSignIn()
                    },
                    enabled = viewModel.isButtonEnabled(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.Size.extraLarge)
                ) {
                    Text(text = getButtonText(isSignIn.value))
                }
            }
        }
    }
}

@Composable
private fun AuthTextField(
    params: AuthTextFieldParams
) {
    val isError = handleTextFieldIsError(
        textFieldType = if (params.isSignIn) params.textFieldTypeSignIn
        else params.textFieldTypeSignUp,
        viewModel = params.viewModel
    )

    OutlinedTextField(
        value = getTextFieldValue(
            isSignIn = params.isSignIn,
            signInTextField = params.signInTextField,
            signUpTextField = params.signUpTextField
        ),
        onValueChange = {
            handleOnValueChange(
                textFieldType = if (params.isSignIn) params.textFieldTypeSignIn
                else params.textFieldTypeSignUp,
                viewModel = params.viewModel,
                value = it
            )
        },
        label = { Text(params.label) },
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = params.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Dimen.Size.extraLarge),
        visualTransformation = params.visualTransformation
    )
}

private fun getTextFieldValue(
    isSignIn: Boolean,
    signInTextField: String,
    signUpTextField: String
): String {
    return if (isSignIn) signInTextField else signUpTextField
}

private fun handleOnValueChange(
    textFieldType: TextFieldType,
    viewModel: AuthViewModel,
    value: String,
) {
    when (textFieldType) {
        TextFieldType.EMAIL_SIGN_IN -> viewModel.onEmailSignInChange(value)
        TextFieldType.PASSWORD_SIGN_IN -> viewModel.onPasswordSignInChange(value)
        TextFieldType.EMAIL_SIGN_UP -> viewModel.onEmailSignUpChange(value)
        TextFieldType.PASSWORD_SIGN_UP -> viewModel.onPasswordSignUpChange(value)
    }
}

private fun handleTextFieldIsError(
    textFieldType: TextFieldType,
    viewModel: AuthViewModel
): Boolean {
    return when (textFieldType) {
        TextFieldType.EMAIL_SIGN_IN -> isBlank(viewModel.emailSignInError)
        TextFieldType.PASSWORD_SIGN_IN -> isBlank(viewModel.passwordSignInError)
        TextFieldType.EMAIL_SIGN_UP -> isBlank(viewModel.emailSignUpError)
        TextFieldType.PASSWORD_SIGN_UP -> isBlank(viewModel.passwordSignUpError)
    }
}

private fun isBlank(stateFlow: StateFlow<String>): Boolean {
    return stateFlow.value.isBlank()
}

@Composable
private fun getButtonText(isSignIn: Boolean): String {
    return if (isSignIn) "Sign In" else "Sign Up"
}

@Composable
private fun getErrorMessage(
    textFieldType: TextFieldType,
    viewModel: AuthViewModel
): String {
    return when (textFieldType) {
        TextFieldType.EMAIL_SIGN_IN -> viewModel.emailSignInError.collectAsStateWithLifecycle().value
        TextFieldType.PASSWORD_SIGN_IN -> viewModel.passwordSignInError.collectAsStateWithLifecycle().value
        TextFieldType.EMAIL_SIGN_UP -> viewModel.emailSignUpError.collectAsStateWithLifecycle().value
        TextFieldType.PASSWORD_SIGN_UP -> viewModel.passwordSignUpError.collectAsStateWithLifecycle().value
    }
}