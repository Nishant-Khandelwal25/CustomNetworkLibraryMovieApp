package com.example.customnetworkapimovie.screens.popular_movies_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.customnetworkapimovie.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    repository: MainRepository
) : ViewModel() {
    val pagingData = repository.getPopularMoviesData().cachedIn(viewModelScope)
}