package com.example.warehousemonitoringsystem.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class Messages {
    private List<Message> messageList=new ArrayList<>();

    class Message {
        private String checkType;
        private String Mess;
        private String Comment;

        Message(String checkType, String Mess, String Comment){
            this.checkType = checkType;
            this.Mess = Mess;
            this.Comment = Comment;
        }

        public String getCheckType() {
            return checkType;
        }

        public String getMess() {
            return Mess;
        }

        public String getComment() {
            return Comment;
        }
    }

    public Message getMess(String p1, String p2, String p3){
        return new Message(p1, p2, p3);
    }

    public void add(Message mess){
        messageList.add(mess);
    }
}
