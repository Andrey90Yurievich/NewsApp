package ru.ayuandrey.neewsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ru.ayuandrey.neewsapp.presentation.news_navigator.NewsNavigator
import ru.ayuandrey.neewsapp.presentation.onboarding.OnBoardingScreen
import ru.ayuandrey.neewsapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(                                                                      //функция навигационный граф
    startDestination: String                                                       //параметром передается стартовый экран приходящий из ViewModel типа строка - маршрут в виде строки
) {
    val navController = rememberNavController()                                     //определяется навконтроллер - для управления навигацией

    NavHost(navController = navController, startDestination = startDestination) {//в функцию навхост передается навконтроллер и стартовый экран из параметра от ViewModel
        navigation(                                                                 //функция расширения NavGraphBuilder/ вложенный навигационный график
            route = Route.AppStartNavigation.route,                                 //маршрут "appStartNavigation"
            startDestination = Route.OnBoardingScreen.route                         //startDestination - начальный пункт назначения, "onBoardingScreen" - на посадочном экране
        ) {
            composable(route = Route.OnBoardingScreen.route) {//"onBoardingScreen" - на посадочном экране
                val viewModel: OnBoardingViewModel = hiltViewModel()                //При использовании Navigation Compose всегда используйте составную функцию hiltViewModel для получения экземпляра вашего ViewModel с аннотацией @HiltViewModel
                OnBoardingScreen(onEvent = viewModel::onEvent)                      //страничка начальных слайдера/ (::) в Kotlin используется для ссылки на метод или класс.
            }                                                                        //viewModel::onEvent - событие передается как параметр в компонуемую функцию
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }
    }
}