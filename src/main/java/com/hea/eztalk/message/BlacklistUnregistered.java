package com.hea.eztalk.message;

import lombok.Data;

@Data
public class BlacklistUnregistered extends AbstractEvent {
// 블랙리스트 해제됨

    private String communityId;
    private String memberId;

}
