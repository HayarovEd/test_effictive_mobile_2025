package com.edurda77.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edurda77.domain.utils.TABLE_COURSE
import com.edurda77.domain.utils.TB_COURSE_ID
import com.edurda77.domain.utils.TB_COURSE_PRICE
import com.edurda77.domain.utils.TB_COURSE_PUBLISH_DATE
import com.edurda77.domain.utils.TB_COURSE_RATE
import com.edurda77.domain.utils.TB_COURSE_START_DATE
import com.edurda77.domain.utils.TB_COURSE_TEXT
import com.edurda77.domain.utils.TB_COURSE_TITLE

@Entity(tableName = TABLE_COURSE)
data class CourseEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = TB_COURSE_ID)
    val id: Int,
    @ColumnInfo(name = TB_COURSE_PRICE)
    val price: String,
    @ColumnInfo(name = TB_COURSE_PUBLISH_DATE)
    val publishDate: String,
    @ColumnInfo(name = TB_COURSE_RATE)
    val rate: String,
    @ColumnInfo(name = TB_COURSE_START_DATE)
    val startDate: String,
    @ColumnInfo(name = TB_COURSE_TEXT)
    val text: String,
    @ColumnInfo(name = TB_COURSE_TITLE)
    val title: String
)