package com.example.warehousemonitoringsystem.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Nomen {
    private String name;
    private String nom_n;
    private int id;
    private int qty;

    List<Warehouse> whList = new ArrayList<>();
    List<Movement> whMovement = new ArrayList<>();

    public void add(Warehouse wh) {
        whList.add(wh);
    }

}
