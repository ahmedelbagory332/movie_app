package com.example.presentation.ui.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.MovieItem
import com.example.presentation.utiles.Constant

@Composable
fun MovieItem(movie: MovieItem) {

    Column {
        CoilImage(
            data = Constant.imageBaseUrl + movie.posterUrl,
            contentDescription = movie.name,
            modifier = Modifier,
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = movie.name,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )
    }
}