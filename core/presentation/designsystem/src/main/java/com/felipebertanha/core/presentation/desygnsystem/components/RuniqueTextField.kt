package com.felipebertanha.core.presentation.desygnsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felipebertanha.core.presentation.desygnsystem.CheckIcon
import com.felipebertanha.core.presentation.desygnsystem.EmailIcon
import com.felipebertanha.core.presentation.desygnsystem.RuniqueTheme

@Composable
fun RuniqueTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    additionalInfo: String? = null,
) {
    var isFocused by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title != null) {
                Text(
                    text = title, color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (error != null) {
                Text(
                    text = error, color = MaterialTheme.colorScheme.error, fontSize = 12.sp
                )
            } else if (additionalInfo != null) {
                Text(
                    text = additionalInfo,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )

            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            state = state,
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    when {
                        isFocused -> MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                        else -> MaterialTheme.colorScheme.surface
                    }
                )
                .border(
                    width = 1.dp, color = when {
                        isFocused -> MaterialTheme.colorScheme.primary
                        else -> Color.Transparent
                    }, shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
                .fillMaxWidth()
                .onFocusChanged(
                    onFocusChanged = {
                        isFocused = it.isFocused
                    }),
            decorator = { innerBox ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (startIcon != null) {
                        Icon(
                            imageVector = startIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        if(state.text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerBox()

                    }
                    if (endIcon != null) {
                        Icon(
                            imageVector = endIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

        )

    }

}

@Preview
@Composable
private fun RuniqueTextFieldPreview() {
    RuniqueTheme {
        RuniqueTextField(
            state = rememberTextFieldState(),
            title = "Email",
            hint = "Enter your email",
            startIcon = EmailIcon,
            endIcon = CheckIcon,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun RuniqueTextFieldWithErrorPreview() {
    RuniqueTheme {
        RuniqueTextField(
            state = rememberTextFieldState(),
            title = "Email",
            hint = "Enter your email",
            error = "Enter a valid email",
            startIcon = EmailIcon,
            endIcon = CheckIcon,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun RuniqueTextFieldWithAdditionalInfoPreview() {
    RuniqueTheme {
        RuniqueTextField(
            state = rememberTextFieldState(),
            title = "Email",
            hint = "Enter your email",
            additionalInfo = "A useful info",
            startIcon = EmailIcon,
            endIcon = CheckIcon,
            modifier = Modifier.fillMaxWidth()
        )
    }
}