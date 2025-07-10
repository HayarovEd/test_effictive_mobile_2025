package com.edurda77.resources.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edurda77.resources.theme.orange1


@Composable
fun UiProgressIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(100.dp),
            color = orange1,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UiProgressIndicatorView() {
    UiProgressIndicator()
}