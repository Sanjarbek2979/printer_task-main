package com.example.printer_task.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessage {
    private  String content;
    private String sender;
    private MessageType type;

    public enum MessageType{
        CHAT,LEAVE,JOIN
    }
}
