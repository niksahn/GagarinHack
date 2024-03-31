package com.niksah.gagarin.data.network

import com.niksah.gagarin.data.Either
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.logging.Logger
import io.ktor.utils.io.CancellationException
import kotlinx.serialization.SerializationException

/** Результат сетевого запроса. */
typealias NetworkResponse<Resource> = Either<ApiResponseFailure, Resource>

/** Ошибка запроса в сеть. */
sealed class ApiResponseFailure {

    /** Отсутствует соединение. */
    object NoConnection : ApiResponseFailure()

    /** Запрос отменен. */
    object Cancellation : ApiResponseFailure()

    /** Ошибка от сервера. */
    data class ServerError(val status: ServerErrorCode) : ApiResponseFailure()

    /** Ошибки сервера. **/
    enum class ServerErrorCode { SERVER_RESPONSE_ERROR, REDIRECT_RESPONSE_ERROR, CLIENT_REQUEST_ERROR }

    /** Ошибка парсинга. */
    class ParseError(@Suppress(names = ["MemberVisibilityCanBePrivate"]) val exception: SerializationException) : ApiResponseFailure() {
        val stackTrace: String get() = exception.stackTraceToString()
        override fun equals(other: Any?): Boolean = other is InternalError && stackTrace == other.stackTrace
        override fun hashCode(): Int = stackTrace.hashCode()
        override fun toString(): String = "ParseError(exception=$exception)"
    }

    /** Внутреняя ошибка. */
    class InternalError(
        @Suppress(names = ["MemberVisibilityCanBePrivate"]) val exception: Exception,
    ) : ApiResponseFailure() {
        val stackTrace: String get() = exception.stackTraceToString()
        override fun equals(other: Any?): Boolean = other is InternalError && stackTrace == other.stackTrace
        override fun hashCode(): Int = stackTrace.hashCode()
        override fun toString(): String = "InternalError(exception=$exception)"
    }
}

/** Возвращает `true` если ошибка запроса: [ApiResponseFailure.Cancellation]. */
val ApiResponseFailure.isCancellation
    get() = when (this) {
        ApiResponseFailure.Cancellation -> true
        is ApiResponseFailure.InternalError,
        ApiResponseFailure.NoConnection,
        is ApiResponseFailure.ParseError,
        is ApiResponseFailure.ServerError -> false
    }

/** Безопасный вызов сетевого запроса, результат будет получен в виде [NetworkResponse]. */
internal suspend inline fun <reified T> safeRequest(block: () -> HttpResponse): NetworkResponse<T> = try {
    Either.Right(block().body())
}  catch (ignore: CancellationException) {
    println("The request has been canceled")
    Either.Left(ApiResponseFailure.Cancellation)
} catch (error: ServerResponseException) {
    println(error.message + " Internal server error")
    Either.Left(ApiResponseFailure.ServerError(ApiResponseFailure.ServerErrorCode.SERVER_RESPONSE_ERROR))
} catch (error: RedirectResponseException) {
    println(error.message + " Internal server error")
    Either.Left(ApiResponseFailure.ServerError(ApiResponseFailure.ServerErrorCode.REDIRECT_RESPONSE_ERROR))
} catch (error: ClientRequestException) {
    println(error.message + " Invalid http status received. HTTP status = ${error.response.status}")
    Either.Left(
        ApiResponseFailure.ServerError(
            if (error.response.status == HttpStatusCode.BadRequest) {
                ApiResponseFailure.ServerErrorCode.CLIENT_REQUEST_ERROR
            } else {
                ApiResponseFailure.ServerErrorCode.SERVER_RESPONSE_ERROR
            }
        )
    )
} catch (error: SerializationException) {
    println(error.message + " Could not perform response sterilization")
    Either.Left(ApiResponseFailure.ParseError(error))
} catch (error: Exception) {
    println(error.message + " Couldn't complete the request")
    Either.Left(ApiResponseFailure.InternalError(error))
}
