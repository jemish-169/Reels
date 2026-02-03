package com.practice.reels.features.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ======================= Feed Response =======================

@Serializable
data class FeedResponse(

    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String? = null,

    @SerialName("data")
    val data: FeedDataDto
)

@Serializable
data class FeedDataDto(

    @SerialName("feeds")
    val feeds: List<FeedItemDto> = emptyList(),

    @SerialName("end_of_feed")
    val endOfFeed: Boolean = false,

    @SerialName("expiry")
    val expiry: Long? = null,

    @SerialName("page_session")
    val pageSession: String? = null
)

@Serializable
data class FeedItemDto(

    @SerialName("uuid")
    val uuid: String,

    @SerialName("type")
    val type: String,

    @SerialName("owner")
    val owner: OwnerDto,

    @SerialName("community")
    val community: CommunityDto? = null,

    @SerialName("loop")
    val loop: LoopDto? = null,

    @SerialName("score")
    val score: ScoreDto? = null,

    @SerialName("video")
    val video: VideoDto? = null
)

@Serializable
data class ScoreDto(

    @SerialName("pop_score")
    val popScore: Double = 0.0,

    @SerialName("rec_score")
    val recScore: Double = 0.0,

    @SerialName("context_score")
    val contextScore: Double = 0.0,

    @SerialName("user_interest_score")
    val userInterestScore: Double = 0.0,

    @SerialName("final_score")
    val finalScore: Double = 0.0
)

// ======================= Owner =======================

@Serializable
data class OwnerDto(

    @SerialName("uuid")
    val uuid: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("username")
    val username: String,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("is_avatar")
    val isAvatar: Boolean = false,

    @SerialName("profile_image")
    val profileImage: String? = null,

    @SerialName("profile_image_s")
    val profileImageS: String? = null,

    @SerialName("profile_image_m")
    val profileImageM: String? = null,

    @SerialName("profile_image_l")
    val profileImageL: String? = null,

    @SerialName("is_brand_system_user")
    val isBrandSystemUser: Boolean = false,

    @SerialName("brand")
    val brand: OwnerBrandDto? = null,

    @SerialName("share_url")
    val shareUrl: String? = null
)

@Serializable
data class OwnerBrandDto(

    @SerialName("brand_id")
    val brandId: Int? = null,

    @SerialName("brand_slug")
    val brandSlug: String? = null,

    @SerialName("brand_user_logo")
    val brandUserLogo: Int? = null
)

// ======================= Community =======================

@Serializable
data class CommunityDto(

    @SerialName("uuid")
    val uuid: String? = null,

    @SerialName("handle")
    val handle: String? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String? = null,

    @SerialName("color_code")
    val colorCode: String? = null,

    @SerialName("text_color_code")
    val textColorCode: String? = null,

    @SerialName("dp")
    val dp: String? = null,

    @SerialName("dp_s")
    val dpS: String? = null,

    @SerialName("dp_m")
    val dpM: String? = null,

    @SerialName("dp_l")
    val dpL: String? = null,

    @SerialName("share_string")
    val shareString: String? = null,

    @SerialName("brand_id")
    val brandId: Int? = null,

    @SerialName("type")
    val type: Int? = null,

    @SerialName("share_url")
    val shareUrl: String? = null,

    @SerialName("no_of_views")
    val noOfViews: Int = 0,

    @SerialName("no_of_members")
    val noOfMembers: Int = 0,

    @SerialName("no_of_videos")
    val noOfVideos: Int = 0,

    @SerialName("no_of_sparks")
    val noOfSparks: Int = 0,

    @SerialName("no_of_groups")
    val noOfGroups: Int = 0,

    @SerialName("brand")
    val brand: CommunityBrandDto? = null
)

@Serializable
data class CommunityBrandDto(

    @SerialName("brand_id")
    val brandId: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("brand_slug")
    val brandSlug: String? = null,

    @SerialName("brand_web_logo")
    val brandWebLogo: String? = null,

    @SerialName("brand_user_logo")
    val brandUserLogo: Int? = null,

    @SerialName("brand_handle")
    val brandHandle: String? = null
)

// ======================= Loop =======================

@Serializable
data class LoopDto(

    @SerialName("uuid")
    val uuid: String? = null,

    @SerialName("group_id")
    val groupId: String? = null,

    @SerialName("group_name")
    val groupName: String,

    @SerialName("group_description")
    val groupDescription: String? = null,

    @SerialName("dp")
    val dp: String? = null,

    @SerialName("dp_s")
    val dpS: String? = null,

    @SerialName("dp_m")
    val dpM: String? = null,

    @SerialName("dp_l")
    val dpL: String? = null,

    @SerialName("color_code")
    val colorCode: String? = null,

    @SerialName("text_color_code")
    val textColorCode: String? = null,

    @SerialName("settings")
    val settings: LoopSettingsDto? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("share_string")
    val shareString: String? = null,

    @SerialName("type")
    val type: Int? = null,

    @SerialName("share_url")
    val shareUrl: String? = null,

    @SerialName("is_loop_subscriber")
    val isLoopSubscriber: Boolean = false,

    @SerialName("is_subscriber")
    val isSubscriber: Boolean = false,

    @SerialName("actions")
    val actions: List<LoopActionDto> = emptyList(),

    @SerialName("no_of_views")
    val noOfViews: Int = 0,

    @SerialName("no_of_members")
    val noOfMembers: Int = 0,

    @SerialName("no_of_videos")
    val noOfVideos: Int = 0,

    @SerialName("no_of_sparks")
    val noOfSparks: Int = 0
)

@Serializable
data class LoopSettingsDto(

    @SerialName("discoverable")
    val discoverable: Boolean = true
)

@Serializable
data class LoopActionDto(

    @SerialName("access_type_id")
    val accessTypeId: Int? = null,

    @SerialName("action_id")
    val actionId: Int? = null
)

// ======================= Video =======================

@Serializable
data class VideoDto(

    @SerialName("uuid")
    val uuid: String? = null,

    @SerialName("duration")
    val duration: Int? = null,

    @SerialName("attached_link")
    val attachedLink: String? = null,

    @SerialName("clickable_url")
    val clickableUrl: String? = null,

    @SerialName("conversation_at")
    val conversationAt: Long? = null,

    @SerialName("description_data")
    val descriptionData: String? = null,

    @SerialName("description_text")
    val descriptionText: String? = null,

    @SerialName("is_pinned")
    val isPinned: Boolean = false,

    @SerialName("is_watched")
    val isWatched: Boolean = false,

    @SerialName("is_read")
    val isRead: Boolean = false,

    @SerialName("is_sparked")
    val isSparked: Boolean = false,

    @SerialName("is_transcribed")
    val isTranscribed: Boolean = false,

    @SerialName("linkouts")
    val linkouts: List<LinkoutDto> = emptyList(),

    @SerialName("linkouts_id")
    val linkoutsId: Int? = null,

    @SerialName("linkouts_inappbrowser")
    val linkoutsInAppBrowser: Boolean = true,

    @SerialName("media_url")
    val mediaUrl: String,

    @SerialName("media_url_m3u8")
    val mediaUrlM3u8: String? = null,

    @SerialName("meta_data")
    val metaData: VideoMetaDataDto? = null,

    @SerialName("no_of_comments")
    val noOfComments: Int = 0,

    @SerialName("no_of_shares")
    val noOfShares: Int = 0,

    @SerialName("no_of_sparks")
    val noOfSparks: Int = 0,

    @SerialName("no_of_views")
    val noOfViews: Int = 0,

    @SerialName("owner")
    val owner: OwnerDto? = null,

    @SerialName("share_url")
    val shareUrl: String? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("sprite_image_url")
    val spriteImageUrl: String? = null,

    @SerialName("thumbnail_url")
    val thumbnailUrl: String,

    @SerialName("thumbnail_url_l")
    val thumbnailUrlL: String? = null,

    @SerialName("thumbnail_url_s")
    val thumbnailUrlS: String? = null,

    @SerialName("video_summary")
    val videoSummary: String? = null,

    @SerialName("card_layout_id")
    val cardLayoutId: Int? = null,

    @SerialName("video_layout_id")
    val videoLayoutId: Int? = null
)

@Serializable
data class VideoMetaDataDto(

    @SerialName("aspect_ratio")
    val aspectRatio: String? = null,

    @SerialName("contains_external_videos")
    val containsExternalVideos: Boolean = false,

    @SerialName("duration")
    val duration: String? = null,

    @SerialName("media_type")
    val mediaType: String? = null,

    @SerialName("resolution")
    val resolution: String? = null,

    @SerialName("size")
    val size: String? = null,

    @SerialName("video_source")
    val videoSource: String? = null
)

@Serializable
data class LinkoutDto(

    @SerialName("cta_link")
    val ctaLink: String? = null,

    @SerialName("cta_text")
    val ctaText: String? = null,

    @SerialName("links")
    val links: List<LinkDto> = emptyList(),

    @SerialName("style")
    val style: Int? = null
)

@Serializable
data class LinkDto(

    @SerialName("image")
    val image: String? = null,

    @SerialName("link")
    val link: String? = null,

    @SerialName("position")
    val position: Int? = null,

    @SerialName("title")
    val title: String? = null
)

// ======================= Request =======================

@Serializable
data class FeedRequest(

    @SerialName("device_id")
    val deviceId: String,

    @SerialName("type")
    val type: Int
)
