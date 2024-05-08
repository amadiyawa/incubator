package com.amadiyawa.incubator.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.amadiyawa.feature_base.presentation.navigation.AppState
import com.amadiyawa.feature_base.presentation.navigation.rememberAppState
import com.amadiyawa.feature_base.presentation.theme.AppTheme
import com.amadiyawa.incubator.presentation.navigation.AppNavHost

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BabyIncubator(
    windowSizeClass: WindowSizeClass,
    appState: AppState = rememberAppState(windowSizeClass = windowSizeClass)
) {
    AppTheme {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                AppNavHost(
                    navController = appState.navController,
                    onNavigateToDestination = appState::navigate,
                    onBackClick = appState::onBackClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}