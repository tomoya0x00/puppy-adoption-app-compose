package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.repository.DogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun DogsScreen(
    dogRepository: DogRepository,
) {
    val dogs by dogRepository.dogs.collectAsState()

    LazyColumn {
        items(items = dogs) { dog ->
            DogCard(
                dog = dog,
                navigateTo = { },
                onToggleFavorite = { })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
