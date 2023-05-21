package com.example.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.screens.DetailsScreen
import com.example.presentation.ui.screens.MainScreen
import com.example.presentation.ui.screens.SearchScreen
import com.example.presentation.utiles.Routes
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun App() {
     val mainNavController = rememberNavController()


        NavHost(
            navController = mainNavController,
            startDestination = Routes().mainRoute
        ) {

            composable(route = Routes().mainRoute) {
                MainScreen( mainNavController)
            }

            composable(route = Routes().detailsRoute + "/{movieId}") {
                val movieId = it.arguments?.getString("movieId")
                DetailsScreen(mainNavController,movieId)
            }

            composable(route = Routes().searchRoute) {
                SearchScreen(mainNavController)
            }



        }


}




