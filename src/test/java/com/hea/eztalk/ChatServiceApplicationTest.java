package com.hea.eztalk;

import com.hea.eztalk.domain.ChatEntryRepository;
import com.hea.eztalk.domain.ChatRoomRepository;
import com.hea.eztalk.domain.RegularChatRoomRepository;
import com.hea.eztalk.domain.TemporaryChatRoomRepository;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatroom.RegularChatRoom;
//import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.service.ChatService;
import com.hea.eztalk.vo.RequestMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ChatServiceApplicationTest {

    @Autowired
    ChatService chatService;

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
    public void 채팅룸만들기() throws Exception {

        String communityId = "ABCD";
        chatService.createCommunityChatRooms(communityId);
    }

    @Test
    @Transactional
    @DisplayName("채팅방 중복 개설 불가")
    public void createChatRoomsError() throws Exception {
        String communityId = "ABCD";

//        chatService.createCommunityChatRooms(communityId);
        assertThrows(DuplicateKeyException.class,
                () -> chatService.createCommunityChatRooms(communityId));
    }

    @Test
    @Transactional
    public void testGetChatRoom() throws Exception {
        String communityId = "ABCD";
        ChatRoom chatRoom = new RegularChatRoom(communityId);

        ChatRoom regularChatRoom = chatService.getRegularChatRoom(communityId);
        if (regularChatRoom == null) {
            System.out.println("Cannot find ChatRoom by Community ID : " + communityId);
        }
        System.out.println(regularChatRoom);
        assertThat(regularChatRoom.getChatRoomType()).isEqualTo("Regular");
    }

    @Test
    @Transactional
    public void testChatEntry() throws Exception {
        String communityId = "ABCD";

        ChatRoom chatRoom = regularChatRoomRepository.findByCommunityId(communityId).get();

        String memberId1 = UUID.randomUUID().toString();
        RequestMember member1 = new RequestMember();
        member1.setMemberId(memberId1);
        ChatEntry entry1 = ChatEntry.join(chatRoom, member1);
        entry1.setNickName("냐옹이");
        entry1.activate();
        chatEntryRepository.save(entry1);

        String memberId2 = UUID.randomUUID().toString();
        RequestMember member2 = new RequestMember();
        member2.setMemberId(memberId1);
        ChatEntry entry2 = ChatEntry.join(chatRoom, member2);
        entry2.setNickName("바둑이");
        chatEntryRepository.save(entry2);

        List<ChatEntry> entries = new ArrayList<>();
        Iterable<ChatEntry> chatEntries = chatEntryRepository.findAllByChatRoomId(chatRoom.getId());
        chatEntries.forEach(c -> entries.add( c ));

        System.out.println("Chatting Entries : " + chatEntries);
        System.out.println("Chatting Entries (DTOs) : " + entries);

        assertThat(entries.stream().count()).isEqualTo(2);
    }
}