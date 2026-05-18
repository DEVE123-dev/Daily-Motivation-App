package com.example.dailymotivation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dailymotivation.data.database.QuoteDatabase
import com.example.dailymotivation.data.repository.QuoteRepository
import com.example.dailymotivation.ui.screens.FavoritesScreen
import com.example.dailymotivation.ui.screens.HomeScreen
import com.example.dailymotivation.ui.theme.DailyMotivationTheme
import com.example.dailymotivation.viewmodel.QuoteViewModel
import com.example.dailymotivation.viewmodel.QuoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyMotivationTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val database = QuoteDatabase.getDatabase(androidx.compose.runtime.remember { android.content.Context::class })
    val repository = QuoteRepository(database.quoteDao())
    val viewModel: QuoteViewModel = viewModel(
        factory = QuoteViewModelFactory(repository)
    )

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") }
                )
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> HomeScreen(viewModel, paddingValues)
            1 -> FavoritesScreen(viewModel, paddingValues)
        }
    }
}
