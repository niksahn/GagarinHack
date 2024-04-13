package com.niksah.gagarin.data.repositories

import com.niksah.gagarin.data.models.History
import com.niksah.gagarin.data.models.Image
import com.niksah.gagarin.data.models.Response
import com.niksah.gagarin.data.network.NetworkResponse

interface ApiRepository {
    suspend fun uploadImage(file: ByteArray, type: String): NetworkResponse<Unit>
    suspend fun getHistory(id: String): NetworkResponse<List<History>>
    suspend fun getImage(id: String): NetworkResponse<String>
}