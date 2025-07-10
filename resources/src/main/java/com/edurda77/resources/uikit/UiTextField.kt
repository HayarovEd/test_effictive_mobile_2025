package com.edurda77.resources.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.edurda77.domain.utils.isAllowed
import com.edurda77.resources.theme.lightGrey
import com.edurda77.resources.theme.white

@Composable
fun UiTextField(
    modifier: Modifier = Modifier,
    content: String,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isNotCyrillic: Boolean = false,
    leadingIcon: ImageVector? = null,
    onClickContent: (String) -> Unit,
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = content,
        onValueChange = { text ->
            if (isNotCyrillic) {
                val filteredText = text.filter { char ->
                    char.isAllowed()
                }
                onClickContent(filteredText)
            } else  {
                onClickContent(text)
            }
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = white),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        leadingIcon = {
            if (leadingIcon != null) {
                UiIconButton(
                    icon = leadingIcon,
                    color = white,
                    onClick = {  }
                )
            }
        },
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = lightGrey,
            unfocusedContainerColor = lightGrey,
            cursorColor = white,
            focusedTextColor = white,
            unfocusedTextColor = white,
            focusedPlaceholderColor = white,
            unfocusedPlaceholderColor = white,
            focusedBorderColor = lightGrey,
            unfocusedBorderColor = lightGrey
        )
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun UiTextFieldView() {
    UiTextField(
        content = "",
        label = "hallo",
        onClickContent = {}
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun UiTextFieldView2() {
    UiTextField(
        content = "world!",
        label = "hallo",
        onClickContent = {}
    )
}


