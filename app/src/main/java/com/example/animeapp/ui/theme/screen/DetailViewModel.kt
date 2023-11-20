package com.example.animeapp.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.repository.AnimeRepository
import com.example.animeapp.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _animeDetail = MutableStateFlow<Anime?>(null)
    val animeDetail: StateFlow<Anime?> = _animeDetail

    fun getAnimeDetail(animeId: String) {
        viewModelScope.launch {
            val detail = repository.getAnimeDetail(animeId)
            _animeDetail.value = detail

        }
    }

}