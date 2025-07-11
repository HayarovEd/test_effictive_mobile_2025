package com.edurda77.profile_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edurda77.resources.R
import com.edurda77.resources.theme.white
import com.edurda77.resources.uikit.UiBaseScaffold

@Composable
fun ProfileScreenRoot(
    bottomBarContent: @Composable () -> Unit,
) {

    ProfileScreen(
        bottomBarContent = bottomBarContent
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    bottomBarContent: @Composable () -> Unit,
) {
    UiBaseScaffold(
        message = null,
        topBarContent = {
            Column(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.profile),
                    fontSize = 22.sp,
                    fontWeight = FontWeight(400),
                    color = white
                )
            }
        },
        bottomBarContent = bottomBarContent
    ) { paddingValues ->

    }
}

@Preview
@Composable
private fun Preview() {
    ProfileScreen(
        bottomBarContent = {}
    )
}