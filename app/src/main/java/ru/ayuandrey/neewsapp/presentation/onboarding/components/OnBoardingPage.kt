package ru.ayuandrey.neewsapp.presentation.onboarding.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ayuandrey.neewsapp.R
import ru.ayuandrey.neewsapp.presentation.Dimens
import ru.ayuandrey.neewsapp.presentation.Dimens.MediumPadding2
import ru.ayuandrey.neewsapp.presentation.onboarding.Page
import ru.ayuandrey.neewsapp.presentation.onboarding.pages
import ru.ayuandrey.neewsapp.ui.theme.NeewsAppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f), //присвоить число объекту типа Float
            painter = painterResource(id = page.image), //painter - художник
            contentDescription = null,
            contentScale = ContentScale.Crop //Масштаб контента, Урожай
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1)) //проставка, разделитель, прострнство
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_MASK, showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    NeewsAppTheme {
        OnBoardingPage(
            page = pages[0]
        )
    }

}