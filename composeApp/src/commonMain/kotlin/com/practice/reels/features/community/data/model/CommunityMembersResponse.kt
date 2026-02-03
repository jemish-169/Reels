package com.practice.reels.features.community.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CommunityMembersResponse(

    @SerialName("code")
    val code: JsonElement,

    @SerialName("message")
    val message: String? = null,

    @SerialName("data")
    val data: CommunityMembersDataDto? = null
)

@Serializable
data class CommunityMembersDataDto(

    @SerialName("members")
    val members: List<CommunityMemberDto> = emptyList()
)

@Serializable
data class CommunityMemberDto(

    @SerialName("member_id")
    val memberId: String? = null,

    @SerialName("status")
    val status: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("nickname")
    val nickname: String? = null,

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

    @SerialName("role")
    val role: Int? = null,

    @SerialName("phone")
    val phone: String? = null,

    @SerialName("is_brand_system_user")
    val isBrandSystemUser: Boolean = false,

    @SerialName("brand")
    val brand: CommunityMemberBrandDto? = null
)

@Serializable
data class CommunityMemberBrandDto(

    @SerialName("brand_user_logo")
    val brandUserLogo: Int? = null,

    @SerialName("brand_id")
    val brandId: Int? = null,

    @SerialName("brand_slug")
    val brandSlug: String? = null
)
