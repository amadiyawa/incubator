package com.amadiyawa.feature_auth.presentation.screen.auth

import androidx.compose.runtime.Immutable
import com.amadiyawa.feature_auth.domain.model.SignIn
import com.amadiyawa.feature_auth.domain.model.SignUp
import com.amadiyawa.feature_base.presentation.viewmodel.BaseAction
import com.amadiyawa.feature_base.presentation.viewmodel.BaseState
import com.amadiyawa.feature_base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AuthViewModel : BaseViewModel<UiState, Action>(UiState.IDLE) {
    private var _emailSignIn = MutableStateFlow("")
    val emailSignIn = _emailSignIn.asStateFlow()

    private var _emailSignInError = MutableStateFlow("")
    val emailSignInError = _emailSignInError.asStateFlow()

    private var _passwordSignIn = MutableStateFlow("")
    val passwordSignIn = _passwordSignIn.asStateFlow()

    private var _passwordSignInError = MutableStateFlow("")
    val passwordSignInError = _passwordSignInError.asStateFlow()

    private var _emailSignUp = MutableStateFlow("")
    val emailSignUp = _emailSignUp.asStateFlow()

    private var _emailSignUpError = MutableStateFlow("")
    val emailSignUpError = _emailSignUpError.asStateFlow()

    private var _passwordSignUp = MutableStateFlow("")
    val passwordSignUp = _passwordSignUp.asStateFlow()

    private var _passwordSignUpError = MutableStateFlow("")
    val passwordSignUpError = _passwordSignUpError.asStateFlow()

    private var _isSignIn = MutableStateFlow(true)
    val isSignIn = _isSignIn.asStateFlow()

    fun onEmailSignInChange(email: String) {
        _emailSignIn.value = email
        if (!isValidEmail(email)) {
            _emailSignInError.value = "Invalid email format"
        } else {
            _emailSignInError.value = ""
        }
    }

    fun onEmailSignUpChange(email: String) {
        _emailSignUp.value = email
        if (!isValidEmail(email)) {
            _emailSignUpError.value = "Invalid email format"
        } else {
            _emailSignUpError.value = ""
        }
    }

    fun onPasswordSignInChange(password: String) {
        _passwordSignIn.value = password
        if (!isValidPassword(password)) {
            _passwordSignInError.value = "Password must be at least 6 characters"
        } else {
            _passwordSignInError.value = ""
        }
    }

    fun onPasswordSignUpChange(password: String) {
        _passwordSignUp.value = password
        if (!isValidPassword(password)) {
            _passwordSignUpError.value = "Password must be at least 6 characters"
        } else {
            _passwordSignUpError.value = ""
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isButtonEnabled(): Boolean {
        return if (_isSignIn.value) {
            _emailSignIn.value.isNotBlank() && _passwordSignIn.value.isNotBlank() && _emailSignInError.value.isBlank() && _passwordSignInError.value.isBlank()
        } else {
            _emailSignUp.value.isNotBlank() && _passwordSignUp.value.isNotBlank() && _emailSignUpError.value.isBlank() && _passwordSignUpError.value.isBlank()
        }
    }

    fun signInOrSignUp() {
        if (_isSignIn.value) {
            val signIn = SignIn(email = _emailSignIn.value, password = _passwordSignIn.value)
            val currentState = getCurrentState()
            if (currentState is UiState.IDLE) {
                val action = Action.SignInSuccess(signIn)
                sendAction(action)
            }
        } else {
            val signUp = SignUp(email = _emailSignUp.value, password = _passwordSignUp.value)
            val currentState = getCurrentState()
            if (currentState is UiState.IDLE) {
                val action = Action.SignUpSuccess(signUp)
                sendAction(action)
            }
        }
    }
}

internal sealed interface Action: BaseAction<UiState> {
    data object Loading : Action {
        override fun reduce(state: UiState) = UiState.Loading
    }

    data class SignInSuccess(private val signIn: SignIn) : Action {
        override fun reduce(state: UiState) = UiState.SignInContent(signIn = signIn)
    }

    data class SignUpSuccess(private val signUp: SignUp) : Action {
        override fun reduce(state: UiState) = UiState.SignUpContent(signUp = signUp)
    }

    data object Error : Action {
        override fun reduce(state: UiState) = UiState.Error
    }
}

@Immutable
internal sealed interface UiState : BaseState {
    data class SignInContent(val signIn: SignIn) : UiState
    data class SignUpContent(val signUp: SignUp) : UiState
    data object IDLE : UiState
    data object Loading : UiState
    data object Error : UiState
}