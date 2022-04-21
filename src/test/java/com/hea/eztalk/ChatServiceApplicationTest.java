package com.hea.eztalk;

import com.hea.eztalk.domain.chatroom.dto.ChatEntryDto;
import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntryRepository;
import com.hea.eztalk.domain.chatroom.entity.chatroom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ChatServiceApplicationTest {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    TemporaryChatRoomRepository tempChatRoomRepository;

    @Autowired
    RegularChatRoomRepository regularChatRoomRepository;

    @Autowired
    ChatEntryRepository chatEntryRepository;


    @BeforeEach
    @Transactional
    public void makeChatRoom() throws Exception {

        Long communityId = 1L;
        ChatRoom chatRoom = new TemporaryChatRoom();
        chatRoom.setCommunityId(communityId);

        chatRoomRepository.save(chatRoom);


        communityId = 2L;
        chatRoom = new RegularChatRoom();
        chatRoom.setCommunityId(communityId);

        chatRoomRepository.save(chatRoom);

    }

    @Transactional
    public void testChatRoom() throws Exception {
        Long communityId = 1L;

        Optional<TemporaryChatRoom> optChatRoom = tempChatRoomRepository.findByCommunityId(communityId);
        if (optChatRoom.isEmpty()) {
            System.out.println("Cannot find Chat-Room by Community ID : " + communityId);
        }
        TemporaryChatRoom room1 = optChatRoom.get();
        System.out.println("Chat-Room : " + room1);

        assertEquals("Temporary", room1.getChatType());
    }

    @Test
    @Transactional
    public void testChatEntry() throws Exception {
        Long communityId = 2L;
//        ChatRoom chatRoom = new RegularChatRoom();
//        chatRoom.setCommunityId(communityId);

        Optional<RegularChatRoom> optChatRoom = regularChatRoomRepository.findByCommunityId(communityId);
        if (optChatRoom.isEmpty()) {
            System.out.println("Cannot find Chat-Room by Community ID : " + communityId);
        }
        RegularChatRoom room2 = optChatRoom.get();

        String memberId1 = UUID.randomUUID().toString();
        ChatEntry entry1 = new ChatEntry(room2, memberId1);
        entry1.setNickName("냐옹이");
        entry1.setActivate();
        chatEntryRepository.save(entry1);

        String memberId2 = UUID.randomUUID().toString();
        ChatEntry entry2 = new ChatEntry(room2, memberId2);
        entry2.setNickName("바둑이");
        chatEntryRepository.save(entry2);

        List<ChatEntryDto> entries = new ArrayList<>();
        Iterable<ChatEntry> chatEntries = chatEntryRepository.findAllByChatRoom(room2);
        chatEntries.forEach(c -> entries.add( new ModelMapper().map(c, ChatEntryDto.class)));

        System.out.println("Chatting Entries : " + chatEntries);
        System.out.println("Chatting Entries (DTOs) : " + entries);

        assertEquals(2, entries.stream().count());

    }
}