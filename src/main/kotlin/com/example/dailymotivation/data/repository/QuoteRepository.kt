package com.example.dailymotivation.data.repository

import com.example.dailymotivation.data.database.QuoteDao
import com.example.dailymotivation.data.database.QuoteEntity
import com.example.dailymotivation.data.model.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteRepository(private val quoteDao: QuoteDao) {

    private val seedQuotes = listOf(
        Quote(1, "The only way to do great work is to love what you do.", "Steve Jobs"),
        Quote(2, "Innovation distinguishes between a leader and a follower.", "Steve Jobs"),
        Quote(3, "Life is what happens when you're busy making other plans.", "John Lennon"),
        Quote(4, "The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
        Quote(5, "It is during our darkest moments that we must focus to see the light.", "Aristotle"),
        Quote(6, "The only impossible journey is the one you never begin.", "Tony Robbins"),
        Quote(7, "Success is not final, failure is not fatal.", "Winston Churchill"),
        Quote(8, "Believe you can and you're halfway there.", "Theodore Roosevelt"),
        Quote(9, "The best time to plant a tree was 20 years ago. The second best time is now.", "Chinese Proverb"),
        Quote(10, "Your time is limited, don't waste it living someone else's life.", "Steve Jobs")
    )

    init {
        initializeSeedData()
    }

    private fun initializeSeedData() {
        // Initialize database with seed quotes
        seedQuotes.forEach { quote ->
            try {
                quoteDao.insertQuote(
                    QuoteEntity(
                        id = quote.id,
                        text = quote.text,
                        author = quote.author,
                        isFavorite = false
                    )
                )
            } catch (e: Exception) {
                // Quote already exists
            }
        }
    }

    suspend fun getAllQuotes(): List<Quote> {
        return quoteDao.getAllQuotes().map {
            Quote(
                id = it.id,
                text = it.text,
                author = it.author,
                isFavorite = it.isFavorite
            )
        }
    }

    fun getFavorites(): Flow<List<Quote>> {
        return quoteDao.getFavorites().map { entities ->
            entities.map {
                Quote(
                    id = it.id,
                    text = it.text,
                    author = it.author,
                    isFavorite = it.isFavorite
                )
            }
        }
    }

    suspend fun isFavorite(id: Int): Boolean {
        return quoteDao.isFavorite(id)
    }

    suspend fun addFavorite(quote: Quote) {
        quoteDao.addFavorite(quote.id)
    }

    suspend fun removeFavorite(id: Int) {
        quoteDao.removeFavorite(id)
    }
}
