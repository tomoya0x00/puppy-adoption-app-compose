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
import com.example.androiddevchallenge.ui.screen.DogsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*

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
            "${MainDestinations.DOG_DETAIL_ROUTE}/${MainDestinations.DOG_DETAIL_ID_KEY}",
            arguments = listOf(navArgument(MainDestinations.DOG_DETAIL_ID_KEY) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val arguments = requireNotNull(MainDestinations.DOG_DETAIL_ID_KEY)
            // TODO: Implment

        }
    }
}

class MainActions(navController: NavController) {
    val selectDog: (UUID) -> Unit = { dogId: UUID ->
        navController.navigate("${MainDestinations.DOG_DETAIL_ROUTE}/$dogId")
    }
}
