package com.hea.eztalk.domain.chatroom.entity.chatentry;

import com.hea.eztalk.domain.chatroom.entity.chatroom.ChatRoom;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Table(name = "chat_entry")
public class ChatEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_entry_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "status")
    @ColumnDefault(value = "READY")
    private String status;

    @Column(name = "joined_at", nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date joinedAt;


    public ChatEntry() {
        this.status = String.valueOf(ChatEntryStatus.READY);
    }

    public ChatEntry(ChatRoom chatRoom, String memberId) {
        this.chatRoom = chatRoom;
        this.memberId = memberId;
        this.status = String.valueOf(ChatEntryStatus.READY);
    }

    public Long getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public String getStatus() {
        return status;
    }

    public ChatEntryStatus showStatusCode() {
        return ChatEntryStatus.valueOf(status);
    }

    public void setActivate() {
        this.status = String.valueOf(ChatEntryStatus.ACTIVATE);
    }
    public void setDeactivate() {
        this.status = String.valueOf(ChatEntryStatus.DEACTIVATE);
    }

    public void setBlocked() {
        this.status = String.valueOf(ChatEntryStatus.BLOCKED);
    }

    public void setUnblocked() {
        this.status = String.valueOf(ChatEntryStatus.READY);
    }

    @Override
    public String toString() {
        return "ChatEntry{" +
                "id=" + id +
                ", chatRoom=" + chatRoom +
                ", memberId='" + memberId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
