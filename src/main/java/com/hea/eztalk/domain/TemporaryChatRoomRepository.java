package com.hea.eztalk.domain;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatroom.TemporaryChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryChatRoomRepository extends JpaRepository<TemporaryChatRoom, String> {
    Optional<ChatRoom> findByCommunityId(String CommunityId);
}
