package com.example.customnetworkapimovie.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customnetworkapimovie.util.Result
import com.example.customnetworkapimovie.repository.MainRepository
import com.example.customnetworkapimovie.util.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: MainRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY) ?: 0
        viewModelScope.launch {
            repository.getMovieDetailsData(heroId).collectLatest {
                when (it) {
                    is Result.Success -> {
                        _uiState.update { state ->
                            state.copy(movieDetailsData = it.data)
                        }
                    }

                    is Result.Error -> {
                        _uiState.update { state ->
                            state.copy(error = it.exception)
                        }
                    }
                }
            }
        }
    }
}