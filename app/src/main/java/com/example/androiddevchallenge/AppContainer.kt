package com.example.androiddevchallenge

import com.example.androiddevchallenge.repository.DogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

object AppContainer {
    @FlowPreview
    @ExperimentalCoroutinesApi
    val dogRepository by lazy {
        DogRepository()
    }
}