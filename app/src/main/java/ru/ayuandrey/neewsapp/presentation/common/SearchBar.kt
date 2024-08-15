package ru.ayuandrey.neewsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ayuandrey.neewsapp.R
import ru.ayuandrey.neewsapp.presentation.Dimens.IconSize
import ru.ayuandrey.neewsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(                            //бар поиска
    modifier: Modifier = Modifier,
    text: String,                         //передается текс типа строки
    readOnly: Boolean,                     //только читать
    onClick: (() -> Unit)? = null,          //тип функции ничего не принимает и ничего не возвращает, может быть null и проинициализирована null
    onValueChange: (String) -> Unit,        //изменение значения /тип функции принимает строку
    onSearch: () -> Unit                    //поиск колбэк
) {

    val interactionSource = remember {       //источник взаимодействия
        MutableInteractionSource()            //представляет поток Interactions, соответствующий событиям, генерируемым компонентом. Эти Interaction файлы можно использовать для изменения отображения компонентов в разных состояниях, например, при нажатии или перетаскивании компонента.
    }
    val isClicked = interactionSource.collectIsPressedAsState().value   //нажат ли /если вы хотите узнать, нажата ли определенная кнопка, вы можете вызвать ее метод collectIsPressedAsState
    //эффект запуска
    LaunchedEffect(key1 = isClicked){// корутина сработает при нажатии на кнопку //возможность вызывать функции приостановки, используйте составной объект LaunchedEffect
        if(isClicked){                              //если нажата кнопка
            onClick?.invoke()                       //то сработает навигация поиска/ запустить
        }
    }

    Box(modifier = modifier) {             //
        TextField(                                     //поле для ввода
            modifier = Modifier
                .fillMaxWidth()
                .searchBar(),                           //бар поиска/ представляет собой плавающее поле поиска с возможностями поиска и навигации.
            value = text,                               //текст приходит
            onValueChange = onValueChange,              //
            readOnly = readOnly,                         //только для чтения = true приходит
            leadingIcon = {                               //иконка с лупой
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize),
                    tint = colorResource(id = R.color.body)
                )
            },
            placeholder = {                                //плейсхолдер
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = MaterialTheme.shapes.medium,                  //форма
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.input_background),                   //цвет контейнера
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,      //выделенный цвет текста
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,           //цвет курсора
                disabledIndicatorColor = Color.Transparent,                                       //отключенный цвет индикатора
                errorIndicatorColor = Color.Transparent,                                           //цвет индикатора ошибки
                focusedIndicatorColor = Color.Transparent,                                        //сфокусированный цвет индикатора
                unfocusedIndicatorColor = Color.Transparent                                       //несфокусированный цвет индикатора
            ),
            singleLine = true,                                                    //одиночная строка
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()                                                    //при нажатии на клавиатуре запускается колбек
                }
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSource                          //источник взаимодействия
        )
    }
}

fun Modifier.searchBar(): Modifier = composed {           //если не темнавя тема то окантовка темная
    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    NewsAppTheme {
        SearchBar(text = "", onValueChange = {}, readOnly = false) {

        }
    }
}