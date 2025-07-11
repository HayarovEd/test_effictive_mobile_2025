package com.edurda77.resources.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edurda77.domain.model.Course
import com.edurda77.resources.R
import com.edurda77.resources.theme.darkGrey
import com.edurda77.resources.theme.green
import com.edurda77.resources.theme.lightGrey
import com.edurda77.resources.theme.white
import kotlinx.datetime.LocalDate

@Composable
fun ItemCourse(
    modifier: Modifier = Modifier,
    course: Course,
    isFavorite: Boolean,
    onClickUpdateFavorite: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = darkGrey
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = modifier
                        .height(35.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(lightGrey)
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        tint = green
                    )
                    Text(
                        text = course.rate,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = white
                    )
                }
                Spacer(modifier = modifier.width(4.dp))
                Row(
                    modifier = modifier
                        .height(35.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(lightGrey)
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = course.publishDate.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = white
                    )
                }
                Spacer(modifier = modifier.weight(1f))
                UiIconButton(
                    icon = if (isFavorite) ImageVector.vectorResource(R.drawable.baseline_bookmark_24)
                    else ImageVector.vectorResource(R.drawable.outline_bookmark_24),
                    onClick = onClickUpdateFavorite,
                    buttonColor = lightGrey,
                    color = if (isFavorite) green else white
                )
            }
            Spacer(modifier = modifier.height(15.dp))
            Text(
                text = course.title,
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = white
            )
            Spacer(modifier = modifier.height(15.dp))
            Text(
                text = course.text,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(400),
                color = white
            )
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${course.price} ${stringResource(R.string.unit_rub)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = white
                )
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.more),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = green
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_right_short_fill),
                        contentDescription = "",
                        tint = green
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemCourseView() {
    ItemCourse(
        course = Course(
            id = 1,
            price = "999",
            title = "Java-разработчик с нуля",
            rate = "4.9",
            startDate = LocalDate.parse("2024-05-22"),
            publishDate = LocalDate.parse("2024-02-02"),
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        ),
        isFavorite = true,
        onClickUpdateFavorite = {}
    )
}