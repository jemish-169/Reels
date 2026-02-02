package com.practice.reels.core.data.utils

import com.practice.reels.core.data.dto.BaseAPIResponse
import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.ResultOf
import io.ktor.serialization.JsonConvertException
import kotlinx.serialization.json.Json

inline fun <reified T> verifyBasicData(
    result: BaseAPIResponse
): ResultOf<T, String> {
    return if (!result.success) {
        ResultOf.Error(result.message)
    } else if (result.success && result.data != null) {
        try {
            val json = Json { ignoreUnknownKeys = true }
            val res = json.decodeFromString<T>(result.data.toString())
            ResultOf.Success(res)
        } catch (_: JsonConvertException) {
            ResultOf.Error(DataError.SERIALIZATION.value)
        } catch (_: Exception) {
            ResultOf.Error(DataError.UNKNOWN.value)
        }
    } else if (!result.success && result.error != null) {
        ResultOf.Error(result.message)
    } else ResultOf.Error(DataError.UNKNOWN.value)
}