package com.hea.eztalk.message;

import lombok.Data;

@Data
public class BlacklistRegistered extends AbstractEvent {
// 블랙리스트 등록됨

    private String communityId;
    private String memberId;

}
