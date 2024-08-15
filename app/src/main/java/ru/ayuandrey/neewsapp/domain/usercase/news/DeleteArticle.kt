package ru.ayuandrey.neewsapp.domain.usercase.news

import ru.ayuandrey.neewsapp.data.local.NewsDao
import ru.ayuandrey.neewsapp.domain.model.Article
import javax.inject.Inject

class DeleteArticle @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.delete(article = article)
    }

}