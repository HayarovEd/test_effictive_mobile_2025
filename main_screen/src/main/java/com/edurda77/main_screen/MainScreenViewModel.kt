package com.edurda77.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.model.Course
import com.edurda77.domain.repository.LocalRepository
import com.edurda77.domain.repository.RemoteRepository
import com.edurda77.domain.utils.ResultWork
import com.edurda77.resources.uikit.asUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state
        .onStart {
            loadLocalData()
            loadRemoteData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MainScreenState()
        )

    fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.UpdateFavorite -> {
                updateFavorite(action.course)
            }

            MainScreenAction.SortByPublishDate -> {
                _state.value.copy(
                    remoteCourses = state.value.remoteCourses.sortedByDescending { it.publishDate }
                )
                    .updateState()
            }

            is MainScreenAction.UpdateSearch -> {
                _state.value.copy(
                    query = action.query
                )
                    .updateState()
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

    private fun loadRemoteData() {
        _state.value.copy(
            isLoading = true
        )
            .updateState()
        viewModelScope.launch {
            when (val result = remoteRepository.getCourses()) {
                is ResultWork.Error -> {
                    _state.value.copy(
                        message = result.error.asUiText(),
                        isLoading = false
                    )
                        .updateState()
                }

                is ResultWork.Success -> {
                    _state.value.copy(
                        remoteCourses = result.data,
                        isLoading = false
                    )
                        .updateState()
                }
            }
        }
    }


    private fun updateFavorite(course: Course) {
        viewModelScope.launch {
            if (state.value.favoriteCourses.contains(course)) {
                localRepository.deleteCourse(course.id)
            } else {
                localRepository.insertCourse(course)
            }
        }
    }

    private fun MainScreenState.updateState() {
        _state.update {
            this
        }
    }
}