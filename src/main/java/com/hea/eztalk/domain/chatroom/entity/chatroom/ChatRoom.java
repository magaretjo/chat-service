package com.hea.eztalk.domain.chatroom.entity.chatroom;

import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.entity.chatroom.ChatRoomType;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "room_type",
        columnDefinition = "CHAR(30)"
)
@Table(name = "chatroom")
public abstract class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    // this is reference key of Community-Service
    @Column(name = "community_id")
    private Long communityId;

//    @Column(name = "chatroom_type", nullable = false, updatable = false)
//    ChatRoomType chatRoomType;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatRoom", cascade = CascadeType.ALL)
//    private List<ChatEntry> chatEntries;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //    public ChatRoomType getChatRoomType() {
//        return chatRoomType;
//    }
//
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "ChatRoom {" +
                "id=" + id +
                ", communityId=" + communityId +
                ", createdAt=" + createdAt +
                ", ";
    }
}
