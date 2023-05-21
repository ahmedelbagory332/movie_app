package com.example.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.GenreItemModel
import com.example.presentation.R
import com.example.presentation.theme.darkWhite
import com.example.presentation.ui.component.PlaceHolder
import com.example.presentation.ui.component.TopBar
import com.example.presentation.ui.viewModels.GenreViewModel
import com.example.presentation.ui.viewModels.MovieSearchViewModel
import com.example.presentation.ui.viewModels.MoviesViewModel
import com.example.presentation.utiles.Routes
import com.example.presentation.ui.widget.GenreList
import com.example.presentation.ui.widget.MovieList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    mainNavController: NavHostController,
    movieSearchViewModel: MovieSearchViewModel = hiltViewModel(),
    ) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
     Scaffold(
                topBar = {
           TopBar(
               title = "Search Screen",

           )
       }
   ) {
       Column(
           modifier = Modifier
               .background(darkWhite)
               .fillMaxWidth()
               .fillMaxHeight()
       ) {

           LaunchedEffect(Unit) {
               delay(200)
               focusRequester.requestFocus()
           }
           OutlinedTextField(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(5.dp)
                   .focusRequester(focusRequester),
               value = movieSearchViewModel.query.value,
               onValueChange = { movieSearchViewModel.query.value = it },
               label = { Text("Enter movie name") },
               leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
               keyboardOptions = KeyboardOptions(
                   imeAction = ImeAction.Search
               ),
               keyboardActions = KeyboardActions(
                   onSearch = {
                       focusManager.clearFocus()
                       movieSearchViewModel.showPlaceHolder.value = false
                       movieSearchViewModel.getSearchMovie(language = Locale.getDefault().language, movieName = movieSearchViewModel.query.value)
                       Log.d("TAG", "MainScreenbego: ${movieSearchViewModel.query.value}")

                   }

               )
           )
           if( movieSearchViewModel.showPlaceHolder.value) {
               PlaceHolder(
                   text = "Search on movies",
                   painter = painterResource(id = R.drawable.search)
               )
           }else{
               Box {}
           }

           MovieList(true, onMovieLongClick = { movieName ->
               Log.d("TAG", "MainScreenbego: $movieName")
               coroutineScope.launch {
                   snackbarHostState.showSnackbar(message = movieName,)
               }
           }, onMovieClick = {movieId->
               mainNavController.navigate(Routes().detailsRoute+ "/${movieId}")
           })



       }
   }


}