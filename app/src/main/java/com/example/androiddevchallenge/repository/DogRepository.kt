package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.model.Dog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

@ExperimentalCoroutinesApi
@FlowPreview
class DogRepository {
    companion object {
        val DOGS = listOf(
            Dog(
                name = "Henry",
                imageUrl = "https://images.pexels.com/photos/3726314/pexels-photo-3726314.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 8,
                description = "Belay there's nothing like the clear punishment growing on the grog."
            )
        )
    }

    private val _dogs = MutableStateFlow(DOGS)
    val dogs: StateFlow<List<Dog>> = _dogs

    suspend fun changeFavorite(id: UUID, favorite: Boolean) {
        val newDogs = _dogs.value.map { dog ->
            if (dog.id == id) dog.copy(isFavorite = favorite) else dog
        }

        _dogs.emit(newDogs)
    }
}
