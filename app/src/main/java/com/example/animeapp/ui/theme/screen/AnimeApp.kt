package com.example.animeapp.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animeapp.repository.AnimeRepository
import com.example.animeapp.components.AnimeItem
import com.example.animeapp.components.SearchBar
import com.example.animeapp.navigation.AppNavigation
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.animeapp.ui.theme.Brown
import com.example.animeapp.ui.theme.ViewModelFactory
import com.example.animeapp.ui.theme.orangelight

@Composable
fun AnimeApp(
    viewModel: AnimeViewModel = viewModel(factory = ViewModelFactory(AnimeRepository()))
) {
    val groupedAnimes by viewModel.groupedAnimes.collectAsState()
    val query by viewModel.query
    val navController = rememberNavController()

    Column {
        AppNavigation(navController) // Integrasi AppNavigation di sini
        SearchBar(
            query = query,
            onQueryChange = viewModel::search,
            modifier = Modifier
                .background(Brown)
                .fillMaxWidth()
        )
        if (groupedAnimes.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(Brown)
            ) {
                groupedAnimes.forEach { (_, animes) ->
                    items(animes) { data ->
                        AnimeItem(
                            name = data.name,
                            photoUrl = data.photoUrl,
                            year = data.year,
                            animeId = data.id,
                            navController
                        )
                    }
                }
            }
        } else {
            // Tampilkan pesan kosong atau indikator loading sesuai kebutuhan
            Text(text = "No results found", modifier = Modifier.padding(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "AnimeApp", color = orangelight)
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("profile")
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "About", tint = orangelight)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Brown
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AnimeAppPreview() {
    AnimeAppTheme {
        AnimeApp()
    }
}
