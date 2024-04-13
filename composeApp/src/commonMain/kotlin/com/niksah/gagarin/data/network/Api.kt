package com.niksah.gagarin.data.network

import com.niksah.gagarin.data.models.History
import com.niksah.gagarin.data.models.Image
import com.niksah.gagarin.data.models.Response
import io.ktor.client.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.*
import io.ktor.resources.*
import kotlinx.serialization.Serializable
import okio.ByteString.Companion.toByteString
import kotlin.random.Random

/** Спецификация сервиса авторизации. */
interface ApiSpec {
    suspend fun uploadFile(file: String, userId: String): NetworkResponse<Unit>
    suspend fun getResults(id: String): NetworkResponse<Response>
    suspend fun getHistory(userId: String): NetworkResponse<List<History>>
    suspend fun getImage(id: String): NetworkResponse<String>
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
//                MultiPartFormDataContent(parts = formData {
//                append(key = "File", file, Headers.build {
//                    append(HttpHeaders.ContentDisposition, "filename=${Random.nextInt()}.${type}")
//                })
//            })
            )
        }
    }

    override suspend fun getResults(id: String): NetworkResponse<Response> = safeRequest {
        get(urlString = "result/$id")
    }

    override suspend fun getHistory(userId: String): NetworkResponse<List<History>> = safeRequest {
        get(urlString = "/DocumentRecognition/$userId")
        //get(urlString = "/Ping")
    }

    override suspend fun getImage(id: String): NetworkResponse<String> = safeRequest {
        get(urlString = "/File/$id")
    }
}

@Serializable
data class ImageRequest(
    val image: String,
    val userId: String
)
