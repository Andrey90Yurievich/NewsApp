package ru.ayuandrey.neewsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.presentation.Dimens.ExtraSmallPadding2
import ru.ayuandrey.neewsapp.presentation.Dimens.MediumPadding1
import ru.ayuandrey.neewsapp.presentation.home.components.ArticleCard

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = articles.size,
        ) {
            articles[it]?.let { article ->
                ArticleCard(article = article, onClick = { onClick(article) })
            }
        }
    }

}

@Composable
fun ArticlesList(                                                        //список статей
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,                                   //принимает статьи из потока
    onClick: (Article) -> Unit                                            //по клику принимает параметр Article
) {

    val handlePagingResult = handlePagingResult(articles)                  //обрабатывать результат подкачки по страницам


    if (handlePagingResult) {                                              //если
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)           //Значения заполнения - Описывает заполнение, которое будет применяться по краям внутри коробки.
        ) {
            items(                                                                //элементы
                count = articles.itemCount,                                      //считает количество заметок
            ) {
                articles[it]?.let { article ->                                    //текущая статья
                    ArticleCard(article = article, onClick = { onClick(article) }) //карточка статьи/ по клику переходим
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {                     //обрабатывать результат подкачки по страницам/ принимает статьи
    val loadState = articles.loadState                                                     //Состояние загрузки
    val error = when {                                                                      //ошибка когда
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error        //освежить типа ошибки то
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null                                                                     //иначе налл
    }

    return when {                                                                        //верннуть когда
        loadState.refresh is LoadState.Loading -> {                                       //освежить когда типа зашрузка - при загруске чтоли
            ShimmerEffect()                                                               //эфект мерцания при загрузке
            false                                                                         //выкл или хз
        }

        error != null -> {                                                                //если ошибка не налл
            EmptyScreen(error = error)                                                    //пустой экран / принимает ошибку
            false
        }

        else -> {                                                                          //иначе включить
            true
        }
    }
}

@Composable
fun ShimmerEffect() {                                                          //Эффект мерцания - эфект типо ничего гн найдено - заставака
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {                                                   //повторени 10 раз
            ArticleCardShimmerEffect(                                             //Эффект мерцания карточки товара
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}