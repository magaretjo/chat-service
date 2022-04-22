package com.hea.eztalk.message;

import lombok.Data;

@Data
public class CommunityMemberDismissed extends AbstractEvent {
// 커뮤니티 멤버 탈퇴 메시지

    private String communityId;
    private String memberId;

}
