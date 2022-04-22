package com.hea.eztalk.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestChatRoom {
    private String communityId;
    private String chatRoomId;
}
