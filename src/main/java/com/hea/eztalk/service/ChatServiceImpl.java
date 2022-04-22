package com.hea.eztalk.service;

import com.hea.eztalk.domain.ChatEntryRepository;
import com.hea.eztalk.domain.ChatRoomRepository;
import com.hea.eztalk.domain.RegularChatRoomRepository;
import com.hea.eztalk.domain.TemporaryChatRoomRepository;
import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.*;
import com.hea.eztalk.vo.RequestMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    ChatRoomRepository chatRoomRepository;
    TemporaryChatRoomRepository temporaryChatRoomRepository;
    RegularChatRoomRepository regularChatRoomRepository;
    ChatEntryRepository chatEntryRepository;

    @Autowired
    public ChatServiceImpl(ChatRoomRepository chatRoomRepository,
                           TemporaryChatRoomRepository temporaryChatRoomRepository,
                           RegularChatRoomRepository regularChatRoomRepository,
                           ChatEntryRepository chatEntryRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.temporaryChatRoomRepository = temporaryChatRoomRepository;
        this.regularChatRoomRepository = regularChatRoomRepository;
        this.chatEntryRepository = chatEntryRepository;
    }

    protected ChatRoom createTemporaryChatRoom(String communityId) {
        TemporaryChatRoom chatRoom = new TemporaryChatRoom(communityId);
        chatRoomRepository.save(chatRoom);
        log.debug("created TemporaryChatRoom >> " + chatRoom);

        return chatRoom;
    }

    protected ChatRoom createRegularChatRoom(String communityId) {
        RegularChatRoom chatRoom = new RegularChatRoom(communityId);
        chatRoomRepository.save(chatRoom);
        log.debug("created RegularChatRoom >> " + chatRoom);

        return chatRoom;
    }

    // 커뮤니티 채팅방 개설
    @Override
    public List<ChatRoom> createCommunityChatRooms(String communityId) {

        if ( getTemporaryChatRoom(communityId) != null || getRegularChatRoom(communityId) != null ) {
            throw new DuplicateKeyException("채팅방이 이미 존재합니다.");
        }
        ChatRoom chatRoom1 = createTemporaryChatRoom(communityId);
        ChatRoom chatRoom2 = createRegularChatRoom(communityId);

        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRooms.add(chatRoom1);
        chatRooms.add(chatRoom2);

        return chatRooms;
    }

    @Override
    public ChatRoom getChatRoom(String chatRoomId) {
        Optional<ChatRoom> optChatRoom = chatRoomRepository.findById(chatRoomId);

        if (optChatRoom.isEmpty()) return null;

        return optChatRoom.get();

//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        return mapper.map(optChatRoom.get(), ChatRoomDto.class);
    }

    @Override
    public ChatRoom getTemporaryChatRoom(String communityId) {
        return mapChatRoom(temporaryChatRoomRepository.findByCommunityId(communityId));
    }

    @Override
    public ChatRoom getRegularChatRoom(String communityId) {
        return mapChatRoom(regularChatRoomRepository.findByCommunityId(communityId));
    }

    private ChatRoom mapChatRoom(Optional<ChatRoom> opt) {

        return (opt.isEmpty()) ? null : opt.get();

//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
//        ChatRoomDto chatRoomDto = mapper.map(chatRoom, ChatRoomDto.class);
//        return chatRoomDto;
    }

    //----------------------------------------------------------------------
    //  APIs for Chat Entries
    @Override
    public ChatEntry joinChatRoom(String chatRoomId, RequestMember member) {
        ChatEntry entry = getChatEntry( chatRoomId,  member.getMemberId());
        if (entry != null) {
            log.error("채팅방에 이미 들어와 있습니다. :: " + member + " in chatRoom='" + chatRoomId + "'");
            return entry;
        }

        Optional<ChatRoom> optChatRoom = chatRoomRepository.findById(chatRoomId);
        if (optChatRoom.isEmpty()) {
            log.error("채팅방을 찾을 수 없습니다 :: chatRoom='" + chatRoomId + "'");
            throw new IllegalArgumentException("채팅방을 찾을 수 없습니다.");
        }
        ChatRoom theChatRoom = optChatRoom.get();
        ChatEntry chatEntry = ChatEntry.join(theChatRoom, member);
        chatEntryRepository.save(chatEntry);

//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
//        mapper.map(chatEntry, ChatEntryDto.class);
        return chatEntry;

    }

    @Override
    public List<ChatEntry> getChatEntries(String chatRoomId) {
        List<ChatEntry> list = new ArrayList<>();
        Iterable<ChatEntry> entries = chatEntryRepository.findAllByChatRoom(getChatRoom(chatRoomId));
        entries.forEach(e -> list.add(e));
//        list.addAll(getChatRoom(chatRoomId).getChatEntries());
        return list;
    }

    @Override
    public ChatEntry connectChatRoom(String chatRoomId, String memberId) {
        ChatEntry entry = getChatEntry( chatRoomId,  memberId);
        if (entry == null) {
            log.info("Cannot find entry by memberId='"+ memberId+"' in chatRoom('" + chatRoomId + "')");
            return null;
        } else {
            entry.activate();
            chatEntryRepository.save(entry);
            return entry;
        }
    }

    @Override
    public ChatEntry disconnectChatRoom(String chatRoomId, String memberId) {
        ChatEntry entry = getChatEntry( chatRoomId,  memberId);
        if (entry == null) {
            log.info("Cannot find entry by memberId='"+ memberId+"' in chatRoom('" + chatRoomId + "')");
            return null;
        } else {
            entry.deactivate();
            chatEntryRepository.save(entry);
            return entry;
        }
    }

    @Override
    public void removeEntry(String chatRoomId, String memberId) {
        ChatEntry entry = getChatEntry( chatRoomId,  memberId);
        if (entry == null) {
            log.info("Cannot remove entry by memberId='" + memberId + "' in chatRoom('" + chatRoomId + "')");
        } else {
            chatEntryRepository.delete(entry);
        }
    }

    private ChatEntry getChatEntry(String chatRoomId, String memberId) {

        List<ChatEntry> list = new ArrayList<>();
        Iterable<ChatEntry> entries = chatEntryRepository
                                        .findByChatRoomAndMemberId( getChatRoom(chatRoomId), memberId);
        entries.forEach(e -> list.add(e));
        int idx = list.size();
        return idx == 0 ? null : list.get(idx-1);
    }


}
