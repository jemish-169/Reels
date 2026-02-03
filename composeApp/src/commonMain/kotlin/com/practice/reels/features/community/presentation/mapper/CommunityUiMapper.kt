package com.practice.reels.features.community.presentation.mapper

import com.practice.reels.features.community.domain.model.CommunityDetails
import com.practice.reels.features.community.domain.model.CommunityLoops
import com.practice.reels.features.community.domain.model.CommunityMembers
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import com.practice.reels.features.community.presentation.model.CommunityLoopsUi
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import com.practice.reels.features.community.presentation.model.LoopUi
import com.practice.reels.features.community.presentation.model.MemberUi

fun CommunityDetails.toUi(): CommunityDetailsUi {
    return CommunityDetailsUi(
        id = this.id,
        name = this.name,
        handle = this.handle,
        slug = this.slug
    )
}

fun CommunityLoops.toUi(): CommunityLoopsUi {
    return CommunityLoopsUi(
        loops = this.loops.map { loop ->
            LoopUi(
                id = loop.id,
                groupId = loop.groupId,
                groupName = loop.groupName
            )
        },
        count = this.count
    )
}

fun CommunityMembers.toUi(): CommunityMembersUi {
    return CommunityMembersUi(
        members = this.members.map { member ->
            MemberUi(
                id = member.id,
                username = member.username
            )
        }
    )
}
