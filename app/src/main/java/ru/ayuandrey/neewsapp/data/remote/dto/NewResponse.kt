package ru.ayuandrey.neewsapp.data.remote.dto

import ru.ayuandrey.neewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)