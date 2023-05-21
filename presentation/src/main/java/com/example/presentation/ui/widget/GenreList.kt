package com.example.presentation.ui.widget


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.GenreItemModel
import com.example.presentation.R
import com.example.presentation.ui.component.Chip
import com.example.presentation.ui.component.ErrorHolder
import com.example.presentation.ui.component.LoadingIndicator
import com.example.presentation.ui.component.PlaceHolder
import com.example.presentation.ui.viewModels.GenreViewModel
import com.example.presentation.ui.viewModels.MoviesViewModel
import java.util.*


@SuppressLint("UnrememberedMutableState")
@Composable
fun GenreList(
    genreViewModel: GenreViewModel = hiltViewModel(),
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    onMovieClick: (movieId: String) -> Unit,
    onMovieLongClick: (movieId: String) -> Unit,

    ) {


    if (genreViewModel.genres.value.isLoading) {
        LoadingIndicator()
    }
    else if (genreViewModel.genres.value.genre.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(PaddingValues(8.dp)),
            text = "Movie Category : ",
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(genreViewModel.genres.value.genre) { genre ->
                Chip(
                    genre = genre,
                    selected = genreViewModel.selectedGenre.value == genre,
                    onSelected = {
                        if (genreViewModel.selectedGenre.value == genre)
                            genreViewModel.setSelectedGenre(GenreItemModel())
                        else
                            genreViewModel.setSelectedGenre(genre)

                    },
                    modifier = Modifier
                )
            }
        }
        if (genreViewModel.selectedGenre.value.name.isNotEmpty()) {
            moviesViewModel.getMovies(Locale.getDefault().language,genreViewModel.selectedGenre.value.id.toString())
            MovieList(false, onMovieLongClick = { movieName ->
                onMovieLongClick(movieName)
            }, onMovieClick = { movieId ->
                onMovieClick(movieId)
            })
        } else {
            PlaceHolder(
                text = "Please select category",
                painter = painterResource(id = R.drawable.select)
            )
        }

    }
    else if (genreViewModel.genres.value.error.isNotEmpty()) {
        ErrorHolder(text = moviesViewModel.movies.value.error)
    }

}