package com.hea.eztalk.dto;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatEntryDto {
    private Long id;
    private String chatRoomId;
    private String memberId;
    private String nickName;
    private String status;
    private LocalDateTime joinedAt;
    private LocalDateTime connectedLastAt;
//    private ChatRoom chatRoom;

    @Override
    public String toString() {
        return "ChatEntryDto {" +
                "id=" + id +
                ", chatRoomId='" + chatRoomId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status='" + status + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
