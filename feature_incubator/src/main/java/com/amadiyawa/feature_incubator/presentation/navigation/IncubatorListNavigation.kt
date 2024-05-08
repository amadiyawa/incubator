package com.amadiyawa.feature_incubator.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.amadiyawa.feature_base.presentation.navigation.AppNavigationDestination
import com.amadiyawa.feature_incubator.presentation.screen.incubatorlist.IncubatorListScreen

object IncubatorListNavigation: AppNavigationDestination {
    private const val INCUBATOR_LIST = "incubator_list"
    private const val INCUBATOR_DETAIL = "incubator_detail/{incubatorId}"

    fun incubatorDetailRoute(incubatorId: Int) = "incubator_detail/$incubatorId"

    override val route = INCUBATOR_LIST
    override val destination = "incubator_list_destination"
}

fun NavGraphBuilder.incubatorListGraph(
    onNavigateToIncubatorDetail: (uuid: Int) -> Unit,
    nestedGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = IncubatorListNavigation.destination,
        route = IncubatorListNavigation.route
    ) {
        composable(route = IncubatorListNavigation.destination) {
            IncubatorListScreen(
                onUserClick = onNavigateToIncubatorDetail
            )
        }
        nestedGraph()
    }
}