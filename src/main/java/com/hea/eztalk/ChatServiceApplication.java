package com.hea.eztalk;


import com.hea.eztalk.domain.chatentry.ChatEntry;
import com.hea.eztalk.domain.ChatEntryRepository;
import com.hea.eztalk.domain.chatroom.ChatRoom;
import com.hea.eztalk.domain.ChatRoomRepository;
import com.hea.eztalk.domain.chatroom.TemporaryChatRoom;
import org.modelmapper.ModelMapper;
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

    }

}
