package ru.ayuandrey.neewsapp.presentation.bookmark

import ru.ayuandrey.neewsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)