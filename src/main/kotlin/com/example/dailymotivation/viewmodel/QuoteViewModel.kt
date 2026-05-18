package com.example.dailymotivation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dailymotivation.data.model.Quote
import com.example.dailymotivation.data.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _currentQuote = MutableStateFlow<Quote?>(null)
    val currentQuote: StateFlow<Quote?> = _currentQuote.asStateFlow()

    private val _favorites = MutableStateFlow<List<Quote>>(emptyList())
    val favorites: StateFlow<List<Quote>> = _favorites.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    init {
        loadTodayQuote()
        loadFavorites()
    }

    private fun loadTodayQuote() {
        viewModelScope.launch {
            val allQuotes = repository.getAllQuotes()
            if (allQuotes.isNotEmpty()) {
                val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
                val index = dayOfYear % allQuotes.size
                val quote = allQuotes[index]
                _currentQuote.value = quote
                _isFavorite.value = repository.isFavorite(quote.id)
            }
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavorites().collect { favorites ->
                _favorites.value = favorites
            }
        }
    }

    fun toggleFavorite(quote: Quote) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.removeFavorite(quote.id)
                _isFavorite.value = false
            } else {
                repository.addFavorite(quote)
                _isFavorite.value = true
            }
        }
    }

    fun removeFavorite(quote: Quote) {
        viewModelScope.launch {
            repository.removeFavorite(quote.id)
            if (_currentQuote.value?.id == quote.id) {
                _isFavorite.value = false
            }
        }
    }
}

class QuoteViewModelFactory(private val repository: QuoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
