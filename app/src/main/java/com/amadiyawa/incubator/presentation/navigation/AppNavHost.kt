package com.amadiyawa.incubator.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amadiyawa.feature_auth.presentation.navigation.AuthNavigation
import com.amadiyawa.feature_auth.presentation.navigation.authGraph
import com.amadiyawa.feature_base.presentation.navigation.AppNavigationDestination
import com.amadiyawa.feature_incubator.presentation.navigation.IncubatorListNavigation
import com.amadiyawa.feature_incubator.presentation.navigation.incubatorListGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    onNavigateToDestination: (AppNavigationDestination, String?) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = AuthNavigation.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        authGraph{
            navController.popBackStack()
            onNavigateToDestination(IncubatorListNavigation, IncubatorListNavigation.route)
        }
        incubatorListGraph(
            onNavigateToIncubatorDetail = { incubatorId ->
                onNavigateToDestination(
                    IncubatorListNavigation,
                    IncubatorListNavigation.incubatorDetailRoute(incubatorId)
                )
            },
            onBackClick = onBackClick
        )
    }
}