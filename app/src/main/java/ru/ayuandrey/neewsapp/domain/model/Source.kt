package ru.ayuandrey.neewsapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize         //Эта аннотация генерирует необходимый для вас код.
//вы можете передавать объект между действиями или фрагментами, используя метод putExtra
data class Source(                  //источники
    val id: String,                 //фйди
    val name: String                 //имя
) : Parcelable //наследуется чтоли от него расширяет интерфейс