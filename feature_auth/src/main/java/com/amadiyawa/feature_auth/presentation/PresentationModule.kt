package com.amadiyawa.feature_auth.presentation

import com.amadiyawa.feature_auth.presentation.screen.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {
    viewModelOf(::AuthViewModel)
}