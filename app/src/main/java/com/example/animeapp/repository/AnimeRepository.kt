package com.example.animeapp.repository

import com.example.animeapp.model.Anime
import com.example.animeapp.model.AnimeData

class AnimeRepository {

    fun getAnime(): List<Anime> {
        return AnimeData.animes
    }

    fun searchAnime(query: String): List<Anime> {
        return AnimeData.animes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getAnimeDetail(animeId: String): Anime {
        return AnimeData.animes.find { it.id == animeId }?.toDetail()
            ?: throw NoSuchElementException("Anime not found")
    }

    private fun Anime.toDetail(): Anime {
        return Anime(id, name, photoUrl, year, description)
    }
}