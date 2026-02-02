package com.practice.reels.core.data.utils

import com.practice.reels.core.data.dto.BaseAPIResponse
import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.ResultOf
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ResultOf<T, String> {
    val response = try {
        execute()
    } catch (_: SocketTimeoutException) {
        return ResultOf.Error(DataError.REQUEST_TIMEOUT.value)
    } catch (_: UnresolvedAddressException) {
        return ResultOf.Error(DataError.NO_INTERNET.value)
    } catch (_: Exception) {
        currentCoroutineContext().ensureActive()
        return ResultOf.Error(DataError.UNKNOWN.value)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): ResultOf<T, String> {
    return withContext(Dispatchers.Default) {
        when (response.status.value) {
            408 -> ResultOf.Error(DataError.REQUEST_TIMEOUT.value)
            429 -> ResultOf.Error(DataError.TOO_MANY_REQUESTS.value)
            in 500..599 -> ResultOf.Error(DataError.SERVER.value)
            else -> {
                try {
                    val result = response.body<BaseAPIResponse>()
                    if (response.status.value in 200..299)
                        verifyBasicData(result = result)
                    else
                        ResultOf.Error(result.message)
                } catch (_: JsonConvertException) {
                    ResultOf.Error(DataError.SERIALIZATION.value)
                } catch (_: NoTransformationFoundException) {
                    ResultOf.Error(DataError.SERIALIZATION.value)
                } catch (_: Exception) {
                    ResultOf.Error(DataError.UNKNOWN.value)
                }
            }
        }
    }
}