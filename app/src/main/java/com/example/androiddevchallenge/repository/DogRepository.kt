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
            ),
            Dog(
                name = "Ana",
                imageUrl = "https://images.pexels.com/photos/1718181/pexels-photo-1718181.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 14,
                description = "Warp impressively like a small klingon."
            ),
            Dog(
                name = "Alex",
                imageUrl = "https://images.pexels.com/photos/3888471/pexels-photo-3888471.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 20,
                description = "Everyone just loves the fierceness of pickles paste tossd with dill."
            ),
            Dog(
                name = "Sury",
                imageUrl = "https://images.pexels.com/photos/3478876/pexels-photo-3478876.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 28,
                description = "Unda mechanice ducunt ad alter contencio."
            ),
            Dog(
                name = "Dany",
                imageUrl = "https://images.pexels.com/photos/1790444/pexels-photo-1790444.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 14,
                description = "Earth is not the alchemistic awareness of the spirit."
            ),
            Dog(
                name = "Jack",
                imageUrl = "https://images.pexels.com/photos/3478875/pexels-photo-3478875.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 15,
                description = "Enamel four oz of chickpeas in one package of salsa verde."
            ),
        )
    }

    private val _dogs = MutableStateFlow(DOGS)
    val dogs: StateFlow<List<Dog>> = _dogs

    suspend fun toggleFavorite(id: UUID) {
        val newDogs = _dogs.value.map { dog ->
            if (dog.id == id) dog.copy(isFavorite = !dog.isFavorite) else dog
        }

        _dogs.emit(newDogs)
    }
}
