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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.AppContainer
import com.example.androiddevchallenge.ui.screen.DogDetailScreen
import com.example.androiddevchallenge.ui.screen.DogsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.UUID

object MainDestinations {
    const val DOGS_ROUTE = "dogs"

    const val DOG_DETAIL_ROUTE = "dog"
    const val DOG_DETAIL_ID_KEY = "dogId"
}

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun NavGraph(startDestination: String = MainDestinations.DOGS_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.DOGS_ROUTE) {
            DogsScreen(
                dogRepository = AppContainer.dogRepository,
                selectDog = actions.selectDog
            )
        }
        composable(
            "${MainDestinations.DOG_DETAIL_ROUTE}/{${MainDestinations.DOG_DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.DOG_DETAIL_ID_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            DogDetailScreen(
                dogRepository = AppContainer.dogRepository,
                dogId = UUID.fromString(arguments.getString(MainDestinations.DOG_DETAIL_ID_KEY, ""))
            )
        }
    }
}

class MainActions(navController: NavController) {
    val selectDog: (UUID) -> Unit = { dogId ->
        navController.navigate("${MainDestinations.DOG_DETAIL_ROUTE}/$dogId")
    }
}
