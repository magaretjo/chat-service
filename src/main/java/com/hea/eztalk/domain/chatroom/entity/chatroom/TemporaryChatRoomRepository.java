package com.hea.eztalk.domain.chatroom.entity.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TemporaryChatRoomRepository extends JpaRepository<TemporaryChatRoom, Long> {
    Optional<TemporaryChatRoom> findByCommunityId(Long CommunityId);
}
