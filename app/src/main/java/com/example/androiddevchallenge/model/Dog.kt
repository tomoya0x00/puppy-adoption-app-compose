package com.example.androiddevchallenge.model

import java.util.*

data class Dog(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val imageUrl: String,
    val months: Int,
    val description: String,
    val isFavorite: Boolean = false,
)
