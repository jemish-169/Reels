package com.practice.reels.core.data.utils

import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.ResultOf
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException

suspend inline fun <reified T> safeBodyParse(
    response: HttpResponse
): ResultOf<T, String> {
    return try {
        val result = response.body<T>()
        ResultOf.Success(result)
    } catch (_: JsonConvertException) {
        ResultOf.Error(DataError.SERIALIZATION.value)
    } catch (_: NoTransformationFoundException) {
        ResultOf.Error(DataError.SERIALIZATION.value)
    } catch (_: Exception) {
        ResultOf.Error(DataError.UNKNOWN.value)
    }
}