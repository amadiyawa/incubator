package com.amadiyawa.feature_incubator.presentation.compose.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.amadiyawa.feature_base.common.res.Dimen
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleLarge
import com.amadiyawa.feature_incubator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    centered: Boolean = false,
    title: String = stringResource(id = R.string.empty_toolbar_title),
    onBackClick: (() -> Unit)?,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
){
    if (centered) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement
                        .spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(Dimen.Size.medium),
                        painter = painterResource(id = R.drawable.cameroon_flag_icon),
                        contentDescription = stringResource(id = R.string.country_flag),
                        tint = Color.Unspecified
                    )
                    TextTitleLarge(text = title)
                    Icon(
                        modifier = Modifier
                            .size(Dimen.Size.extraLarge),
                        painter = painterResource(id = R.drawable.aui),
                        contentDescription = stringResource(id = R.string.country_flag),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = backgroundColor
            ),
            navigationIcon = {
                IconButton(onClick = onBackClick!!) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            windowInsets = WindowInsets(0, 0, 0, 0)
        )
    } else {
        TopAppBar(
            title = {
                TextTitleLarge(
                    text = title,
                )
            },
            actions = {

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor
            ),
            windowInsets = WindowInsets(0, 0, 0, 0)
        )
    }
}