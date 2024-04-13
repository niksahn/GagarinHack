package com.niksah.gagarin.data.repositories

import com.niksah.gagarin.data.models.History
import kotlinx.coroutines.flow.MutableStateFlow

interface ResponseRepository {
    val history: MutableStateFlow<List<History>>
    val selected: MutableStateFlow<History?>
}