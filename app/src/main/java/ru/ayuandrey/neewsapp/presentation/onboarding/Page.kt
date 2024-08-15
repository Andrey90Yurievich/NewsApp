package ru.ayuandrey.neewsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import ru.ayuandrey.neewsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int //значение - целое число, будет ссылкой на ресурс для рисования
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum is simp",
        description = "Я его рот",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simp",
        description = "Я его рот",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simp",
        description = "Я его рот",
        image = R.drawable.onboarding3
    ),
)
