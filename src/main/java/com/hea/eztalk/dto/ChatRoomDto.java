package com.hea.eztalk.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ChatRoomDto implements Serializable {
    private String id;
    private String communityId;
    private String chatRoomType;
    private LocalDateTime createdAt;
}
