package com.edurda77.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edurda77.domain.utils.TABLE_COURSE
import com.edurda77.domain.utils.TB_COURSE_ID
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    //LocationEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(courseEntity: CourseEntity)

    @Query("SELECT * FROM $TABLE_COURSE")
    fun getAllCourses(): Flow<List<CourseEntity>>


    @Query("DELETE FROM $TABLE_COURSE WHERE $TB_COURSE_ID=:id")
    suspend fun deleteCourseById(id: Int)
}