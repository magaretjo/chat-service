package com.hea.eztalk.domain;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import org.springframework.data.repository.CrudRepository;


public interface ChatEntryRepository extends CrudRepository<ChatEntry, Long> {
    Iterable<ChatEntry> findAllByChatRoom(ChatRoom chatroom);
}
