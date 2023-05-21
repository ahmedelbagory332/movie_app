package com.example.presentation.ui.component



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.R


@Composable
fun ErrorHolder(
    text:String,
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = "My Image",
            modifier = Modifier.size(width = 250.dp, height = 250.dp)
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(
            modifier = Modifier.padding(PaddingValues(8.dp)),
            text = text,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold
        )
    }
}
