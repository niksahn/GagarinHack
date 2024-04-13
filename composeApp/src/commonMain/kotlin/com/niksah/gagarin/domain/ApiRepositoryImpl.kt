package com.niksah.gagarin.domain

import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.models.left
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.network.NetworkClient
import com.niksah.gagarin.data.network.NetworkResponse
import com.niksah.gagarin.data.network.Response
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class ApiRepositoryImpl(private val client: NetworkClient) : ApiRepository {
    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun uploadImage(file: ByteArray, type: String): NetworkResponse<Response> {
        val fileB = Base64.encodeToByteArray(file, 0, file.size)
        client.api.uploadFile(fileB, type).fold(
            ifLeft = { return NetworkResponse.left(it) },
            ifRight = {
                return client.api.getResults(it)
            }
        )
    }

    override suspend fun getHistory(id: String): NetworkResponse<Unit> {
        return client.api.getHistory(id)
    }
}