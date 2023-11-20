package com.example.animeapp.ui.theme.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.animeapp.repository.AnimeRepository
import com.example.animeapp.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _groupedAnimes = MutableStateFlow(
        repository.getAnime()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedAnimes: MutableStateFlow<Map<Char, List<Anime>>> get() = _groupedAnimes
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedAnimes.value = repository.searchAnime(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}
