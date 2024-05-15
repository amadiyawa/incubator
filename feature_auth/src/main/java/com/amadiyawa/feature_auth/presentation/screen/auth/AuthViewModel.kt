package com.amadiyawa.feature_auth.presentation.screen.auth

import androidx.compose.runtime.Immutable
import com.amadiyawa.feature_auth.domain.model.SignIn
import com.amadiyawa.feature_auth.domain.model.SignUp
import com.amadiyawa.feature_base.presentation.viewmodel.BaseAction
import com.amadiyawa.feature_base.presentation.viewmodel.BaseState
import com.amadiyawa.feature_base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AuthViewModel : BaseViewModel<UiState, Action>(UiState.Loading) {
    private var _emailSignIn = MutableStateFlow("")
    val emailSignIn = _emailSignIn.asStateFlow()

    private var _passwordSignIn = MutableStateFlow("")
    val passwordSignIn = _passwordSignIn.asStateFlow()

    private var _emailSignUp = MutableStateFlow("")
    val emailSignUp = _emailSignUp.asStateFlow()

    private var _passwordSignUp = MutableStateFlow("")
    val passwordSignUp = _passwordSignUp.asStateFlow()
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
    data object Loading : UiState
    data object Error : UiState
}