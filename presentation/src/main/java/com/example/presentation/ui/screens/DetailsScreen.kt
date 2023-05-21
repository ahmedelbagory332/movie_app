package com.example.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.GenreItemModel
import com.example.presentation.R
import com.example.presentation.theme.darkWhite
import com.example.presentation.ui.component.CoilImage
import com.example.presentation.ui.component.ErrorHolder
import com.example.presentation.ui.component.LoadingIndicator
import com.example.presentation.ui.component.TopBar
import com.example.presentation.ui.viewModels.MovieDetailsViewModel
import com.example.presentation.ui.viewModels.MoviesViewModel
import com.example.presentation.utiles.Constant
import com.example.presentation.utiles.Routes
import com.example.presentation.ui.widget.MovieList
import java.util.*


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    mainNavController: NavHostController,
    movieId: String?,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    movieDetailsViewModel.getMovies(Locale.getDefault().language, movieId ?: "")
    Scaffold(
        topBar = {
            TopBar(
                title = "Details Screen",
                menu = {
                    IconButton(onClick = {
                        mainNavController.navigate(Routes().searchRoute)

                    }) {
                        Icon(Icons.Filled.Search, "search")
                    }
                }
            )
        }
    ) {

        if (movieDetailsViewModel.movie.value.isLoading) {
            LoadingIndicator()
        }
        else if (movieDetailsViewModel.movie.value.error.isNotEmpty()) {
            ErrorHolder(text = movieDetailsViewModel.movie.value.error)
        } else  {
            val movie = movieDetailsViewModel.movie.value.movie
             Column(
                 modifier = Modifier.verticalScroll(rememberScrollState())
              ) {

                CoilImage(
                    data = Constant.imageBaseUrl + movie.posterUrl,
                    contentDescription = movie.name?:"",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie.name?:"",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.tagline?:"",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.secondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.release_date, movie.releaseDate?:""),
                        style = MaterialTheme.typography.subtitle2
                    )
                    Text(
                        text = stringResource(R.string.duration, movie.runtime?:""),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie.overview?:"",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.genres,
                        movie.genres?.joinToString { it.name } ?: listOf("") ),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(
                        R.string.production_companies,
                        movie.productionCompanies?.joinToString { it.name } ?: listOf("") ),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
             }

        }




    }


}