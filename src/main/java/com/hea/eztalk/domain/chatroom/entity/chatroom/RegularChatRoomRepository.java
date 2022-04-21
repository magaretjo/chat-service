package com.hea.eztalk.domain.chatroom.entity.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegularChatRoomRepository extends JpaRepository<RegularChatRoom, Long> {
    Optional<RegularChatRoom> findByCommunityId(Long CommunityId);
}
