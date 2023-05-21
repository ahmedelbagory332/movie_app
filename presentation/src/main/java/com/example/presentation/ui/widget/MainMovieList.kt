package com.example.presentation.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.ui.component.ErrorHolder
import com.example.presentation.ui.component.LoadingIndicator
import com.example.presentation.ui.component.MovieItem
import com.example.presentation.ui.viewModels.MoviesViewModel

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MainMovieList(
    viewModel: MoviesViewModel,
    onMovieLongClick: (movieId: String) -> Unit,
    onMovieClick: (movieId: String) -> Unit
) {


    val movies = viewModel.movies.value.movies!!.flow.collectAsLazyPagingItems()

    if (viewModel.movies.value.isLoading) {
        LoadingIndicator()
    }  else if (viewModel.movies.value.error.isNotEmpty()) {
        ErrorHolder(text = viewModel.movies.value.error)
    }else   {

        LazyVerticalGrid(
             columns = GridCells.Fixed(2),
            content = {
                items(movies.itemCount) { index ->
                    Card(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize()
                            .clickable {

                            }
                            .combinedClickable(
                                onLongClick = { onMovieLongClick(movies[index]!!.name) },
                                onClick = { onMovieClick(movies[index]!!.id.toString()) }),
                        elevation = 15.dp,
                    ) {

                        MovieItem(movies[index]!!)
                    }
                }
            }
        )

    }
}
