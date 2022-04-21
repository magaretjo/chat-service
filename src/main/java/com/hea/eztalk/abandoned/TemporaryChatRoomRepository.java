package com.hea.eztalk.abandoned;

import com.hea.eztalk.domain.chatroom.TemporaryChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryChatRoomRepository { // extends JpaRepository<TemporaryChatRoom, String> {
    Optional<TemporaryChatRoom> findByCommunityId(Long CommunityId);
}
