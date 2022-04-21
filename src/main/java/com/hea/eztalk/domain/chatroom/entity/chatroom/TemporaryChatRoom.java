package com.hea.eztalk.domain.chatroom.entity.chatroom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Temporary")
public class TemporaryChatRoom extends ChatRoom  implements Serializable {
//    public TemporaryChatRoom() {
//        this.chatRoomType = ChatRoomType.TEMPORARY;
//    }

    public String getChatType() {
        return "Temporary";
    }

    @Override
    public String toString() {
        return super.toString() + " TemporaryChatRoom }";
    }
}
