package com.amadiyawa.feature_auth.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(Dimen.Padding.screenContent)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimen.Padding.screenContent)
            ) {
                HandleAuthRow(isSignIn, viewModel)
                HandleAuthLine(isSignIn)
                HandleAuthBody(isSignIn)
                HandleAuthTextFields(isSignIn, emailSignIn, passwordSignIn, emailSignUp, passwordSignUp, viewModel)
                HandleAuthButton(isSignIn, viewModel, onSignIn)
            }
        }
    }
}

@Composable
private fun HandleAuthRow(isSignIn: State<Boolean>, viewModel: AuthViewModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextTitleLarge(
                text = stringResource(id = R.string.sign_in),
                color = if (isSignIn.value) MaterialTheme.colorScheme.primary else Color.LightGray,
                modifier = Modifier.clickable { viewModel.setIsSignIn(true) },
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.End) {
            TextTitleLarge(
                text = stringResource(id = R.string.sign_up),
                color = if (!isSignIn.value) MaterialTheme.colorScheme.primary else Color.LightGray,
                modifier = Modifier.clickable { viewModel.setIsSignIn(false) },
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun HandleAuthLine(isSignIn: State<Boolean>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SignLine(isActive = isSignIn.value)
        SignLine(isActive = !isSignIn.value)
    }
}

@Composable
private fun RowScope.SignLine(isActive: Boolean) {
    Row(
        modifier = Modifier
            .height(4.dp)
            .weight(1f)
            .background(if (isActive) MaterialTheme.colorScheme.primary else Color.LightGray)
    ){}
}

@Composable
private fun HandleAuthBody(isSignIn: State<Boolean>) {
    TextTitleLarge(
        modifier = Modifier
            .fillMaxWidth(),
        text = if (isSignIn.value) stringResource(id = R.string.sign_in_body) else stringResource(id = R.string.sign_up_body),
    )
}

@Composable
private fun HandleAuthTextFields(
    isSignIn: State<Boolean>,
    emailSignIn: State<String>,
    passwordSignIn: State<String>,
    emailSignUp: State<String>,
    passwordSignUp: State<String>,
    viewModel: AuthViewModel
) {
    AuthTextField(
        params = AuthTextFieldParams(
            isSignIn = isSignIn.value,
            signInTextField = emailSignIn.value,
            signUpTextField = emailSignUp.value,
            textFieldTypeSignIn = TextFieldType.EMAIL_SIGN_IN,
            textFieldTypeSignUp = TextFieldType.EMAIL_SIGN_UP,
            label = stringResource(id = R.string.email),
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
            label = stringResource(id = R.string.password),
            viewModel = viewModel,
            visualTransformation = PasswordVisualTransformation(),
            errorMessage = getErrorMessage(
                if (isSignIn.value) TextFieldType.PASSWORD_SIGN_IN else TextFieldType.PASSWORD_SIGN_UP,
                viewModel
            )
        )
    )
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
private fun HandleAuthButton(
    isSignIn: State<Boolean>,
    viewModel: AuthViewModel,
    onSignIn: () -> Unit
) {
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

@Composable
private fun getButtonText(isSignIn: Boolean): String {
    return if (isSignIn) stringResource(id = R.string.sign_in) else stringResource(id = R.string.sign_up)
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