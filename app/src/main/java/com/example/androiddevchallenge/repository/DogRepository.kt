package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.model.Dog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
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
                description = "Belay there's nothing like the clear punishment growing on the grog. The ancient tribble proudly outweighs the star."
            ),
            Dog(
                name = "Ana",
                imageUrl = "https://images.pexels.com/photos/1718181/pexels-photo-1718181.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 14,
                description = "Warp impressively like a small klingon. The ancient tribble proudly outweighs the star."
            ),
            Dog(
                name = "Alex",
                imageUrl = "https://images.pexels.com/photos/3888471/pexels-photo-3888471.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 20,
                description = "Everyone just loves the fierceness of pickles paste tossd with dill. The ancient tribble proudly outweighs the star."
            ),
            Dog(
                name = "Sury",
                imageUrl = "https://images.pexels.com/photos/3478876/pexels-photo-3478876.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 28,
                description = "Unda mechanice ducunt ad alter contencio. The ancient tribble proudly outweighs the star."
            ),
            Dog(
                name = "Dany",
                imageUrl = "https://images.pexels.com/photos/1790444/pexels-photo-1790444.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 14,
                description = "Earth is not the alchemistic awareness of the spirit. The ancient tribble proudly outweighs the star."
            ),
            Dog(
                name = "Jack",
                imageUrl = "https://images.pexels.com/photos/3478875/pexels-photo-3478875.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                months = 15,
                description = "Enamel four oz of chickpeas in one package of salsa verde. The ancient tribble proudly outweighs the star."
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

    fun findDogById(id: UUID): Flow<Dog?> {
        return dogs.map {
            it.find { dog -> dog.id == id}
        }.distinctUntilChanged()
    }
}
