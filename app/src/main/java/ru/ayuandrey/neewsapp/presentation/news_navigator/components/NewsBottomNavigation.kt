package ru.ayuandrey.neewsapp.presentation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ayuandrey.neewsapp.R
import ru.ayuandrey.neewsapp.presentation.Dimens.ExtraSmallPadding2
import ru.ayuandrey.neewsapp.presentation.Dimens.IconSize
import ru.ayuandrey.neewsapp.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(                                //нижняя навигация
    items: List<BottomNavigationItem>,                   //принимает список с объектами нижней навигации
    selectedItem: Int,                                   //выбранный элемент
    onItemClick: (Int) -> Unit   //приходит индекс       //клик по элементу, функция принимает параметр инт и ничего не возращает
) {
    NavigationBar(                                        //компос функция наигационного бара
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,  //цвет контейнера
        tonalElevation = 10.dp                                    //оттененеие высота
    ) {
        items.forEachIndexed { index, item ->             //элемент.для каждого проиндексированного
            NavigationBarItem(                            //элемент навигации
                selected = index == selectedItem,         //selected - выбранный/ присвоить переданному из параметров
                onClick = { onItemClick(index) },         //переходить по маршруту в зависимости от индекса
                icon = {                                  //иконка навбара
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(                             //иконка переданная из объектов списка
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(    //цвета навигации по умолчанию
                    selectedIconColor = MaterialTheme.colorScheme.primary,   //выбранный цвет значка
                    selectedTextColor = MaterialTheme.colorScheme.primary,   //выбранный цвет ТЕКСТА
                    unselectedIconColor = colorResource(id = R.color.body),  //невыбранный цвет значка
                    unselectedTextColor = colorResource(id = R.color.body),   //невыбранный цвет ТЕКСТА
                    indicatorColor = MaterialTheme.colorScheme.background      //цвет индикатора ТОГО ЧТО ВЫБРАНО
                ),
            )
        }
    }
}

data class BottomNavigationItem(                  //элемент нижней навигации
    @DrawableRes val icon: Int,                   //принимает картинку из ресурсов приложения
    val text: String                               //принимает текст типа строка
)

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme(dynamicColor = false) {
        NewsBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        ), selectedItem = 0, onItemClick = {})
    }
}