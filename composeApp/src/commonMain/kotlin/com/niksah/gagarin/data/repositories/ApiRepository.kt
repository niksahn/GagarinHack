package com.niksah.gagarin.data.repositories

import com.niksah.gagarin.data.network.NetworkResponse

interface ApiRepository {
    suspend fun uploadImage(file:ByteArray,  type: String): NetworkResponse<String>
}