package ru.ayuandrey.neewsapp.presentation.details

import ru.ayuandrey.neewsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()    //Обновить и удалить статью

    object RemoveSideEffect : DetailsEvent()                                //Устранить побочный эффект

}