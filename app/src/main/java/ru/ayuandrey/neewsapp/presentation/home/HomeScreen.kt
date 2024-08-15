package ru.ayuandrey.neewsapp.presentation.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.delay
import ru.ayuandrey.neewsapp.R
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.presentation.Dimens.MediumPadding1
import ru.ayuandrey.neewsapp.presentation.common.ArticlesList
import ru.ayuandrey.neewsapp.presentation.common.SearchBar
import ru.ayuandrey.neewsapp.presentation.navgraph.Route


@Composable
fun HomeScreen(                                  //домашняя страница
    articles: LazyPagingItems<Article>,           //статьи передаются типа/ Элементы отложенной подкачки по страницам/
    state: HomeState,                              //состояние
    event: (HomeEvent) -> Unit,                    //событие / принимает параметр и ничего не возвращает/ парапметры это события начальные и максимальные
    navigateToSearch: () -> Unit,                  //перейдите к Поиску
    navigateToDetails: (Article) -> Unit           //перейдите К Подробной Информации/ принимает Article - Статья
) {

    val titles by remember {                        //заголовок запомнить
        derivedStateOf {                             //следует использовать функцию derivedStateOf когда ваши входные данные для компонуемого объекта меняются чаще, чем вам нужно перекомпоновать. Это часто происходит, когда что-то часто меняется, например положение прокрутки, но составному объекту нужно реагировать на это только тогда, когда оно пересекает определенный порог. derivedStateOf создает новый объект состояния Compose, который вы можете наблюдать,
            if (articles.itemCount > 10) {           //если articles - приходят из вью модели/ количество предметов больше 10
                articles.itemSnapshotList.items      //Снимок данных элементов
                    .slice(IntRange(start = 0, endInclusive = 9))  //ломтик/Внутренний диапазон о 0 до конец включительно 9
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }  //присоединиться к строке/разделитель/ этот заголовок
            } else {                               //иначе путая строка
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()                             //Добавляет подкладку для размещения status bars вставок.
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),   //кабар
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(                                                  //поиск
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = navigateToSearch                                   //по клику на клавиатуре переходим в навигацию по поиску
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        val scrollState = rememberScrollState(initial = state.scrollValue)   //состояние прокуртки

        Text(                                                            //бегущая строка
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .horizontalScroll(scrollState, enabled = false),           //бегущая строка
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

                                                                         // Обновите значение maxscrolling
        LaunchedEffect(key1 = scrollState.maxValue) {     //при запуске/ ключем передаем максимальное значение с состояния скрола
            event(HomeEvent.UpdateMaxScrollingValue(scrollState.maxValue)) //значение максимальное скрола передается в объект запечатанного класса уходит в ViewModel
        }
                                                                           // Сохраните состояние позиции прокрутки
        LaunchedEffect(key1 = scrollState.value) {
            event(HomeEvent.UpdateScrollValue(scrollState.value)) //значение скрола передается в объект запечатанного класса уходит в ViewModel
        }
                                                                           // Анимируйте прокрутку
        LaunchedEffect(key1 = state.maxScrollingValue) {
            delay(500)
            if (state.maxScrollingValue > 0) {
                scrollState.animateScrollTo(                              //Прокрутите до положения в пикселях с анимацией.
                    value = state.maxScrollingValue,                      //максимальное значение
                    animationSpec = infiniteRepeatable(                    //спецификация анимации/ бесконечно повторяемый
                        tween(                                               //твин
                                                                         //продолжительность в миллисекундах
                            durationMillis = (state.maxScrollingValue - state.scrollValue) * 50_000 / state.maxScrollingValue,
                            easing = LinearEasing,                       //ослабление/Линейное ослабление
                            delayMillis = 1000                           //задержка в миллисекундах
                        )
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(                                                   //
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = navigateToDetails
        )
    }
}