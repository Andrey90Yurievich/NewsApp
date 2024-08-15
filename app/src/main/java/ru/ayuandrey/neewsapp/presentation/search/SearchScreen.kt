package ru.ayuandrey.neewsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.presentation.Dimens.MediumPadding1
import ru.ayuandrey.neewsapp.presentation.common.ArticlesList
import ru.ayuandrey.neewsapp.presentation.common.SearchBar

@Composable
fun SearchScreen(                                                                       //экран поиска
    state: SearchState,                                                                  //принимает состояние поиска из вю модели
    event:(SearchEvent) -> Unit,                                                          //событие - поискове событие
    navigateToDetails:(Article) -> Unit                                                   //нвигация на детали
) {

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()                                                            //пространство для статус бара
    ) {
        SearchBar(                                                                          //бар поиска
            text = state.searchQuery,                                                       //принимает введенный запрос пользователем
            readOnly = false,                                                                //
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },                    //поисковый запрос куда то идет
            onSearch = {                                                                      //ищет новости при нажатии на клавиатуре чтоли
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {                                             //состояние из вью модели/ если поток статей не null
            val articles = it.collectAsLazyPagingItems()  //получит доступ к потоку             //Для получения экземпляра Класс, отвечающий за доступ к данным из Flow - LazyPagingItems
                                                                                                // используйте collectAsLazyPagingItems метод расширения Flow
            ArticlesList(                                                                        //Список статей
                articles = articles,                                                              //принимает из потока выше
                onClick = navigateToDetails                                                        //по клику на статью навигация на детали статьи на экран деталей/ по текущей заметке
            )
        }
    }
}