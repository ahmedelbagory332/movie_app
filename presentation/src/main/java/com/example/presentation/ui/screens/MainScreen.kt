package com.example.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.GenreItemModel
import com.example.presentation.R
import com.example.presentation.theme.darkWhite
import com.example.presentation.ui.component.TopBar
import com.example.presentation.ui.viewModels.GenreViewModel
import com.example.presentation.ui.viewModels.MoviesViewModel
import com.example.presentation.utiles.Routes
import com.example.presentation.ui.widget.GenreList
import com.example.presentation.ui.widget.MovieList
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

   Scaffold(
       snackbarHost = { SnackbarHost(snackbarHostState) },
               topBar = {
           TopBar(
               title = "Home Screen",
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
       Column(
           modifier = Modifier
               .background(darkWhite)
               .fillMaxWidth()
               .fillMaxHeight()
       ) {


           GenreList(
               onMovieLongClick = { movieName ->
                   Log.d("TAG", "MainScreenbego: $movieName")
                   coroutineScope.launch {
                       snackbarHostState.showSnackbar(message = movieName,)
               }
               }, onMovieClick = {movieId->
                   mainNavController.navigate(Routes().detailsRoute+ "/${movieId}")
               }
           )


       }
   }


}