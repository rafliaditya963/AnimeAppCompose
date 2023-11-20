package com.example.animeapp.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.animeapp.ui.theme.Brown
import com.example.animeapp.ui.theme.orangelight

@Composable
fun AnimeItem(
    name: String,
    photoUrl: String,
    year: String,
    animeId: String,
    navController: NavController
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = orangelight,
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                navController.navigate("detail/$animeId")
            },
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "gambar anime",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .border(2.dp, Brown, CircleShape)
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Brown
                )
                Text(
                    text = year,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic
                    ),
                    color = Brown
                )

            }
        }
    }
}
