package com.hea.eztalk.domain.chatroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "room_type",
        columnDefinition = "CHAR(30)"
)
@Table(name = "chatroom")
public abstract class ChatRoom implements Serializable {
    @Id
    @Column(name = "chatroom_id")
    private String id;
        public String getId() {
        return id;
    }

    // this is reference key of Community-Service
    @Column(name = "community_id")
    private String communityId;
        public String getCommunityId() {
            return communityId;
        }
        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

    @Column(name = "created_at", nullable = false, updatable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
        public LocalDateTime getCreatedAt() { return createdAt; }

//    @JsonIgnore
//    @OneToMany (mappedBy = "chatRoom", fetch = FetchType.LAZY)
//    private List<ChatEntry> chatEntries = new ArrayList<>();
//        public List<ChatEntry> getChatEntries() { return chatEntries; }

    // cannot create room without communityId
    protected ChatRoom() {}

    public ChatRoom(String communityId) {
        this.id = UUID.randomUUID().toString().substring(0, 18);
        this.communityId = communityId;
        this.createdAt = LocalDateTime.now();
    }

    public abstract String getChatRoomType();

}
