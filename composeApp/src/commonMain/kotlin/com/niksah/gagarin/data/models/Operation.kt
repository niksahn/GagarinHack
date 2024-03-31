package com.niksah.seconHack.data.models

sealed class Operation<out Failure, out Success> {
	
	/** Операция выполняется. */
	object Preparing : Operation<Nothing, Nothing>()
	
	/** Операция выполнена успешно. Результат операции [value]. */
	data class Success<out T>(val value: T) : Operation<Nothing, T>()
	
	/** Операция завершилась c ошибкой [value]. */
	data class Failure<out T>(val value: T) : Operation<T, Nothing>()
}