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
}

/** Авторизация. */
@Serializable
@Resource(path = "/auth")
private class AuthRequest {
    /** Обновление токена. */
    @Serializable
    @Resource(path = "/token")
    class RefreshToken(val parent: AuthRequest = AuthRequest())
}

/** Вспомнить пароль. */
@Serializable
@Resource(path = "/forgot")
private class ForgotRequest


/** Сменить пароль. */
@Serializable
@Resource(path = "/resetPassword")
private class ChangePasswordRequest


@Serializable
@Resource(path = "/File")
private class File
