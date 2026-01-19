package com.felipebertanha.core.presentation.desygnsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipebertanha.core.presentation.desygnsystem.RuniqueBlack
import com.felipebertanha.core.presentation.desygnsystem.RuniqueGray
import com.felipebertanha.core.presentation.desygnsystem.RuniqueTheme

@Composable
fun RuniqueActionButton(
    text: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(IntrinsicSize.Min),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = RuniqueGray,
            disabledContentColor = RuniqueBlack,
        ),
        shape = RoundedCornerShape(100f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text,
                modifier = Modifier
                    .alpha(if (isLoading) 0f else 1f),
                fontWeight = FontWeight.Medium
            )
        }

    }
}

@Composable
fun RuniqueOutlinedActionButton(
    text: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(IntrinsicSize.Min),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(100f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text,
                modifier = Modifier
                    .alpha(if (isLoading) 0f else 1f),
                fontWeight = FontWeight.Medium
            )
        }

    }
}

@Preview
@Composable
private fun RuniqueActionButtonDefault() {
    RuniqueTheme {
        RuniqueActionButton(
            text = "Sample Button",
        ) {}
    }
}

@Preview
@Composable
private fun RuniqueActionButtonLoading() {
    RuniqueTheme {
        RuniqueActionButton(
            "Sample Button",
            isLoading = true
        ) {}
    }
}

@Preview
@Composable
private fun RuniqueActionButtonDisabled() {
    RuniqueTheme {
        RuniqueActionButton(
            "Sample Button",
            enabled = false
        ) {}
    }
}

@Preview
@Composable
private fun RuniqueOutlinedActionButtonDefault() {
    RuniqueTheme {
        RuniqueOutlinedActionButton(
            text = "Sample Button",
        ) {}
    }
}

@Preview
@Composable
private fun RuniqueOutlinedActionButtonLoading() {
    RuniqueTheme {
        RuniqueOutlinedActionButton(
            "Sample Button",
            isLoading = true
        ) {}
    }
}

@Preview
@Composable
private fun RuniqueOutlinedActionButtonDisabled() {
    RuniqueTheme {
        RuniqueOutlinedActionButton(
            "Sample Button",
            enabled = false
        ) {}
    }
}