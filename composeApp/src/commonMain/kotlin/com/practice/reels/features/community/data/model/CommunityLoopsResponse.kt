package com.practice.reels.features.community.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CommunityLoopsResponse(

    @SerialName("code")
    val code: JsonElement,

    @SerialName("message")
    val message: String? = null,

    @SerialName("data")
    val data: CommunityLoopsDataDto? = null
)

@Serializable
data class CommunityLoopsDataDto(

    @SerialName("conversations")
    val conversations: List<ConversationDto> = emptyList(),

    @SerialName("no_of_data")
    val noOfData: Int = 0
)

@Serializable
data class ConversationDto(

    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("type")
    val type: Int? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("share_url")
    val shareUrl: String? = null,

    @SerialName("settings")
    val settings: ConversationSettingsDto? = null,

    @SerialName("position")
    val position: Int? = null,

    @SerialName("latest_message_at")
    val latestMessageAt: String? = null,

    @SerialName("is_ai_generated")
    val isAiGenerated: Boolean = false,

    @SerialName("template_id")
    val templateId: String? = null,

    @SerialName("is_welcome_loop")
    val isWelcomeLoop: Boolean = false,

    @SerialName("group")
    val group: GroupDto? = null,

    @SerialName("latest_messages")
    val latestMessages: List<LatestMessageDto> = emptyList(),

    @SerialName("member_info")
    val memberInfo: MemberInfoDto? = null,

    @SerialName("is_subscriber")
    val isSubscriber: Boolean = false,

    @SerialName("logged_in_user_status")
    val loggedInUserStatus: Int? = null,

    @SerialName("is_post_allowed")
    val isPostAllowed: Boolean = false,

    @SerialName("is_view_allowed")
    val isViewAllowed: Boolean = true,

    @SerialName("unread_message_count")
    val unreadMessageCount: Int = 0
)

@Serializable
data class ConversationSettingsDto(

    @SerialName("discoverable")
    val discoverable: Boolean = true
)

@Serializable
data class GroupDto(

    @SerialName("group_id")
    val groupId: String? = null,

    @SerialName("group_name")
    val groupName: String? = null,

    @SerialName("group_description")
    val groupDescription: String? = null,

    @SerialName("color_code")
    val colorCode: String? = null,

    @SerialName("text_color_code")
    val textColorCode: String? = null,

    @SerialName("no_of_members")
    val noOfMembers: Int = 0,

    @SerialName("no_of_subscribers")
    val noOfSubscribers: Int = 0,

    @SerialName("no_of_views")
    val noOfViews: Int = 0,

    @SerialName("no_of_videos")
    val noOfVideos: Int = 0,

    @SerialName("members")
    val members: List<GroupMemberDto> = emptyList(),

    @SerialName("dp")
    val dp: String? = null,

    @SerialName("dp_s")
    val dpS: String? = null,

    @SerialName("dp_m")
    val dpM: String? = null,

    @SerialName("dp_l")
    val dpL: String? = null
)

@Serializable
data class GroupMemberDto(

    @SerialName("member_id")
    val memberId: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("username")
    val username: String? = null,

    @SerialName("is_avatar")
    val isAvatar: Boolean = false,

    @SerialName("is_brand_system_user")
    val isBrandSystemUser: Boolean = false,

    @SerialName("status")
    val status: Int? = null,

    @SerialName("role")
    val role: Int? = null,

    @SerialName("profile_image")
    val profileImage: String? = null
)

@Serializable
data class LatestMessageDto(

    @SerialName("message_id")
    val messageId: String? = null,

    @SerialName("message_at")
    val messageAt: String? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("owner")
    val owner: MessageOwnerDto? = null,

    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,

    @SerialName("thumbnail_url_s")
    val thumbnailUrlS: String? = null,

    @SerialName("thumbnail_url_l")
    val thumbnailUrlL: String? = null
)

@Serializable
data class MessageOwnerDto(

    @SerialName("member_id")
    val memberId: String? = null,

    @SerialName("username")
    val username: String? = null
)

@Serializable
data class MemberInfoDto(

    @SerialName("member_id")
    val memberId: String? = null,

    @SerialName("status")
    val status: Int? = null,

    @SerialName("role")
    val role: Int? = null
)
