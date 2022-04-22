package com.hea.eztalk.controller;

import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.dto.ChatRoomDto;
import com.hea.eztalk.service.ChatService;
import com.hea.eztalk.vo.RequestMember;
import com.hea.eztalk.vo.RequestChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class ChatServiceController {

    ChatService chatService;

    @Autowired
    public ChatServiceController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 채팅방 개설 : 임시 및 정회원 채팅방을 동시에 만든다.
     * @param request {String : communityId}
     * @return
     */
    @PostMapping("/chatRooms")
    public ResponseEntity<List<ChatRoom>> createChatRooms(@RequestBody RequestChatRoom request) {
        System.out.println("#### request of createChatRooms : " + request.getCommunityId());
        List<ChatRoom> chatRooms = chatService.createCommunityChatRooms(request.getCommunityId());
        return ResponseEntity.status(HttpStatus.CREATED).body(chatRooms);   // return with 201
    }

    /**
     * 채팅방 조회
     */
    @GetMapping("/chatRooms/{chatRoomId}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable("chatRoomId") String chatRoomId) {
        ChatRoom chatRoom = chatService.getChatRoom(chatRoomId);

        if (chatRoom == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(chatRoom);
        else
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom);
    }

    /**
     * 정회원 채팅방 조회
     * @param request {String : communityId}
     * @return
     */
    @GetMapping("/chatRooms/regularChatRoom")
    public ResponseEntity<ChatRoom> getRegularChatRoom(@RequestBody RequestChatRoom request) {
        System.out.println("#### request of createChatRooms : " + request.getCommunityId());
        ChatRoom chatRoom = chatService.getRegularChatRoom(request.getCommunityId());

        if (chatRoom == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(chatRoom);
        else
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom);
    }

    /**
     * 채팅방 입장
     */
    @PostMapping("/chatRooms/{chatRoomId}/entries")
    public ResponseEntity<ChatEntry> joinEntry(@PathVariable("chatRoomId") String chatRoomId,
                                              @RequestBody RequestMember member) {
        System.out.println("#### request of joinEntry : " + member + " joined '" + chatRoomId + "'");
        try {
            ChatEntry chatEntry = chatService.joinChatRoom(chatRoomId, member);
            return ResponseEntity.status(HttpStatus.CREATED).body(chatEntry);   // return with 201
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * 채팅망 참여 Entry 조회
     */
    @GetMapping("/chatRooms/{chatRoomId}/entries")
    public ResponseEntity<List<ChatEntry>> getEntries(@PathVariable("chatRoomId") String chatRoomId) {
        System.out.println("#### request of getEntries in ('" + chatRoomId + "')");
        List<ChatEntry> list = chatService.getChatEntries(chatRoomId);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     *  채팅방 퇴장
     */
    @DeleteMapping("/chatRooms/{chatRoomId}/entries")
    public String exitEntry(@PathVariable("chatRoomId") String chatRoomId,
                            @RequestBody RequestMember member) {
        System.out.println("#### request of exitEntry : " + member + " leaves '" + chatRoomId + "')");
        chatService.removeEntry(chatRoomId, member.getMemberId());

        return "퇴장하셨습니다.";
    }

    /**
     *  채팅방 접속
     */
    @PostMapping("/chatRooms/{chatRoomId}/connect")
    public ChatEntry connectChatroom(@PathVariable("chatRoomId") String chatRoomId,
                          @RequestBody RequestMember member) {
        System.out.println("#### request of connectChatroom : " + member + " login chatRoom('" + chatRoomId + "')");
        ChatEntry entry = chatService.connectChatRoom(chatRoomId, member.getMemberId());

        return entry;
    }

    /**
     * 정회원 채팅방 접속종료
     */
    @PostMapping("/chatRooms/{chatRoomId}/disconnect")
    public ChatEntry disconnectChatroom(@PathVariable("chatRoomId") String chatRoomId,
                                  @RequestBody RequestMember member) {
        System.out.println("#### request of disconnectChatroom : " + member + " logout '" + chatRoomId + "')");
        ChatEntry entry = chatService.disconnectChatRoom(chatRoomId, member.getMemberId());

        return entry;
    }

    /**
     * 정회원 채팅방 차단
     */

    /**
     * 정회원 채팅방 차단해제
     */

}
