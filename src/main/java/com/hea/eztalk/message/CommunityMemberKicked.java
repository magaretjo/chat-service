package com.hea.eztalk.message;

import lombok.Data;

@Data
public class CommunityMemberKicked extends AbstractEvent {
// 커뮤니티 멤버 퇴출 메시지

    private String communityId;
    private String memberId;

}
