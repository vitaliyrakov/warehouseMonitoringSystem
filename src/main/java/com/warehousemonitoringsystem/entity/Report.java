package com.warehousemonitoringsystem.entity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Report {
    private List<Message> messageList = new ArrayList<>();

    public void add(Message mess) {
        messageList.add(mess);
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void clear() {
        messageList.clear();
    }
}
