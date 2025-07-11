package com.edurda77.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.repository.LocalRepository
import com.edurda77.domain.utils.ResultWork
import com.edurda77.resources.uikit.asUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(
    private val localRepository: LocalRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteScreenState())
    val state = _state
        .onStart {
            loadLocalData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = FavoriteScreenState()
        )

    fun onAction(action: FavoriteScreenAction) {
        when (action) {
            is FavoriteScreenAction.DeleteFromFavorite -> {
                viewModelScope.launch {
                    localRepository.deleteCourse(action.id)
                }
            }
        }
    }

    private fun loadLocalData() {
        viewModelScope.launch {
            localRepository.getAllCourses().collect { collector ->
                when (collector) {
                    is ResultWork.Error -> {
                        _state.value.copy(
                            message = collector.error.asUiText()
                        )
                            .updateState()
                    }

                    is ResultWork.Success -> {
                        _state.value.copy(
                            favoriteCourses = collector.data
                        )
                            .updateState()
                    }
                }
            }
        }
    }

    private fun FavoriteScreenState.updateState() {
        _state.update {
            this
        }
    }
}