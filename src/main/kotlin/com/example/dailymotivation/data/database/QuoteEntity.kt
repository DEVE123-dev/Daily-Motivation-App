package com.example.dailymotivation.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey val id: Int,
    val text: String,
    val author: String,
    val isFavorite: Boolean = false
)
