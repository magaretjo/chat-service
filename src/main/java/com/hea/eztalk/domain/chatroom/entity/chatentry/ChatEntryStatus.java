package com.hea.eztalk.domain.chatroom.entity.chatentry;

public enum ChatEntryStatus {
    READY,             // 채팅방에 처음 입장 (채팅창에서 일단 말을 하면 READY --> ACTIVATE 됨 )
    ACTIVATE,           // 채팅중 ( 채팅창에 접속하면 ACTIVATE )
    DEACTIVATE,         // 채팅창을 떠남
    BLOCKED       // 채팅 차단됨. 차단해제시 READY 상태
}
