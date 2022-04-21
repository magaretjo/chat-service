package com.hea.eztalk.service;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.dto.ChatRoomDto;
import com.hea.eztalk.vo.RequestMember;
import com.hea.eztalk.vo.RequestChatRoom;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoom> createCommunityChatRooms(RequestChatRoom request) ;

    ChatRoomDto getChatRoom(String id);

    ChatEntryDto joinChatRoom(String id, RequestMember member);

    List<ChatEntryDto> getChatEntries(String chatRoomId);
}
