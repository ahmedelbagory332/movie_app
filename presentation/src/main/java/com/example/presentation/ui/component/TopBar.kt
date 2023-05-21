package com.example.presentation.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String,
    menu: @Composable() (() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            menu()
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}