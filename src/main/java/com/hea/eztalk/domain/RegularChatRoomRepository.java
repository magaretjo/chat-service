package com.hea.eztalk.domain;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatroom.RegularChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegularChatRoomRepository extends JpaRepository<RegularChatRoom, String> {
    Optional<ChatRoom> findByCommunityId(String CommunityId);
}
