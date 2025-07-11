package com.edurda77.test_effictive_mobile_2025.di


import androidx.room.Room
import com.edurda77.data.local.CourseDataBase
import com.edurda77.data.remote.TestApi
import com.edurda77.domain.utils.BASE_URL
import com.edurda77.domain.utils.COURSE_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val baseModule = module {

    single<TestApi> {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestApi::class.java)
    }

    single<CourseDataBase> {
        Room.databaseBuilder(
            androidContext(),
            CourseDataBase::class.java,
            COURSE_DB
        )
            .build()
    }
}