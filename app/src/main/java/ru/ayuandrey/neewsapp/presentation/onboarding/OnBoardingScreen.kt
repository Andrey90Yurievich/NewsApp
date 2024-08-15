package ru.ayuandrey.neewsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.ayuandrey.neewsapp.presentation.Dimens.MediumPadding2
import ru.ayuandrey.neewsapp.presentation.common.NewsButton
import ru.ayuandrey.neewsapp.presentation.common.NewsTextButton
import ru.ayuandrey.neewsapp.presentation.onboarding.components.OnBoardingPage
import ru.ayuandrey.neewsapp.presentation.onboarding.components.PagerIndicator
import ru.ayuandrey.neewsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(                                                             //доска слайдов
    onEvent: (OnBoardingEvent) -> Unit                                             //параметр передается в тело функции, затем обращается к анонимному классу SaveAppEntry
) {                                 // (типы_параметров) -> возвращаемый_тип       //при нажатии на кнопку/
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {                     //состояние страниц с начальной 1 страницы
            pages.size                                                             //передается количество страниц = 3, хранит состояние из 3 страниц
        }
        val buttonsState = remember {                                              //состояние кнопок
            derivedStateOf {                                                       //производное состояние
                when (pagerState.currentPage) {                                    //когда состояние страниц равно 1, 2, 3
                    0 -> listOf("", "Next")                            // то на кнопках отображать список из 2-х элементов
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->                   //Горизонтальный пейджер принимает страницу его номер индекс
            OnBoardingPage(page = pages[index])                                       //страница пейджера принимает индекс цифровой что в состоянии и отображается страничка пейджера
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),                                            //Добавляет подкладку для размещения
            horizontalArrangement = Arrangement.SpaceBetween,                         //расположение элеметнов Пространство между
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(                                                          //Индикатор пейджера
                modifier = Modifier.width(52.dp),                                     //принимает параметры ширины 52 пикселя
                pagesSize = pages.size,                                               //количесов страниц - 3
                selectedPage = pagerState.currentPage                                  //выбранная страница/ значение из состояния
            )

            Row(verticalAlignment = Alignment.CenterVertically) {          //
                val scope = rememberCoroutineScope()                                   //Создает сопрограмму, привязанную к жизненному циклу
                //Скрывайте кнопку, когда первый элемент списка пуст                 //для запуска сопрограммы вне компонуемого объекта.
                if (buttonsState.value[0].isNotEmpty()) {                            //если страничка 1 не пустая
                    NewsTextButton(                                                  //то отобразить первую кнопку
                        text = buttonsState.value[0],                                 //передается параметры значения состояния кнопки с индексом 0
                        onClick = {                                                    //при клике кнопки
                            scope.launch {                             //запускается сопрограмма
                                pagerState.animateScrollToPage(                       //применяем функцию анимировать Прокрутку Страницы к pagerState
                                    page = pagerState.currentPage - 1                 //передаем араметр страницы значение индекс страницы - 1
                                )
                            }
                        }
                    )
                }
                NewsButton(                                                           //следующая кнопка
                    text = buttonsState.value[1],                                      //принимает с состояния кнопки значение
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {                           //если состояние страницы 2 последняя то
                                onEvent(OnBoardingEvent.SaveAppEntry)                     //то хз что происходит переход на старницу с новостями
                            } else {                                                     //иначе
                                pagerState.animateScrollToPage(                           //анимировать прокрутку страницы
                                    page = pagerState.currentPage + 1                      //на страницу вперед перейти кнопка
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

