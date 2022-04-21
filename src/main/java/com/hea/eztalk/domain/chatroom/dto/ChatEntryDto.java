package com.hea.eztalk.domain.chatroom.dto;

import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntryStatus;
import com.hea.eztalk.domain.chatroom.entity.chatroom.ChatRoom;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Data
public class ChatEntryDto {
    private Long id;
    private ChatRoom chatRoom;
    private String memberId;
    private String nickName;
    private String status;
    private Date joinedAt;

    @Override
    public String toString() {
        return "ChatEntryDto{" +
                "id=" + id +
//                ", chatRoom=" + chatRoom +
                ", memberId='" + memberId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status='" + status + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
