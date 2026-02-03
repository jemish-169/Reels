package com.practice.reels.features.community.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CommunityDetailsResponse(

    @SerialName("code")
    val code: JsonElement,

    @SerialName("message")
    val message: String? = null,

    @SerialName("data")
    val data: CommunityDetailsDataDto? = null
)

@Serializable
data class CommunityDetailsDataDto(

    @SerialName("community_id")
    val communityId: String? = null,

    @SerialName("handle")
    val handle: String? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("type")
    val type: Int? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("color_code")
    val colorCode: String? = null,

    @SerialName("text_color_code")
    val textColorCode: String? = null,

    @SerialName("welcome_loop_id")
    val welcomeLoopId: String? = null,

    @SerialName("banner")
    val banner: String? = null,

    @SerialName("is_community_join_requested")
    val isCommunityJoinRequested: Boolean = false,

    @SerialName("dp")
    val dp: String? = null,

    @SerialName("dp_s")
    val dpS: String? = null,

    @SerialName("dp_m")
    val dpM: String? = null,

    @SerialName("dp_l")
    val dpL: String? = null,

    @SerialName("share_url")
    val shareUrl: String? = null,

    @SerialName("no_of_members")
    val noOfMembers: Int = 0,

    @SerialName("no_of_loops")
    val noOfLoops: Int = 0,

    @SerialName("no_of_videos")
    val noOfVideos: Int = 0,

    @SerialName("no_of_views")
    val noOfViews: Int = 0,

    @SerialName("no_of_comments")
    val noOfComments: Int = 0,

    @SerialName("no_of_reactions")
    val noOfReactions: Int = 0,

    @SerialName("no_of_shares")
    val noOfShares: Int = 0,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("is_ai_generated")
    val isAiGenerated: Boolean = false,

    @SerialName("is_loop_creation_allowed")
    val isLoopCreationAllowed: Boolean = false,

    @SerialName("social_links")
    val socialLinks: SocialLinksDto? = null,

    @SerialName("leader")
    val leader: CommunityLeaderDto? = null,

    @SerialName("guidelines")
    val guidelines: List<GuidelineDto> = emptyList(),

    @SerialName("brand_guidelines")
    val brandGuidelines: List<BrandGuidelineDto> = emptyList(),

    @SerialName("moderators")
    val moderators: List<ModeratorDto> = emptyList(),

    @SerialName("brand")
    val brand: CommunityBrandDetailDto? = null
)

@Serializable
data class SocialLinksDto(

    @SerialName("social_web_url")
    val socialWebUrl: String? = null,

    @SerialName("twitter")
    val twitter: SocialLinkItemDto? = null,

    @SerialName("linkedin")
    val linkedin: SocialLinkItemDto? = null,

    @SerialName("insta")
    val insta: SocialLinkItemDto? = null,

    @SerialName("discord_url")
    val discordUrl: String? = null,

    @SerialName("reddit_id")
    val redditId: SocialLinkItemDto? = null
)

@Serializable
data class SocialLinkItemDto(

    @SerialName("id")
    val id: String? = null,

    @SerialName("url")
    val url: String? = null
)

@Serializable
data class CommunityLeaderDto(

    @SerialName("member_id")
    val memberId: String? = null,

    @SerialName("nickname")
    val nickname: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("is_brand_system_user")
    val isBrandSystemUser: Boolean = false,

    @SerialName("is_avatar")
    val isAvatar: Boolean = false,

    @SerialName("role")
    val role: Int? = null,

    @SerialName("profile_image")
    val profileImage: String? = null
)

@Serializable
data class GuidelineDto(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("position")
    val position: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("guideline_id")
    val guidelineId: Int? = null,

    @SerialName("description")
    val description: String? = null
)

@Serializable
data class BrandGuidelineDto(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("position")
    val position: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("guideline_id")
    val guidelineId: Int? = null,

    @SerialName("description")
    val description: String? = null
)

@Serializable
data class ModeratorDto(

    @SerialName("member_id")
    val memberId: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("nickname")
    val nickname: String? = null,

    @SerialName("is_brand_system_user")
    val isBrandSystemUser: Boolean = false,

    @SerialName("is_avatar")
    val isAvatar: Boolean = false,

    @SerialName("role")
    val role: Int? = null,

    @SerialName("profile_image")
    val profileImage: String? = null,

    @SerialName("profile_image_s")
    val profileImageS: String? = null,

    @SerialName("profile_image_m")
    val profileImageM: String? = null,

    @SerialName("profile_image_l")
    val profileImageL: String? = null,

    @SerialName("brand")
    val brand: ModeratorBrandDto? = null
)

@Serializable
data class ModeratorBrandDto(

    @SerialName("brand_user_logo")
    val brandUserLogo: Int? = null,

    @SerialName("brand_id")
    val brandId: Int? = null,

    @SerialName("brand_slug")
    val brandSlug: String? = null
)

@Serializable
data class CommunityBrandDetailDto(

    @SerialName("brand_id")
    val brandId: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("subdomain")
    val subdomain: String? = null,

    @SerialName("logo")
    val logo: String? = null,

    @SerialName("created_at")
    val createdAt: Long? = null,

    @SerialName("brand_web_logo")
    val brandWebLogo: String? = null,

    @SerialName("favicon")
    val favicon: String? = null,

    @SerialName("brand_system_user_id")
    val brandSystemUserId: String? = null,

    @SerialName("brand_slug")
    val brandSlug: String? = null,

    @SerialName("brand_handle")
    val brandHandle: String? = null,

    @SerialName("brand_user_logo")
    val brandUserLogo: Int? = null
)
