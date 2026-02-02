package com.practice.reels.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class BaseAPIResponse(
    // Default fields for success and errors
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("origin") val origin: String,
    @SerialName("statusCode") val statusCode: Int,

    // Dynamic fields based on status
    @SerialName("data") val data: JsonObject? = null,
    @SerialName("errors") val error: JsonObject? = null
)