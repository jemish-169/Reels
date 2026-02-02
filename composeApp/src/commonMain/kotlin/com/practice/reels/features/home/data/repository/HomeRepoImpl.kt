package com.practice.reels.features.home.data.repository

import com.practice.reels.features.home.domain.HomeRepository
import io.ktor.client.HttpClient

class HomeRepoImpl(
    private val client: HttpClient
) : HomeRepository {

}