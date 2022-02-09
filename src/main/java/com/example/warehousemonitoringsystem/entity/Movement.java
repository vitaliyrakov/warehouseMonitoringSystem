package com.example.warehousemonitoringsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Movement {
    private Date date;
    private Warehouse whFrom;
    private Warehouse whTo;
    private Nomen nomen;
    private int qty;
    private String wereNameFrom;
    private String wereNameTo;
    private String master;
    private Docum baseDocum;

}
