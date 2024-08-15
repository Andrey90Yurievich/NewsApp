package ru.ayuandrey.neewsapp.presentation.home

data class HomeState(                //состояние домашней страницы
    val newsTicker: String = "",     //объект с пустой строкой   - бегущая строка новостей
    val isLoading: Boolean = false,   //загружается
    val scrollValue: Int = 0,         //значение прокрутки
    val maxScrollingValue: Int = 0    //максимальное значение прокрутки
)