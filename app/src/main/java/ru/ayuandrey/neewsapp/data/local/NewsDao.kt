package ru.ayuandrey.neewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article

@Dao
interface NewsDao {                                     //дао интерфейс

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)                //удалчется из таблицы чтоли

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")       //запрос к SQL таблице на языке sql - где :url така встречается запись
    suspend fun getArticle(url: String): Article?        //получить статью / принимает запрос возврашает отъект типа Article/ может быть налл

}