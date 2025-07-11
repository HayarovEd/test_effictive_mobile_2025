package com.edurda77.login_screen

import android.content.Intent
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.edurda77.domain.utils.OK_URL
import com.edurda77.resources.uikit.UiBaseScaffold
import com.edurda77.resources.R
import com.edurda77.resources.theme.blue
import com.edurda77.resources.theme.green
import com.edurda77.resources.theme.orange1
import com.edurda77.resources.theme.orange2
import com.edurda77.resources.theme.stroke
import com.edurda77.resources.theme.white
import com.edurda77.resources.uikit.UiTextField
import androidx.core.net.toUri
import com.edurda77.domain.utils.VK_URL
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginScreenViewModel = koinViewModel(),
    onNavigateToMain: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateToMain = onNavigateToMain
    )
}

@Composable
fun LoginScreenScreen(
    modifier: Modifier = Modifier,
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
    onNavigateToMain: () -> Unit,
) {
    val context = LocalContext.current

    UiBaseScaffold(
        message = null
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.enter),
                fontSize = 28.sp,
                fontWeight = FontWeight(400),
                color = white
            )
            Spacer(modifier = modifier.height(15.dp))
            Text(
                text = stringResource(R.string.email),
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                color = white
            )
            Spacer(modifier = modifier.height(5.dp))
            UiTextField(
                label = stringResource(R.string.email_mask),
                content = state.email,
                keyboardType = KeyboardType.Email,
                isNotCyrillic = true,
                onClickContent = {
                    onAction(LoginScreenAction.UpdateEmail(it))
                }
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.password),
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                color = white
            )
            Spacer(modifier = modifier.height(5.dp))
            UiTextField(
                label = stringResource(R.string.enter_password),
                content = state.password,
                onClickContent = {
                    onAction(LoginScreenAction.UpdatePassword(it))
                }
            )
            Spacer(modifier = modifier.height(15.dp))
            Button(
                modifier = modifier.fillMaxWidth(),
                enabled = state.email.isNotBlank()&&state.password.isNotBlank()&&isEmailValid(state.email),
                onClick = onNavigateToMain,
                colors = ButtonDefaults.buttonColors(
                    containerColor = green,
                    disabledContainerColor = green.copy(0.5f)
                )
            ) {
                Text(
                    text = stringResource(R.string.enter),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = white
                )
            }
            Spacer(modifier = modifier.height(10.dp))
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = white,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600)
                        )
                    ) {
                        append(stringResource(R.string.no_profile))
                    }
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = green,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600)
                        )
                    ) {
                        append("${stringResource(R.string.registration)}\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = green,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600)
                        )
                    ) {
                        append(stringResource(R.string.forget_password))
                    }
                }
            )
            Spacer(modifier = modifier.height(15.dp))
            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = stroke
            )
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(30.dp))
                        .background(blue)
                        .padding(vertical = 10.dp)
                        .clickable{
                            val intent = Intent(Intent.ACTION_VIEW, VK_URL.toUri())
                            context.startActivity(intent)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.vk),
                        contentDescription = "",
                        tint = white
                    )
                }
                Spacer(modifier = modifier.width(10.dp))
                Box(
                    modifier = modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(30.dp))
                        .background(brush = Brush.verticalGradient(listOf(orange1, orange2)))
                        .padding(vertical = 10.dp)
                        .clickable{
                            val intent = Intent(Intent.ACTION_VIEW, OK_URL.toUri())
                            context.startActivity(intent)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ok),
                        contentDescription = "",
                        tint = white
                    )
                }
            }
        }
    }
}


private fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
private fun Preview() {
    LoginScreenScreen(
        state = LoginScreenState(),
        onAction = {},
        onNavigateToMain = {}
    )
}