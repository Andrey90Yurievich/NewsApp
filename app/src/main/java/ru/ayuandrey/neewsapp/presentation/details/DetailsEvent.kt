package ru.ayuandrey.neewsapp.presentation.details

import ru.ayuandrey.neewsapp.domain.model.Article

<<<<<<< HEAD
sealed class DetailsEvent {                                                    //
=======
sealed class DetailsEvent {
>>>>>>> origin/lesson7
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()    //Обновить и удалить статью

    object RemoveSideEffect : DetailsEvent()                                //Устранить побочный эффект

}