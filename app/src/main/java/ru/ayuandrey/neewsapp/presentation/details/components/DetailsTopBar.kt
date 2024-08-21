package ru.ayuandrey.neewsapp.presentation.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.ayuandrey.neewsapp.R
import ru.ayuandrey.neewsapp.ui.theme.NewsAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
<<<<<<< HEAD
fun DetailsTopBar(                                                   //бфр с кнопками
    onBrowsingClick: () -> Unit,                                     //кнопка перехода в браузер
    onShareClick: () -> Unit,                                           //кнопка поделится
    onBookMarkClick: () -> Unit,                                      //закинуть в закладки
    onBackClick: () -> Unit,                                          //кнопка назад
) {

    TopAppBar(                                                          //
=======
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {

    TopAppBar(
>>>>>>> origin/lesson7
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body),
        ),
        title = {},
        navigationIcon = {
<<<<<<< HEAD
            IconButton(onClick = onBackClick) {                           //КНОПКА НАЗАД
=======
            IconButton(onClick = onBackClick) {
>>>>>>> origin/lesson7
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {

<<<<<<< HEAD
            IconButton(onClick = onBookMarkClick) {                          //кнопка закладка
=======
            IconButton(onClick = onBookMarkClick) {
>>>>>>> origin/lesson7
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null
                )
            }
<<<<<<< HEAD
            IconButton(onClick = onShareClick) {                               //кнопка поделится
=======
            IconButton(onClick = onShareClick) {
>>>>>>> origin/lesson7
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
<<<<<<< HEAD
            IconButton(onClick = onBrowsingClick) {                           //найти в интеренете
=======
            IconButton(onClick = onBrowsingClick) {
>>>>>>> origin/lesson7
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview() {
    NewsAppTheme(dynamicColor = false) {
        DetailsTopBar(
            onShareClick = { /*TODO*/ },
            onBookMarkClick = { /*TODO*/ },
            onBrowsingClick = {}) {

        }
    }
}//}