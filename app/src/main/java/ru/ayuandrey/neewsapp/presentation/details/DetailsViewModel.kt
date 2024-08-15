package ru.ayuandrey.neewsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.domain.usercase.news.DeleteArticle
import ru.ayuandrey.neewsapp.domain.usercase.news.GetSavedArticle
import ru.ayuandrey.neewsapp.domain.usercase.news.UpsertArticle
import ru.ayuandrey.neewsapp.util.UIComponent
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedArticle,                        //свойство в конструктор - получить сохраненный статья /
    private val deleteArticleUseCase: DeleteArticle,                            //удалить статью
    private val upsertArticleUseCase: UpsertArticle                             //заменить статью
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)                  //состояние
        private set

    fun onEvent(event: DetailsEvent) {                                              //событие
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {                                 //Обновить и удалить статью
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(url = event.article.url)      //
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        deleteArticleUseCase(article = article)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        upsertArticleUseCase(article = article)
        sideEffect = UIComponent.Toast("Article Inserted")
    }

}