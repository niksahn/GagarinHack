package com.niksah.gagarin.data

import com.niksah.gagarin.data.network.NetworkResponse
import com.niksah.gagarin.data.network.safeRequest
import io.ktor.client.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.resources.*
import kotlinx.serialization.Serializable

/** Спецификация сервиса авторизации. */
interface ApiSpec {

}

internal fun HttpClient.createApiSpec(): ApiSpec = object : ApiSpec {

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
