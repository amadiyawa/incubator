package com.amadiyawa.feature_auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amadiyawa.feature_auth.presentation.screen.auth.AuthScreen
import com.amadiyawa.feature_base.presentation.navigation.AppNavigationDestination

object AuthNavigation: AppNavigationDestination {
    override val route = "auth"
    override val destination = "auth_destination"
}

fun NavGraphBuilder.authGraph(
    onNavigateToSignIn: () -> Unit
) {
    composable(route = AuthNavigation.route) {
        AuthScreen(
            onSignIn = onNavigateToSignIn
        )
    }
}