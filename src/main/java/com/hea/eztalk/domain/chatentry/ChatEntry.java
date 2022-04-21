package com.hea.eztalk.domain.chatentry;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.vo.RequestMember;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "chat_entry")
public class ChatEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_entry_id")
    private Long id;
        public Long getId() {
        return id;
    }

    @ManyToOne (fetch = LAZY) //, cascade = CascadeType.ALL)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;
        public ChatRoom getChatRoom() {
        return chatRoom;
    }

    @Column(name = "member_id")
    private String memberId;
        public String getMemberId() { return memberId; }

    @Column(name = "nickname")
    private String nickName;
        public String getNickName() {
            return nickName;
        }
        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

    @Column(name = "joined_at", nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime joinedAt;
        public LocalDateTime getJoinedAt() { return joinedAt; }

    @Column(name = "connected_last_at", nullable = false)
    private LocalDateTime connectedLastAt;
        public LocalDateTime getConnectedLastAt() { return connectedLastAt; }

    @Column(name = "status", nullable = false)
    private String status;
        public String getStatus() { return status; }
        public ChatEntryStatus getStatusCode() {
        return ChatEntryStatus.valueOf(status);
    }


    private void setInitialValues(ChatEntry entry) {
        entry.status = String.valueOf(ChatEntryStatus.READY);
        entry.joinedAt = LocalDateTime.now();
        entry.connectedLastAt = this.joinedAt;
    }

    private ChatEntry() {}


    /**
     * Additional Functions for defining Entity Behaviors
     */
    public static ChatEntry join(ChatRoom chatRoom, RequestMember member) {
        ChatEntry entry = new ChatEntry();
        entry.chatRoom = chatRoom;
        entry.memberId = member.getMemberId();
        entry.nickName = member.getNickName();
        entry.status = String.valueOf(ChatEntryStatus.READY);
        entry.joinedAt = LocalDateTime.now();
        entry.connectedLastAt = entry.joinedAt;

        return entry;
    }

    // 채팅창에 접속할 때, 일단 접속 시간을 남겨둔다.
    public void activate() {
        this.status = String.valueOf(ChatEntryStatus.ACTIVATE);
        this.connectedLastAt = LocalDateTime.now();
    }

    // 채팅창을 떠날 때, 마지막 접속 시간을 남겨둔다.
    public void deactivate() {
        if (this.getStatusCode() == ChatEntryStatus.ACTIVATE)
            this.status = String.valueOf(ChatEntryStatus.DEACTIVATE);
        this.connectedLastAt = LocalDateTime.now();
    }

    public void blocked() {
        this.status = String.valueOf(ChatEntryStatus.BLOCKED);
    }

    public void unblock() {
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
