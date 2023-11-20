package com.example.animeapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animeapp.repository.AnimeRepository
import com.example.animeapp.ui.theme.screen.AnimeViewModel
import com.example.animeapp.ui.theme.screen.DetailViewModel

class ViewModelFactory(private val repository: AnimeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            return AnimeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}