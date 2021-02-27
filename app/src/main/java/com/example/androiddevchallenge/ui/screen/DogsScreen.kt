package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.repository.DogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import java.util.UUID

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun DogsScreen(
    dogRepository: DogRepository,
    selectDog: (UUID) -> Unit
) {
    val dogs by dogRepository.dogs.collectAsState()
    val composableScope = rememberCoroutineScope()

    LazyColumn {
        items(items = dogs) { dog ->
            DogCard(
                dog = dog,
                onSelectDog = selectDog,
                onToggleFavorite = {
                    composableScope.launch {
                        dogRepository.toggleFavorite(dog.id)
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
