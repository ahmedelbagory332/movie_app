package com.example.presentation.ui.widget


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.GenreItemModel
import com.example.presentation.R
import com.example.presentation.theme.darkWhite
import com.example.presentation.ui.viewModels.MovieSearchViewModel
import com.example.presentation.ui.viewModels.MoviesViewModel
import com.example.presentation.utiles.Constant


@SuppressLint("UnrememberedMutableState")
@Composable
fun MovieList(
    isSearch: Boolean = false,
    onMovieClick: (movieId: String) -> Unit,
    onMovieLongClick: (movieId: String) -> Unit,
) {


    Column(
        modifier = Modifier
            .background(darkWhite)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {


        if (isSearch) {
            val viewModel = hiltViewModel<MovieSearchViewModel>()

            SearchMovieList(viewModel, onMovieLongClick, onMovieClick)


        } else {
            val viewModel = hiltViewModel<MoviesViewModel>()
            MainMovieList(viewModel, onMovieLongClick, onMovieClick)
        }


    }

}


