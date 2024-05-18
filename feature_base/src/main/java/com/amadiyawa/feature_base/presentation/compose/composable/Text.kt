package com.amadiyawa.feature_base.presentation.compose.composable

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextTitleLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current,
    )
}

@Composable
fun TextTitleMedium(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}

@Composable
fun TextTitleSmall(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}

@Composable
fun TextBodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}

@Composable
fun TextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}

@Composable
fun TextBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}

@Composable
fun TextCaption(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = fontWeight,
        color = color ?: LocalContentColor.current
    )
}