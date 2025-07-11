package com.edurda77.test_effictive_mobile_2025.di


import com.edurda77.favorite_screen.FavoriteScreenViewModel
import com.edurda77.login_screen.LoginScreenViewModel
import com.edurda77.main_screen.MainScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::FavoriteScreenViewModel)
}