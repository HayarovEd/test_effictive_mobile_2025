package com.edurda77.main_screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.edurda77.domain.model.Course
import com.edurda77.resources.uikit.UiBaseScaffold
import com.edurda77.resources.uikit.UiTextField
import org.koin.androidx.compose.koinViewModel
import com.edurda77.resources.R
import com.edurda77.resources.theme.green
import com.edurda77.resources.theme.lightGrey
import com.edurda77.resources.theme.white
import com.edurda77.resources.uikit.ItemCourse
import kotlinx.datetime.LocalDate

@Composable
fun MainScreenRoot(
    viewModel: MainScreenViewModel = koinViewModel(),
    bottomBarContent: @Composable () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        bottomBarContent = bottomBarContent
    )
}

@Composable
fun MainScreenScreen(
    modifier: Modifier = Modifier,
    state: MainScreenState,
    onAction: (MainScreenAction) -> Unit,
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
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UiTextField(
                        modifier = modifier
                            .weight(6f),
                        label = stringResource(R.string.search_courses),
                        content = state.query,
                        leadingIcon = Icons.Default.Search,
                        onClickContent = {
                            onAction(MainScreenAction.UpdateSearch(it))
                        }
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    IconButton(
                        modifier = modifier.size(48.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = lightGrey
                        ),
                        onClick = {}
                    ) {
                        Icon(
                            modifier = modifier,
                            imageVector = ImageVector.vectorResource(R.drawable.outline_filter_alt_24),
                            contentDescription = "",
                            tint = white
                        )
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .align(Alignment.End)
                        .clickable {
                            onAction(MainScreenAction.SortByPublishDate)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.sorting),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = green
                    )
                    Icon(
                        modifier = modifier,
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_down_up),
                        contentDescription = "",
                        tint = green
                    )
                }
            }
        },
        bottomBarContent = bottomBarContent
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = modifier.size(100.dp),
                    color = green
                )
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.remoteCourses) { course ->
                    ItemCourse(
                        course = course,
                        isFavorite = state.favoriteCourses.contains(course),
                        onClickUpdateFavorite = {
                            onAction(MainScreenAction.UpdateFavorite(course))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val courses = listOf(
        Course(
            id = 1,
            price = "999",
            title = "Java-разработчик с нуля",
            rate = "4.9",
            startDate = LocalDate.parse("2024-05-22"),
            publishDate = LocalDate.parse("2024-02-02"),
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        ),
        Course(
            id = 1,
            price = "999",
            title = "Java-разработчик с нуля",
            rate = "4.9",
            startDate = LocalDate.parse("2024-05-22"),
            publishDate = LocalDate.parse("2024-02-02"),
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        ),
        Course(
            id = 1,
            price = "999",
            title = "Java-разработчик с нуля",
            rate = "4.9",
            startDate = LocalDate.parse("2024-05-22"),
            publishDate = LocalDate.parse("2024-02-02"),
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        ),
    )
    MainScreenScreen(
        state = MainScreenState(
            remoteCourses = courses
        ),
        onAction = {},
        bottomBarContent = {}
    )
}