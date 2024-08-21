package ru.ayuandrey.neewsapp.presentation.news_navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import ru.ayuandrey.neewsapp.R
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.presentation.bookmark.BookmarkScreen
import ru.ayuandrey.neewsapp.presentation.bookmark.BookmarkViewModel
import ru.ayuandrey.neewsapp.presentation.details.DetailsScreen
import ru.ayuandrey.neewsapp.presentation.details.DetailsViewModel
import ru.ayuandrey.neewsapp.presentation.home.HomeScreen
import ru.ayuandrey.neewsapp.presentation.home.HomeViewModel
import ru.ayuandrey.neewsapp.presentation.navgraph.Route
import ru.ayuandrey.neewsapp.presentation.news_navigator.components.BottomNavigationItem
import ru.ayuandrey.neewsapp.presentation.news_navigator.components.NewsBottomNavigation
import ru.ayuandrey.neewsapp.presentation.search.SearchScreen
import ru.ayuandrey.neewsapp.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {                                                            //экран новостей

    val bottomNavigationItems = remember {                                        //нижние элементы навигации
        listOf(                                                                     //список объектов нижней навигации
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),        //создаются объеты data class BottomNavigationItem
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()                                   //определяем навигационный контроллер
    val backStackState = navController.currentBackStackEntryAsState().value        //состояние обратного стека - получите текущий NavBackStackEntry с помощью функции currentBackStackEntryAsState()/ доступ к текущему NavDestination - Навигационный пункт назначения
    var selectedItem by rememberSaveable {                                        //выбранный элемент/ запоминающийся, сохраняемый/ сохраняется при изменении конфигурации./
        mutableStateOf(0)                                                     //наблюдаемое состояние 0
    }
    selectedItem = when (backStackState?.destination?.route) {                      //выбранный элемент  / когда текущая навигация, маршрут
        Route.HomeScreen.route -> 0                                                 //маршруты возвращается порядковый номер маршрута
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0                                                                     //иначе домашняя
    }

                                                                                      //Скрывайте нижнюю навигацию, когда пользователь находится на экране сведений
    val isBottomBarVisible = remember(key1 = backStackState) {                         //Видна ли Нижняя панель/ remember - помнить
        backStackState?.destination?.route == Route.HomeScreen.route ||                //0 = домашнему маршруту или 1 = поисковому маршруту или 2 = маршруту закладки
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {                            //
        if (isBottomBarVisible) {                                                        //если isBottomBarVisible - показать нижнюю навигаци = true
            NewsBottomNavigation(                                                        //то рисовать нижнюю навигацию
                items = bottomNavigationItems,                                           //ПЕРЕДАЕТСЯ ОДИН ИЗ ОБЪЕКТОВ СПИСКА
                selectedItem = selectedItem,                                             //ПЕРЕДАЕТСЯ ВЫБРАННЫЙ ЭЛЕМЕНТ
                onItemClick = { index ->                                                 //ПРИ НАЖАТИИ КУЛБЕК ВОЗВРАЩАЕТ ИНДЕКС ЧИСЛО
                    when (index) {                                                      //КОГДА ВОЗВРАЩЕННЫЙ ИНДЕКС
                        0 -> navigateToTab(                                             //перейдите на Вкладку
                            navController = navController,                              //навконтроллер передается
                            route = Route.HomeScreen.route                               //0 ТО ПЕРЕХОДим на домашний экран
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()                                    //нижняя прокладка/ Заполнение, которое будет применено вдоль нижнего края внутри поля.
        NavHost(                                                                           //навигационнный хост
            navController = navController,                                                 //навконтроллер принимает который определен выше глобальнее
            startDestination = Route.HomeScreen.route,                                     //стартовый экран домашний
            modifier = Modifier.padding(bottom = bottomPadding)                             //снизу будет отступ
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry -> //маршрут передается в функцию
                val viewModel: HomeViewModel = hiltViewModel()                                  //создается объект HomeViewModel
                val articles = viewModel.news.collectAsLazyPagingItems()                         //статьи/ во вю модели список с строками/Класс, отвечающий за доступ к данным из Flow
                HomeScreen(                                                                       //домашний экран
                    articles = articles,                                                       //статьи идут из ViewModel
                    navigateToSearch = {                                                        //переход к экрану поиска
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->                                           //навигация к деталям
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    },
                    event = viewModel::onEvent,                                                //событие прокрутки
                    state = viewModel.state.value                                              //состояние прокрутки
                )
            }
            composable(route = Route.SearchScreen.route) {   //маршрут для экрана поиска
                val viewModel: SearchViewModel = hiltViewModel()                                   //создается объект SearchViewModel
                val state = viewModel.state.value                                                  //состояние из SearchViewModel
                OnBackClickStateSaver(navController = navController)
                SearchScreen(                                                                      //экран поиска
                    state = state,                                                                 //принимает состояние
                    event = viewModel::onEvent,                                                    //при нажатии на поиск идет обращение к событию во вью модели
                    navigateToDetails = { article ->                                               //прин нажатии на статью сработает лямбда функция
                        navigateToDetails(                                                         //
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {     //экран деталей
                val viewModel: DetailsViewModel = hiltViewModel()                                    //создается объект вью модели
<<<<<<< HEAD
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")      //создание карты с данными
                    ?.let { article ->                                                                //если не налл, то статью передаем в экран деталей
                        DetailsScreen(                                                                //экран с деталями
                            article = article,                                                          //статья передается
                            event = viewModel::onEvent,                                                  //события по нажатию на кнопки
                            navigateUp = { navController.navigateUp() },                              //навигация по иконкам
                            sideEffect = viewModel.sideEffect                                          //хз
=======
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
>>>>>>> origin/lesson7
                        )
                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {                        //На обратной стороне нажмите кнопку Сохранить состояние
    BackHandler(true) {                                                   //Обработка кнопки возврата системы
        navigateToTab(                                                            //переход на домашний экран
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {    //перейдите на Вкладку
    navController.navigate(route) {                        //переходит к экрану по строке маршруту полученной из вне
        navController.graph.startDestinationRoute?.let { screen_route ->     //начать маршрут назначения/let - код  в {} выполнится если navController.graph.startDestinationRoute не null
            popUpTo(screen_route) {                          //
                saveState = true                                             //как понимаю при переходе к экрану предидущий закрывается
            }
        }
        launchSingleTop = true                                                //запуск одиночной вершины
        restoreState = true                                                    //восстановить состояние
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {          //навигация на детали/ принимает навконтроллер и  объекты - статьи типа Article
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)       //Возврат результата к предыдущему месту назначения
                                                                                         //создание карты для хранения значений хз для чего
                                                                                         //SavedStateHandle - это карта ключ-значение, которая может использоваться для хранения и извлечения данных./ Используя данный SavedStateHandle, вы можете получать доступ к данным и передавать их между пунктами назначения.
    navController.navigate(                                                              //навигация на экран деталей
        route = Route.DetailsScreen.route
    )
}