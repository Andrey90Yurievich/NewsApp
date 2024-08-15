package ru.ayuandrey.neewsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import ru.ayuandrey.neewsapp.domain.model.Source
   //позволяет разработчикам легко сериализовывать и десериализовывать объекты для использования в приложениях Android
// Эта аннотация упрощает процесс передачи данных между действиями, фрагментами и другими компонентами приложения для Android.
@Parcelize //Чтобы сделать класс данных доступным для парцелляции, вам нужно пометить его аннотацией @Parcelize .
//Эта аннотация генерирует необходимый для вас код.
@Entity
data class Article(                 //класс данных
    val author: String,              //автор
    val content: String,              //контент
    val description: String,           //описание
    val publishedAt: String,           //публикаия
    val source: Source,                  //источник
    val title: String,                  //заголовок
    @PrimaryKey val url: String,        //ключ
    val urlToImage: String               //адрес картинки
) : Parcelable                            //расширяет интерфейс