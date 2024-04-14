package com.niksah.gagarin.data.network

import com.niksah.gagarin.data.models.History
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.random.Random

/** Спецификация сервиса авторизации. */
interface ApiSpec {
    suspend fun uploadFile(file: String, userId: String): NetworkResponse<Unit>
    suspend fun getHistory(userId: String): NetworkResponse<List<History>>
    suspend fun getImage(id: String): NetworkResponse<String>
    suspend fun getSva(guid: String): NetworkResponse<String>
}

internal fun HttpClient.createApiSpec(): ApiSpec = object : ApiSpec {
    override suspend fun uploadFile(
        file: String,
        userId: String
    ): NetworkResponse<Unit> = safeRequest {
        post(urlString = "/DocumentRecognition") {
            contentType(ContentType.Application.Json)
            setBody(
                ImageRequest(
                    file, userId
                )
            )
        }
    }

    override suspend fun getHistory(userId: String): NetworkResponse<List<History>> = safeRequest {
        get(urlString = "/DocumentRecognition/$userId")
    }

    override suspend fun getImage(id: String): NetworkResponse<String> = safeRequest {
        get(urlString = "/File/$id")
    }

    override suspend fun getSva(guid: String): NetworkResponse<String> = safeRequest {
        get(urlString = "/DocumentRecognition/download/$guid")
    }
}

@Serializable
data class ImageRequest(
    val image: String,
    val userId: String
)

fun String.saveToFile(fileSystem: FileSystem): String {
    val file = this
    val path =
        FileSystem.SYSTEM_TEMPORARY_DIRECTORY.relativeTo("ScanReport-${Random.nextInt()}".toPath())

    val readmeContent = fileSystem.write(path) {
        write(com.niksah.gagarin.screens.result.encodeToByteArray(file))
    }.emit()
    return path.name
}