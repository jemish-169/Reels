package com.practice.reels.core.domain.util

enum class DataError(val value: String) {
    REQUEST_TIMEOUT(value = "The request timed out."),
    TOO_MANY_REQUESTS(value = "Your quota seems to be exceeded."),
    NO_INTERNET(value = "Couldn't reach server, please check your internet connection."),
    SERVER(value = "Description not available."),
    SERIALIZATION(value = "Couldn't parse data."),
    UNKNOWN(value = "Oops, something went wrong."),
    DATA_NOT_FOUND(value = "No data found!"),
}