package com.niksah.gagarin.domain

import com.niksah.gagarin.data.models.History
import com.niksah.gagarin.data.models.Response
import com.niksah.gagarin.data.repositories.ResponseRepository
import kotlinx.coroutines.flow.MutableStateFlow

class ResponseRepositoryImpl : ResponseRepository {
    override val history: MutableStateFlow<List<History>> = MutableStateFlow(emptyList())
    override val selected: MutableStateFlow<History?> = MutableStateFlow(null)
}
