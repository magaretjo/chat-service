package com.hea.eztalk.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestMember {
    private String memberId;
    private String nickName;

 /* public RequestMember(String memberId) {
        this.memberId = memberId;
    }
 }*/
}
