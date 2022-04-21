package com.hea.eztalk;

import com.hea.eztalk.domain.ChatEntryRepository;
import com.hea.eztalk.domain.ChatRoomRepository;
import com.hea.eztalk.abandoned.RegularChatRoomRepository;
import com.hea.eztalk.abandoned.TemporaryChatRoomRepository;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatroom.RegularChatRoom;
import com.hea.eztalk.domain.chatroom.TemporaryChatRoom;
//import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.vo.RequestMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        ChatRoom chatRoom = new TemporaryChatRoom(communityId);
//        chatRoomRepository.save(chatRoom);


        communityId = 2L;
        chatRoom = new RegularChatRoom(communityId);
//        chatRoomRepository.save(chatRoom);

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

        assertEquals("Temporary", room1.getChatRoomType());
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
/*

        String memberId1 = UUID.randomUUID().toString();
        RequestMember member = new RequestMember();
        member.setMemberId(memberId1);
        ChatEntry entry1 = ChatEntry.join(room2, member);
        entry1.setNickName("냐옹이");
        entry1.activate();
        chatEntryRepository.save(entry1);

        String memberId2 = UUID.randomUUID().toString();
        ChatEntry entry2 = ChatEntry.join(room2, new RequestMember(memberId2));
        entry2.setNickName("바둑이");
        chatEntryRepository.save(entry2);

//        List<ChatEntryDto> entries = new ArrayList<>();
        Iterable<ChatEntry> chatEntries = chatEntryRepository.findAllByChatRoom(room2);
//        chatEntries.forEach(c -> entries.add( new ModelMapper().map(c, ChatEntryDto.class)));

        System.out.println("Chatting Entries : " + chatEntries);
//        System.out.println("Chatting Entries (DTOs) : " + entries);

//        assertEquals(2, entries.stream().count());
*/

    }
}