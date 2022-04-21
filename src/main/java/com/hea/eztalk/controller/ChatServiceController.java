package com.hea.eztalk.controller;

import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.dto.ChatEntryDto;
import com.hea.eztalk.dto.ChatRoomDto;
import com.hea.eztalk.service.ChatRoomService;
import com.hea.eztalk.vo.RequestMember;
import com.hea.eztalk.vo.RequestChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ChatServiceController {

    ChatRoomService chatRoomService;

    @Autowired
    public ChatServiceController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    /**
     * 채팅방 개설 : 임시 및 정회원 채팅방을 동시에 만든다.
     */
    @PostMapping("/chatRooms")
    public ResponseEntity<List<ChatRoom>> createChatRooms(@RequestBody RequestChatRoom request) {
        System.out.println("#### request of createChatRooms : " + request);
        List<ChatRoom> chatRooms = chatRoomService.createCommunityChatRooms(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatRooms);   // return with 201
    }

    /**
     * 채팅방 조회
     */
    @GetMapping("/chatRooms/{id}")
    public ResponseEntity<ChatRoomDto> getChatRoom(@PathVariable("id") String id) {
        ChatRoomDto chatRoom = chatRoomService.getChatRoom(id);

        if (chatRoom == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(chatRoom);
        else
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom);
    }

    /**
     * 정회원 채팅방 입장
     */
    @PostMapping("/chatRooms/{id}/entries")
    public ResponseEntity<ChatEntryDto> joinEntry(@PathVariable("id") String id,
                                                  @RequestBody RequestMember request) {
        System.out.println("#### request of joinEntry : " + request + " to '" + id + "'");
        try {
            ChatEntryDto chatEntry = chatRoomService.joinChatRoom(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(chatEntry);   // return with 201
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/chatRooms/{id}/entries")
    public ResponseEntity<List<ChatEntryDto>> getEntries(@PathVariable("id") String id) {
        System.out.println("#### request of getEntries in ('" + id + "')");
        List<ChatEntryDto> list = chatRoomService.getChatEntries(id);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * 정회원 채팅방 퇴장
     */

    /**
     * 정회원 채팅방 접속
     */

    /**
     * 정회원 채팅방 접속종료
     */

    /**
     * 정회원 채팅방 차단
     */

    /**
     * 정회원 채팅방 차단해제
     */
}
