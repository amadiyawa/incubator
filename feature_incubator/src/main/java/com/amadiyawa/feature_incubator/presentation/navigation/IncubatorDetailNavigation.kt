package com.amadiyawa.feature_incubator.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amadiyawa.feature_base.presentation.navigation.AppNavigationDestination
import com.amadiyawa.feature_incubator.presentation.screen.incubatordetail.IncubatorDetailScreen

object IncubatorDetailNavigation: AppNavigationDestination {
    private const val INCUBATOR_DETAIL = "incubator_detail/{incubatorID}"

    fun incubatorDetailRoute(incubatorId: String) = "incubator_detail/$incubatorId"

    override val route = INCUBATOR_DETAIL
    override val destination = "incubator_detail_destination"
}

fun NavGraphBuilder.incubatorDetailGraph(
    onBackClick: () -> Unit
) {
    composable(route = IncubatorDetailNavigation.route) { backStackEntry ->
        val incubatorId = backStackEntry.arguments?.getString("incubatorId")
        if (incubatorId != null) {
            IncubatorDetailScreen(
                onBackClick = onBackClick,
                incubatorId = incubatorId
            )
        }
    }
}