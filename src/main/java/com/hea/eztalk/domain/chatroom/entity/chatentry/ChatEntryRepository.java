package com.hea.eztalk.domain.chatroom.entity.chatentry;

import com.hea.eztalk.domain.chatroom.entity.chatroom.ChatRoom;
import org.springframework.data.repository.CrudRepository;


public interface ChatEntryRepository extends CrudRepository<ChatEntry, Long> {
    Iterable<ChatEntry> findAllByChatRoom(ChatRoom chatroom);
}
