package com.edurda77.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        CourseEntity::class,],
    version = 1
)
abstract class CourseDataBase : RoomDatabase() {
    abstract val courseDao: CourseDao
}