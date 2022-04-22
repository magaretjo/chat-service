package com.hea.eztalk.domain;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEntryRepository extends JpaRepository<ChatEntry, Long> {
    Iterable<ChatEntry> findAllByChatRoom(ChatRoom chatroom);
    Iterable<ChatEntry> findAllByChatRoomId(String chatroomId);
    Iterable<ChatEntry> findByChatRoomAndMemberId(ChatRoom chatRoom, String memberId);
}
