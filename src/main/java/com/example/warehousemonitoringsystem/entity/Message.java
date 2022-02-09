package com.example.warehousemonitoringsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String checkType;
    private String Mess;
    private String Comment;

}
