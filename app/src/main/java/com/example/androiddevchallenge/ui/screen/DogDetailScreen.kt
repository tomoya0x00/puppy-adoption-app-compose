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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
fun DogDetailScreen(
    dogRepository: DogRepository,
    dogId: UUID
) {
    val dog by dogRepository.findDogById(dogId).collectAsState(null)
    val composableScope = rememberCoroutineScope()

    dog?.let { dog ->
        Column {
            ImageAndFavoriteButton(
                dog = dog,
                onToggleFavorite = {
                    composableScope.launch {
                        dogRepository.toggleFavorite(dogId)
                    }
                }
            )
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
                Text(
                    text = dog.description,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}
