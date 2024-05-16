package com.amadiyawa.feature_auth.presentation.screen.auth

import androidx.compose.ui.text.input.VisualTransformation
import com.amadiyawa.feature_base.common.enum.TextFieldType

internal data class AuthTextFieldParams(
    val isSignIn: Boolean,
    val signInTextField: String,
    val signUpTextField: String,
    val textFieldTypeSignIn: TextFieldType,
    val textFieldTypeSignUp: TextFieldType,
    val label: String,
    val viewModel: AuthViewModel,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val errorMessage: String
)