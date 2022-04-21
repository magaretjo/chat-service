package com.hea.eztalk.domain.chatroom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Regular")
public class RegularChatRoom extends ChatRoom  implements Serializable {

    // cannot create room without communityId
    protected RegularChatRoom() {}

    public RegularChatRoom(Long communityId) {
        super(communityId);
    }

    @Override
    public String getChatRoomType() {
        return "Regular";
    }

    /**
     * 정회원 채팅방에서는 주고받은 메시지를 공지사항으로도 등록할 수 있다.
     */
    public void postMessage() {
        // TBD
    }

    @Override
    public String toString() {
        return "RegularChatRoom {" +
                "id='" + this.getId() + "'" +
                ", communityId='" + this.getCommunityId()  + "'" +
                ", createdAt='" + this.getCreatedAt()  + "'" +
                "} ";
    }
}
