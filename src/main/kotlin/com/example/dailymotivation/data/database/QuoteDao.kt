package com.example.dailymotivation.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    suspend fun getAllQuotes(): List<QuoteEntity>

    @Query("SELECT * FROM quotes WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<QuoteEntity>>

    @Query("SELECT isFavorite FROM quotes WHERE id = :id")
    suspend fun isFavorite(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("UPDATE quotes SET isFavorite = 1 WHERE id = :id")
    suspend fun addFavorite(id: Int)

    @Query("UPDATE quotes SET isFavorite = 0 WHERE id = :id")
    suspend fun removeFavorite(id: Int)
}
