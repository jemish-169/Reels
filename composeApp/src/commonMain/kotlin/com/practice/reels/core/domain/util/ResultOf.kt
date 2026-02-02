package com.practice.reels.core.domain.util


sealed interface ResultOf<out D, out E> {
    data class Success<out D>(val data: D) : ResultOf<D, Nothing>
    data class Error<out E>(val error: E) : ResultOf<Nothing, E>
}

inline fun <T, E, R> ResultOf<T, E>.map(map: (T) -> R): ResultOf<R, E> {
    return when (this) {
        is ResultOf.Error -> ResultOf.Error(error)
        is ResultOf.Success -> ResultOf.Success(map(data))
    }
}

fun <T, E> ResultOf<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

inline fun <T, E> ResultOf<T, E>.onSuccess(action: (T) -> Unit): ResultOf<T, E> {
    return when (this) {
        is ResultOf.Error -> this
        is ResultOf.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E> ResultOf<T, E>.onError(action: (E) -> Unit): ResultOf<T, E> {
    return when (this) {
        is ResultOf.Error -> {
            action(error)
            this
        }

        is ResultOf.Success -> this
    }
}

typealias EmptyResult<E> = ResultOf<Unit, E>