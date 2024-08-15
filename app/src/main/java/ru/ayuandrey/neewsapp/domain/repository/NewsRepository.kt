package ru.ayuandrey.neewsapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article


interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?

}