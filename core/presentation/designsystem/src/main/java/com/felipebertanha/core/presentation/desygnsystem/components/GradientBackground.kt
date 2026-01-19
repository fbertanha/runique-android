package com.felipebertanha.core.presentation.desygnsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.felipebertanha.core.presentation.desygnsystem.RuniqueTheme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthPx = with(density) {
        configuration.screenWidthDp.dp.roundToPx()
    }

    val smallerDimensionPx = minOf(
        configuration.screenWidthDp.dp, configuration.screenHeightDp.dp
    ).let { dimension ->
        with(density) {
            dimension.roundToPx()
        }
    }

    val primaryColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .blur(
                    smallerDimensionPx.dp / 3f
                )
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            primaryColor,
                            MaterialTheme.colorScheme.background,
                        ),
                        center = Offset(
                            x = screenWidthPx / 2f,
                            y = -100f
                        ),
                        radius = smallerDimensionPx / 2f
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (hasToolbar) {
                        Modifier
                    } else {
                        Modifier.systemBarsPadding()
                    }
                )
        ) {
            content()
        }
    }

}

@PreviewScreenSizes
@Composable
private fun GradientBackgroundPreview() {
    RuniqueTheme {
        GradientBackground(modifier = Modifier.fillMaxSize()) {

        }
    }

}

