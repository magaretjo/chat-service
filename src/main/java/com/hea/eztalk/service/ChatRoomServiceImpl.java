package com.hea.eztalk.service;

import com.hea.eztalk.domain.ChatEntryRepository;
import com.hea.eztalk.domain.ChatRoomRepository;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.chatroom.RegularChatRoom;
import com.hea.eztalk.domain.chatroom.TemporaryChatRoom;
import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.dto.ChatRoomDto;
import com.hea.eztalk.vo.RequestMember;
import com.hea.eztalk.vo.RequestChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

    ChatRoomRepository chatRoomRepository;
    ChatEntryRepository chatEntryRepository;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository,
                               ChatEntryRepository chatEntryRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatEntryRepository = chatEntryRepository;
    }

    protected ChatRoom createTemporaryChatRoom(RequestChatRoom request) {
        TemporaryChatRoom chatRoom = new TemporaryChatRoom(request.getCommunityId());
        chatRoomRepository.save(chatRoom);
        log.debug("<<TemporaryChatRoom>> : " + chatRoom);

        return chatRoom;
    }

    protected ChatRoom createRegularChatRoom(RequestChatRoom request) {
        RegularChatRoom chatRoom = new RegularChatRoom(request.getCommunityId());
        chatRoomRepository.save(chatRoom);
        log.debug("<<regularChatRoom>> : " + chatRoom);

        return chatRoom;
    }

    public List<ChatRoom> createCommunityChatRooms(RequestChatRoom request) {
        ChatRoom chatRoom1 = createTemporaryChatRoom(request);
        ChatRoom chatRoom2 = createRegularChatRoom(request);

        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRooms.add(chatRoom1);
        chatRooms.add(chatRoom2);

        return chatRooms;
    }

    public ChatRoomDto getChatRoom(String id) {
        Optional<ChatRoom> optChatRoom = chatRoomRepository.findById(id);

        if (optChatRoom.isEmpty()) return null;

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(optChatRoom.get(), ChatRoomDto.class);
    }

    @Override
    public ChatEntryDto joinChatRoom(String chatRoomId, RequestMember member) {
        Optional<ChatRoom> optChatRoom = chatRoomRepository.findById(chatRoomId);

        if (optChatRoom.isEmpty()) {
            throw new IllegalArgumentException("채팅방을 찾을 수 없습니다.");
        }

        ChatRoom theChatRoom = optChatRoom.get();

        ChatEntry chatEntry = ChatEntry.join(theChatRoom, member);
        chatEntryRepository.save(chatEntry);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(chatEntry, ChatEntryDto.class);

    }

    @Override
    public List<ChatEntryDto> getChatEntries(String chatRoomId) {
        Optional<ChatRoom> optChatRoom = chatRoomRepository.findById(chatRoomId);

        if (optChatRoom.isEmpty()) {
            throw new IllegalArgumentException("채팅방을 찾을 수 없습니다.");
        }
        ChatRoom theChatRoom = optChatRoom.get();
        Iterable<ChatEntry> entries = chatEntryRepository.findAllByChatRoom(theChatRoom);
        List<ChatEntryDto> list = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        entries.forEach(e -> list.add(mapper.map(e, ChatEntryDto.class)) );
        return list;
    }
}
