package ru.ayuandrey.neewsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import ru.ayuandrey.neewsapp.R
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {              //Пустой экран/ принимает состояние ошибки

    var message by remember {                                 //сообщение запомнить
        mutableStateOf(parseErrorMessage(error = error))         //наблюдает за ошибкой
    }

    var icon by remember {                                        //иконка
        mutableStateOf(R.drawable.ic_network_error)                //печальный ящик
    }

    if (error == null){                                             //если ошибка налл
        message = "До сих пор вы не сохраняли новости!"                 //сообщение
        icon = R.drawable.ic_search_document                           //иконка поиска по докменту
    }

    var startAnimation by remember {                                  //начать анимацию
        mutableStateOf(false)                                    //выключить
    }

    val alphaAnimation by animateFloatAsState(                       //альфа-анимация
        targetValue = if (startAnimation) 0.3f else 0f,              //
        animationSpec = tween(durationMillis = 1000)                 //спецификация анимации / продолжительность
    )                                                                //tween - прямолинейная анимаци

    LaunchedEffect(key1 = true) {                     //запущенный эффект
        startAnimation = true                                         //запустить анимацию
    }

    EmptyContent(alphaAnim = alphaAnimation, message = message, iconId = icon)    //Пустое содержимое -

}

@Composable
fun EmptyContent(alphaAnim: Float, message: String, iconId: Int) {                  //Пустое содержимое
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(                                                                     //иконка
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )
        Text(                                                                      //текст
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
        )
    }
}


fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }

        is ConnectException -> {
            "Internet Unavailable."
        }

        else -> {
            "Unknown Error."
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(alphaAnim = 0.3f, message = "Internet Unavailable.",R.drawable.ic_network_error)
}