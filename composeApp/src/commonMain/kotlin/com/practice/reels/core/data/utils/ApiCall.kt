package com.practice.reels.core.data.utils

import com.practice.reels.core.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement

suspend inline fun <reified T, reified S, reified U> apiCall(
    urlParams: T,
    headerParams: S,
    bodyParams: U,
    serviceCall: String,
    client: HttpClient,
    baseUrl: String = Constants.BASE_URL,
    httpMethod: HttpMethod
): HttpResponse {
    return withContext(Dispatchers.IO) {
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
        }
        client.request(urlString = baseUrl + serviceCall) {
            this.method = httpMethod

            headers {
                val jsonElement = json.encodeToJsonElement(headerParams)
                if (jsonElement is JsonObject) {
                    jsonElement.forEach { (key, value) ->
                        if (value is JsonPrimitive) {
                            if (!value.isString || value.content.isNotEmpty()) {
                                headers.append(key, value.content)
                            }
                        }
                    }
                }
            }

            url {
                val jsonElement = json.encodeToJsonElement(urlParams)
                if (jsonElement is JsonObject) {
                    jsonElement.forEach { (key, value) ->
                        if (value is JsonPrimitive) {
                            if (!value.isString || value.content.isNotEmpty()) {
                                parameters.append(key, value.content)
                            }
                        }
                    }
                }
            }

            if (httpMethod == HttpMethod.Post || httpMethod == HttpMethod.Put) {
                val jsonElement = json.encodeToJsonElement(bodyParams)
                setBody(jsonElement)
            }
        }
    }
}
