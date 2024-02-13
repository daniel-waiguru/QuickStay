package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WormPageIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 10.dp,
    spacing: Dp = indicatorSize,
    selectedMultiplier: Int = 2
) {
    val rowWidth = (indicatorSize * (selectedMultiplier + (totalPages - 1))) +
            (spacing * (totalPages - 1))
    Row(
        modifier = modifier
            .requiredWidth(rowWidth),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val selected = index == currentPage
            val width: Dp by animateDpAsState(
                if (selected) indicatorSize * selectedMultiplier else indicatorSize,
                label = "DpAnimation",
                animationSpec = tween(durationMillis = 500)
            )
            val color1 = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
            Canvas(
                modifier = Modifier
                    .size(width, indicatorSize),
                onDraw = {
                    drawRoundRect(
                        color = color1,
                        cornerRadius = CornerRadius(indicatorSize.toPx() / 2),
                        size = Size(width.toPx(), indicatorSize.toPx())
                    )
                }
            )
        }
    }
}