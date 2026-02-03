package com.practice.reels.features.home.presentation.model

data class FeedUi(
    val items: List<ReelUi>,
    val endOfFeed: Boolean,
    val pageSession: String? = null
)
