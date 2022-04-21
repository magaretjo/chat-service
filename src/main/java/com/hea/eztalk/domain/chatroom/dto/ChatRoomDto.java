package com.hea.eztalk.domain.chatroom.dto;

import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntry;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ChatRoomDto implements Serializable {
    private Long id;

    private Long communityId;
    private String roomType;
    private List<ChatEntry> chatEntries;
    private Date createdAt;
}
