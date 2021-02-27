/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
