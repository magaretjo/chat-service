package com.hea.eztalk.service;

import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.dto.ChatRoomDto;
import com.hea.eztalk.vo.RequestMember;
import com.hea.eztalk.vo.RequestChatRoom;

import java.util.List;

public interface ChatService {

    List<ChatRoom> createCommunityChatRooms(String communityId) ;

    ChatRoom getChatRoom(String id);
    ChatRoom getTemporaryChatRoom(String communityId);
    ChatRoom getRegularChatRoom(String communityId);

    ChatEntry joinChatRoom(String id, RequestMember member);
    ChatEntry connectChatRoom(String chatRoomId, String memberId);
    ChatEntry disconnectChatRoom(String chatRoomId, String memberId);
    List<ChatEntry> getChatEntries(String chatRoomId);

    void removeEntry(String chatRoomId, String memberId);
}
