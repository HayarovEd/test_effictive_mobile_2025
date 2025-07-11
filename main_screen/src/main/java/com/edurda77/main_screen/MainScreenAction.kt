package com.edurda77.main_screen

import com.edurda77.domain.model.Course

sealed interface MainScreenAction {
    class UpdateSearch(val query: String): MainScreenAction
    class UpdateFavorite(val course: Course): MainScreenAction
    data object SortByPublishDate: MainScreenAction
}