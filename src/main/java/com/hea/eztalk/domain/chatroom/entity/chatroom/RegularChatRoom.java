package com.hea.eztalk.domain.chatroom.entity.chatroom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Regular")
public class RegularChatRoom extends ChatRoom  implements Serializable {

    //    public RegularChatRoom() {
//        this.chatRoomType = ChatRoomType.REGULAR;
//    }
    public String getChatType() {
        return "Regular";
    }

    /**
     * 정회원 채팅방에서는 주고받은 메시지를 공지사항으로도 등록할 수 있다.
     */
    public void postMessage() {

    }

    @Override
    public String toString() {
        return super.toString() + " RegularChatRoom }";
    }
}
