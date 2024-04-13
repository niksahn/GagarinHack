package com.niksah.gagarin.data.network

import io.ktor.client.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.*
import io.ktor.resources.*
import kotlinx.serialization.Serializable
import kotlin.random.Random

/** Спецификация сервиса авторизации. */
interface ApiSpec {
    suspend fun uploadFile(file: ByteArray, type: String): NetworkResponse<String>

    suspend fun getResults(id: String): NetworkResponse<Response>
    suspend fun getHistory(userId: String): NetworkResponse<Unit>
}

internal fun HttpClient.createApiSpec(): ApiSpec = object : ApiSpec {
    override suspend fun uploadFile(
        file: ByteArray,
        type: String
    ): NetworkResponse<String> = safeRequest {
        post(File()) {
            setBody(MultiPartFormDataContent(parts = formData {
                append(key = "File", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=${Random.nextInt()}.${type}")
                })
            }))
        }
    }

    override suspend fun getResults(id: String): NetworkResponse<Response> = safeRequest {
        get(urlString = "/$id")
    }

    override suspend fun getHistory(userId: String): NetworkResponse<Unit> = safeRequest {
        get(urlString = "/history/$userId")
    }
}

@Serializable
data class Response(
    val file: ByteArray,
    val fields: Map<String, String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Response

        if (!file.contentEquals(other.file)) return false
        if (fields != other.fields) return false

        return true
    }

    override fun hashCode(): Int {
        var result = file.contentHashCode()
        result = 31 * result + fields.hashCode()
        return result
    }
}


@Serializable
@Resource(path = "/File")
private class File
