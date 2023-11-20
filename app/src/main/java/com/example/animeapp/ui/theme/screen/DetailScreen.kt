package com.example.animeapp.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.animeapp.repository.AnimeRepository
import com.example.animeapp.ui.theme.Brown
import com.example.animeapp.ui.theme.ViewModelFactory
import com.example.animeapp.ui.theme.orangelight

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(AnimeRepository())),
    animeId: String
) {
    val animeDetail by viewModel.animeDetail.collectAsState()

    LaunchedEffect(animeId) {
        viewModel.getAnimeDetail(animeId)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brown)
            .verticalScroll(rememberScrollState())
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = orangelight,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.TopStart)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            Card (
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = orangelight,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ){
                AsyncImage(
                    model = animeDetail?.photoUrl,
                    contentDescription = "anime_image",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(200.dp)
                        .border(3.dp, Brown, CircleShape)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = animeDetail?.name.orEmpty(),
                    color = Brown,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = animeDetail?.year.orEmpty(),
                    color = Brown,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Text(
                text = animeDetail?.description.orEmpty(),
                color = orangelight,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
