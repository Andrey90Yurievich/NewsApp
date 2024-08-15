package ru.ayuandrey.neewsapp.presentation.onboarding.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.ayuandrey.neewsapp.presentation.Dimens.IndicatorSize
import ru.ayuandrey.neewsapp.ui.theme.BlueGray
import ru.ayuandrey.neewsapp.ui.theme.NeewsAppTheme


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ff(

) {
    NeewsAppTheme {
        PageIndicator(
            modifier = Modifier,
            pageSize = 1,
            selectedPage = 1,

            )
    }
}