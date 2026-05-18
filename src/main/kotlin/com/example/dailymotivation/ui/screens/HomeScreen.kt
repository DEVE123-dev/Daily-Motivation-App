package com.example.dailymotivation.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailymotivation.viewmodel.QuoteViewModel

@Composable
fun HomeScreen(viewModel: QuoteViewModel, paddingValues: PaddingValues) {
    val currentQuote by viewModel.currentQuote.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (currentQuote != null) {
                Text(
                    text = "\"${currentQuote!!.text}\"",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "— ${currentQuote!!.author}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 48.dp)
                )

                // Action Buttons
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Favorite Button
                    IconButton(
                        onClick = { viewModel.toggleFavorite(currentQuote!!) }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Toggle Favorite",
                            tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Share Button
                    Button(
                        onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "\"${currentQuote!!.text}\" — ${currentQuote!!.author}")
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Quote"))
                        },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                        Text("Share Quote")
                    }
                }
            } else {
                Text("Loading quote...")
            }
        }
    }
}
