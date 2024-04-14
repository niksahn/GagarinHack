package com.niksah.gagarin.domain

import com.niksah.gagarin.data.models.History
import com.niksah.gagarin.data.network.NetworkClient
import com.niksah.gagarin.data.network.NetworkResponse
import com.niksah.gagarin.data.repositories.ApiRepository
import okio.ByteString.Companion.toByteString

class ApiRepositoryImpl(private val client: NetworkClient) : ApiRepository {
    override suspend fun uploadImage(file: ByteArray, id: String): NetworkResponse<Unit> {
        val fileA = file.toByteString(0, file.size).base64()
        return client.api.uploadFile(fileA, id)
    }

    override suspend fun getHistory(id: String): NetworkResponse<List<History>> {
        return client.api.getHistory(id)
    }

    override suspend fun getImage(id: String): NetworkResponse<String> =
        client.api.getImage(id)

    override suspend fun getSva(guid: String): NetworkResponse<String> = client.api.getSva(guid)
}