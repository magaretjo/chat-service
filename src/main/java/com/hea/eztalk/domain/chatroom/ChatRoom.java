package com.hea.eztalk.domain.chatroom;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private Long communityId;
        public Long getCommunityId() {
            return communityId;
        }
        public void setCommunityId(Long communityId) {
            this.communityId = communityId;
        }

    @Column(name = "created_at", nullable = false, updatable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
        public LocalDateTime getCreatedAt() { return createdAt; }

    // cannot create room without communityId
    protected ChatRoom() {}

    public ChatRoom(Long communityId) {
        this.id = UUID.randomUUID().toString();
        this.communityId = communityId;
        this.createdAt = LocalDateTime.now();
    }

    public abstract String getChatRoomType();

}
