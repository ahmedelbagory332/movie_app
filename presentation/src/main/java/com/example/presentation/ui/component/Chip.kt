package com.example.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.GenreItemModel

@Composable
fun Chip(
    genre: GenreItemModel,
    selected: Boolean = false,
    onSelected: ((movie: GenreItemModel) -> Unit),
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp),
) {
    Surface(
        modifier = modifier.clickable {
            onSelected(genre)
        }.padding(contentPadding),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        elevation = if (selected) 5.dp else 0.dp
    ) {
        Text(
            modifier = modifier.padding(contentPadding),
            text = genre.name,
            style = MaterialTheme.typography.body2,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
        Divider()
    }
}