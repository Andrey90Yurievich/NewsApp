package ru.ayuandrey.neewsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ayuandrey.neewsapp.domain.model.Source

@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
)