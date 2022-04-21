package com.hea.eztalk.abandoned;

import com.hea.eztalk.domain.chatroom.RegularChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegularChatRoomRepository { //} extends JpaRepository<RegularChatRoom, String> {
    Optional<RegularChatRoom> findByCommunityId(Long CommunityId);
}
