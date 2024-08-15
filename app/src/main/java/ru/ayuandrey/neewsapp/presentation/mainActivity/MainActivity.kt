package ru.ayuandrey.neewsapp.presentation.mainActivity

//импортируются классы из других пакетов, библиотек


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.ayuandrey.neewsapp.presentation.navgraph.NavGraph
import ru.ayuandrey.neewsapp.ui.theme.NewsAppTheme

//при нажатии на иконкку приложения запускается активити
//MainActivity наследует, расширяет, класс ComponentActivity
//MainActivity наследует функциональность базового класса ComponentActivity

//Application (с помощью @HiltAndroidApp)
//ViewModel (с помощью @HiltViewModel)
//Activity           - @AndroidEntryPoint   +
//Fragment            - @AndroidEntryPoint
//View                 - @AndroidEntryPoint
//Service            - @AndroidEntryPoint
//BroadcastReceiver   - @AndroidEntryPoint

@AndroidEntryPoint //аннотация AndroidEntryPoint - Точка входа Android, создает отдельный компонент библиотеки Hilt для каждого класса Android в вашем проекте.
class MainActivity : ComponentActivity() {
                                                                                //называется это создание экземпляра viewModel
    private val viewModel by viewModels<MainViewModel>()                        //переменная viewModel делегирует свои свойства viewModels типа MainViewModel объектом
                                                                                 //возвращает экземпляр MainViewModel что указан в <>
    override fun onCreate(savedInstanceState: Bundle?) {                         //переопределяется функция определенная в ComponentActivity, onCreate
                                                                                //в onCreate функции создается интерфейс приложения
                                                                                //класс Bundle используется для передачи данных между базовыми компонентами, передается в функцию onCreate
        super.onCreate(savedInstanceState)                                       //ключевое слово super обозначает суперкласс - ComponentActivity, вызывается функция onCreate класса ComponentActivity
                                                                                 //savedInstanceState - сохраненное состояние экземпляра, передается параметром типа Bundle в функцию onCreate
        WindowCompat.setDecorFitsSystemWindows(window, false) //Сопоставление окон.установленный декор подходит для системных окон
                                                                                  //класс WindowCompat - является помощником для доступа к функциям в Window
                                                                                   //false, фреймворк не будет отображать верхние и нижние вставки
        installSplashScreen().apply {                                //installSplashScreen - установить заставку - Обеспечивает контроль над экраном-заставкой после запуска приложения. получаем ссылку на объект SplashScreen
            setKeepOnScreenCondition(condition = { viewModel.splashCondition.value }) //setKeepOnScreenCondition - установите режим Сохранения на экране/ condition - состояние/
        }                                                                          //
        setContent {                                                               //установить содержимое
            NewsAppTheme(dynamicColor = false) {                                   //
                val isSystemInDarkMode = isSystemInDarkTheme()                     //если Система В Темной Теме
                val systemUiColor = rememberSystemUiController()                   //цвет пользовательского интерфейса системы
                SideEffect {                                                       //Побочный эффект/ побочные эффекты необходимы, например, чтобы вызвать одноразовое событие, такое как отображение закусочной или переход к другому экрану при определенном условии состояния.
                    systemUiColor.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                //Add fillMaxSize()
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {//отрисовка компонента Box с фоном цвета background и на всю ширину экрана
                    NavGraph(startDestination = viewModel.startDestination.value)    //в навигационный график передается маршрут в зависимости от выполняемого условия во ViewModel
                }
            }
        }
    }
}