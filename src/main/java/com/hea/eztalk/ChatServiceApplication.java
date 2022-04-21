package com.hea.eztalk;


import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.hea.eztalk.domain.chatroom.dto.ChatEntryDto;
import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.entity.chatentry.ChatEntryRepository;
import com.hea.eztalk.domain.chatroom.entity.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatroom.entity.chatroom.ChatRoomRepository;
import com.hea.eztalk.domain.chatroom.entity.chatroom.TemporaryChatRoom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ChatServiceApplication {

    static ApplicationContext applicationContext;
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(ChatServiceApplication.class, args);

//        test();
    }

    private static void test() {
        //--------------------------------------------------------------------------------
        ChatRoomRepository chatRoomRepository = applicationContext.getBean(ChatRoomRepository.class);

        Long communityId = 1L;
        ChatRoom chatRoom = new TemporaryChatRoom();
        chatRoom.setCommunityId(communityId);

        chatRoomRepository.save(chatRoom);

        Optional<ChatRoom> optChatRoom = chatRoomRepository.findByCommunityId(communityId);
        if (optChatRoom.isEmpty()) {
            System.out.println("Cannot find Chat-Room by Community ID : " + communityId);
        }
        chatRoom = optChatRoom.get();
        System.out.println("Created Chat-Room : " + chatRoom);

        //---------------------------------------------------------------------------------
        ChatEntryRepository chatEntryRepository = applicationContext.getBean(ChatEntryRepository.class);

        String memberId1 = UUID.randomUUID().toString();
        ChatEntry entry1 = new ChatEntry(chatRoom, memberId1);
        entry1.setNickName("냐옹이");
        entry1.setActivate();
        chatEntryRepository.save(entry1);

        String memberId2 = UUID.randomUUID().toString();
        ChatEntry entry2 = new ChatEntry(chatRoom, memberId2);
        entry2.setNickName("바둑이");
        chatEntryRepository.save(entry2);

        List<ChatEntryDto> entries = new ArrayList<>();
        Iterable<ChatEntry> chatEntries = chatEntryRepository.findAllByChatRoom(chatRoom);
        chatEntries.forEach(c -> entries.add( new ModelMapper().map(c, ChatEntryDto.class)));

        System.out.println("Chatting Entries : " + entries);
    }

}
