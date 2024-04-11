package com.niksah.gagarin.domain

import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.network.NetworkClient

class ApiRepositoryImpl(private val client: NetworkClient) : ApiRepository {
    override suspend fun uploadImage(file: ByteArray,  type: String) = client.api.uploadFile(file, type)
}