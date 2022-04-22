package com.hea.eztalk.domain.chatroom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Temporary")
public class TemporaryChatRoom extends ChatRoom  implements Serializable {
    // cannot create room without communityId
    protected TemporaryChatRoom() {}

    public TemporaryChatRoom(String communityId) {
        super(communityId);
    }

    @Override
    public String getChatRoomType() {
        return "Temporary";
    }

    @Override
    public String toString() {
        return "TemporaryChatRoom {" +
                "id='" + this.getId() + "'" +
                ", communityId='" + this.getCommunityId()  + "'" +
                ", createdAt='" + this.getCreatedAt()  + "'" +
                "} ";
    }
}
