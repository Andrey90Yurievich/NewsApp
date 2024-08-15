package ru.ayuandrey.neewsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.ayuandrey.neewsapp.domain.usercase.AppEntryUseCase
import ru.ayuandrey.neewsapp.presentation.navgraph.NavGraph
import ru.ayuandrey.neewsapp.presentation.onboarding.OnBoardingScreen
import ru.ayuandrey.neewsapp.presentation.onboarding.OnBoardingViewModel
import ru.ayuandrey.neewsapp.presentation.onboarding.components.OnBoardingPage
import ru.ayuandrey.neewsapp.presentation.onboarding.pages
import ru.ayuandrey.neewsapp.ui.theme.NeewsAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    val viewModel by viewModels<MainViewModel>()

//    @Inject
//    lateinit var useCase: AppEntryUseCase
    //lateinit var appEntryUseCases: AppEntryUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }

//        lifecycleScope.launch {
//            useCase.readAppEntry().collect{
//                Log.d("Test", it.toString())
//            }
//        }

//
//        lifecycleScope.launch {
//            appEntryUseCases.readAppEntry().collect{
//                Log.d("Test", it.toString())
//            }
//        }

        setContent {
            NeewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                        val startDestination = viewModel.startDestination
                        NavGraph(startDestination = startDestination)
                    }
                }

            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NeewsAppTheme {
//        OnBoardingScreen()
//    }
//}