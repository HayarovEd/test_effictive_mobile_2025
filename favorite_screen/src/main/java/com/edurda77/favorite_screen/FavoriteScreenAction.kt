package com.edurda77.favorite_screen


sealed interface FavoriteScreenAction {
    class DeleteFromFavorite(val id: Int): FavoriteScreenAction
}