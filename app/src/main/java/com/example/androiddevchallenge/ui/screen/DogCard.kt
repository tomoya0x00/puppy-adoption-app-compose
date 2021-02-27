package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Dog
import com.example.androiddevchallenge.repository.DogRepository
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Composable
fun DogCard(
    dog: Dog,
    navigateTo: () -> Unit,
    onToggleFavorite: (Dog) -> Unit
) {
    Surface(
        elevation = 1.dp,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column {
            ImageAndFavoriteButton(dog = dog, onToggleFavorite = { onToggleFavorite(dog) })
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.h4,
                )
                Text(
                    text = "${dog.months} months",
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        }
    }
}

@Composable
fun ImageAndFavoriteButton(
    dog: Dog,
    onToggleFavorite: (Dog) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        CoilImage(
            data = dog.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            fadeIn = true,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        )
        {
            FavoriteButton(
                isFavorite = dog.isFavorite,
                onClick = { onToggleFavorite(dog) },
                modifier = Modifier
            )
        }
    }

}

@FlowPreview
@ExperimentalCoroutinesApi
@Preview
@Composable
fun DogCardPreview() {
    val dog = DogRepository.DOGS.first()

    DogCard(
        dog = dog,
        navigateTo = { },
        onToggleFavorite = { },
    )
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { onClick() },
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
        )
    }
}

@Preview("Favorite Button")
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton(isFavorite = false, onClick = { })
}

@Preview("Favorite Button Favorited")
@Composable
fun FavoriteButtonFavoritedPreview() {
    FavoriteButton(isFavorite = true, onClick = { })
}
