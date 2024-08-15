package ru.ayuandrey.neewsapp.domain.usercase.news

import ru.ayuandrey.neewsapp.data.local.NewsDao
import ru.ayuandrey.neewsapp.domain.model.Article
import javax.inject.Inject

class GetSavedArticle @Inject constructor(              //получить сохраненую статью
    private val newsDao: NewsDao                        //принимает объект интерфейса дао
) {

    suspend operator fun invoke(url: String): Article?{   //функция приостановки \ принимает урл
        return newsDao.getArticle(url = url)               //возвращает / применяет функцию  getArticle дао интерфейса/ отправляет запрос и получает ответ типа Article
    }

}