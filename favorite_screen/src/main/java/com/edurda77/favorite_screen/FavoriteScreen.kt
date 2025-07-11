package com.edurda77.favorite_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.edurda77.resources.R
import com.edurda77.resources.theme.white
import com.edurda77.resources.uikit.ItemCourse
import com.edurda77.resources.uikit.UiBaseScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreenRoot(
    viewModel: FavoriteScreenViewModel = koinViewModel(),
    bottomBarContent: @Composable () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoriteScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        bottomBarContent = bottomBarContent
    )
}

@Composable
fun FavoriteScreenScreen(
    modifier: Modifier = Modifier,
    state: FavoriteScreenState,
    onAction: (FavoriteScreenAction) -> Unit,
    bottomBarContent: @Composable () -> Unit,
) {
    UiBaseScaffold(
        message = state.message,
        topBarContent = {
            Column(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.favorite),
                    fontSize = 22.sp,
                    fontWeight = FontWeight(400),
                    color = white
                )
            }
        },
        bottomBarContent = bottomBarContent
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.favoriteCourses) { course ->
                ItemCourse(
                    course = course,
                    isFavorite = true,
                    onClickUpdateFavorite = {
                        onAction(FavoriteScreenAction.DeleteFromFavorite(course.id))
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FavoriteScreenScreen(
        state = FavoriteScreenState(),
        onAction = {},
        bottomBarContent = {}
    )
}