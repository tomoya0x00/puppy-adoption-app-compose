package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
    Column {
        ConstraintLayout {
            val (image, button) = createRefs()
            CoilImage(
                data = dog.imageUrl,
                contentDescription = null,
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            FavoriteButton(
                isFavorite = dog.isFavorite,
                onClick = { onToggleFavorite(dog) },
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(image.top, margin = 16.dp)
                    end.linkTo(image.end, margin = 16.dp)
                }
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
