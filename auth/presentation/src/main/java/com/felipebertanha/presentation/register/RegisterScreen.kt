package com.felipebertanha.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felipebertanha.auth.domain.PasswordValidationState
import com.felipebertanha.auth.domain.UserDataValidator
import com.felipebertanha.auth.presentation.R
import com.felipebertanha.core.presentation.desygnsystem.CheckIcon
import com.felipebertanha.core.presentation.desygnsystem.CrossIcon
import com.felipebertanha.core.presentation.desygnsystem.EmailIcon
import com.felipebertanha.core.presentation.desygnsystem.Poppins
import com.felipebertanha.core.presentation.desygnsystem.RuniqueDarkRed
import com.felipebertanha.core.presentation.desygnsystem.RuniqueGray
import com.felipebertanha.core.presentation.desygnsystem.RuniqueGreen

import com.felipebertanha.core.presentation.desygnsystem.RuniqueTheme
import com.felipebertanha.core.presentation.desygnsystem.components.GradientBackground
import com.felipebertanha.core.presentation.desygnsystem.components.RuniqueActionButton
import com.felipebertanha.core.presentation.desygnsystem.components.RuniquePasswordTextField
import com.felipebertanha.core.presentation.desygnsystem.components.RuniqueTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {

    RegisterScreen(
        state = viewModel.state, onAction = viewModel::onAction
    )

}

@Composable
fun RegisterScreen(
    state: RegisterState, onAction: (RegisterAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )

            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins, color = RuniqueGray
                    )
                ) {
                    append(stringResource(R.string.already_have_an_account) + " ")

                    val link = LinkAnnotation.Clickable(
                        tag = "login_action", linkInteractionListener = {
                            onAction(RegisterAction.OnLoginClick)
                        })

                    withLink(link) {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = Poppins,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(stringResource(R.string.login))
                        }
                    }
                }
            }

            Text(
                text = annotatedString
            )
            Spacer(modifier = Modifier.height(48.dp))
            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else null,
                hint = stringResource(R.string.enter_email_hint),
                title = stringResource(R.string.email_title),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(R.string.must_be_a_valid_email_info),
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.height(16.dp))
            RuniquePasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                hint = stringResource(R.string.enter_password_hint),
                title = stringResource(R.string.password_title),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            PasswordVerifier(
                text = stringResource(
                    id = R.string.at_least_x_characters,
                    UserDataValidator.MIN_PASSWORD_LENGTH
                ),
                isValid = state.passwordValidationState.hasMinLength
            )
            Spacer(Modifier.height(4.dp))
            PasswordVerifier(
                text = stringResource(
                    id = R.string.at_least_one_number
                ),
                isValid = state.passwordValidationState.hasNumber
            )
            Spacer(Modifier.height(4.dp))
            PasswordVerifier(
                text = stringResource(
                    id = R.string.contains_lowercase_characters
                ),
                isValid = state.passwordValidationState.hasLowerCaseChar
            )
            Spacer(Modifier.height(4.dp))
            PasswordVerifier(
                text = stringResource(
                    id = R.string.contains_uppercase_characters
                ),
                isValid = state.passwordValidationState.hasUpperCaseChar
            )
            Spacer(Modifier.height(32.dp))
            RuniqueActionButton(
                text = stringResource(R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                }
            )
        }
    }
}

@Composable
fun PasswordVerifier(
    text: String, isValid: Boolean, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        Icon(
            imageVector = if (isValid) CheckIcon else CrossIcon,
            contentDescription = null,
            tint = if (isValid) RuniqueGreen else RuniqueDarkRed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RuniqueTheme {
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true,
                    hasNumber = true,
                    hasLowerCaseChar = true,
                    hasUpperCaseChar = true
                ),
            ), onAction = {})
    }
}

@Preview
@Composable
private fun RegisterScreenPreviewWithInvalidPassword() {
    RuniqueTheme {
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true
                ),
            ), onAction = {})
    }
}

@Preview
@Composable
private fun RegisterScreenPreviewWithLoadingState() {
    RuniqueTheme {
        RegisterScreen(
            state = RegisterState(
                isRegistering = true
            ), onAction = {})
    }
}