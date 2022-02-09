package com.example.warehousemonitoringsystem.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Warehouse {
    public int id;
    public String Name;
    public Boolean mask;

    List<Nomen> nomenList = new ArrayList<>();

    public void add(Nomen nomen) {
        nomenList.add(nomen);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
