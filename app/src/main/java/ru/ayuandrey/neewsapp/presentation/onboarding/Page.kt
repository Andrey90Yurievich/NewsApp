package ru.ayuandrey.neewsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import ru.ayuandrey.neewsapp.R

data class Page(                              //класс для хранения данных - страница
    val title: String,                        //принимает свойство заголовок типа строки
    val description: String,                  //описание типа строки
    @DrawableRes val image: Int,               //изображение из ресурсов/ возвращаемое значение целочисленного параметра, поля или метода будет ссылкой на доступный для рисования ресурс
)

val pages = listOf(                             //список из объектов класса Page
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)