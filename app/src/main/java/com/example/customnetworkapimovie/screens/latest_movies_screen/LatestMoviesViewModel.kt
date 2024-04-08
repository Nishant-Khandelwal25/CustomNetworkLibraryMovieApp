package com.example.customnetworkapimovie.screens.latest_movies_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.customnetworkapimovie.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LatestMoviesViewModel @Inject constructor(
    repository: MainRepository
) : ViewModel() {
    val pagingData = repository.getLatestMoviesData().cachedIn(viewModelScope)
}