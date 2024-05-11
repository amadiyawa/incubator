package com.amadiyawa.feature_incubator.presentation.screen.incubatorlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.amadiyawa.feature_base.common.res.Dimen
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleLarge
import com.amadiyawa.feature_incubator.R
import com.amadiyawa.feature_incubator.presentation.compose.composable.FloatingActionButton
import com.amadiyawa.feature_incubator.presentation.compose.composable.Toolbar

@Composable
fun IncubatorListScreen(
    onIncubatorClick: (Int) -> Unit,
) {
    Scaffold(
        contentColor = MaterialTheme.colorScheme.onBackground,
        floatingActionButton = {
            FloatingActionButton(
                imageVector = Icons.Filled.Add,
                onClick = { onIncubatorClick(13) },
                label = stringResource(id = R.string.incubator_detail_demo)
            )
        },
        topBar = { SetUpToolbar() },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        SetupContent(
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun SetUpToolbar() {
    Toolbar(centered = false, title = stringResource(R.string.baby_incubator), null)
}

@Composable
private fun SetupContent(paddingValues: PaddingValues) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ){
        HandleUiState()
    }
}

@Composable
private fun HandleUiState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.Padding.screenContent)
    ) {
        TextTitleLarge(text = stringResource(R.string.coming_soon))
    }
}