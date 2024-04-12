package com.niksah.gagarin.data.repositories

import com.niksah.gagarin.data.network.NetworkResponse
import com.niksah.gagarin.data.network.Response

interface ApiRepository {
    suspend fun uploadImage(file:ByteArray,  type: String): NetworkResponse<Response>
}