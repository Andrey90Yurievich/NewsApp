package ru.ayuandrey.neewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import ru.ayuandrey.neewsapp.presentation.onboarding.OnBoardingScreen
import ru.ayuandrey.neewsapp.presentation.onboarding.components.OnBoardingPage
import ru.ayuandrey.neewsapp.presentation.onboarding.pages
import ru.ayuandrey.neewsapp.ui.theme.NeewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NeewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                        OnBoardingScreen()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NeewsAppTheme {
        OnBoardingScreen()
    }
}