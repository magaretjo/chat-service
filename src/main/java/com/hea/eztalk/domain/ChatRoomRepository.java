package com.hea.eztalk.domain;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    List<ChatRoom> findByCommunityId(String CommunityId);
}
