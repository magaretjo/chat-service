# chat-service of eztalk 


- 채팅방 개설 : 임시 및 정회원 채팅방을 동시에 개설한다.
  - RequestBody : String communityID   
```sh
http :8088/chatRooms communityId="AAAA"    
```
-> 생성된 chatRoomId를 확인한다. 
   
- 채팅방 입장 : 주어진 채팅방에 최초 입장한다. 
  - RequestBody : String memberId, String nickName
```sh
 http :8088/chatRooms/ecdbd50a-e308-4e89-a9fc-1468013df73c/entries memberId="1234" nickName="magaretjo"    
```

- 채팅방 접속 : 주어진 채팅방에 접속한다.(채팅 활성화)
  - RequestBody : String memberId
```sh
 http :8088/chatRooms/ecdbd50a-e308-4e89-a9fc-1468013df73c/connect memberId="1234"   
```

- 채팅방 접속 : 주어진 채팅방에 접속 종료한다.(채팅 비활성화)
  - RequestBody : String memberId
```sh
 http :8088/chatRooms/ecdbd50a-e308-4e89-a9fc-1468013df73c/disconnect memberId="1234"   
```

- 채팅방 입장 멤버 조회 : 채팅방에 입장한 멤버들을 조회한다.
```sh
 http :8088/chatRooms/ecdbd50a-e308-4e89-a9fc-1468013df73c/entries
```
   
- 채팅방 퇴장 : 채팅방에서 퇴장한다. 
  - RequestBody : String memberId, String nickName
```sh
http DELETE :8088/chatRooms/ecdbd50a-e308-4e89-a9fc-1468013df73c/entries memberId="1234" nickName="magaretjo"      
```
