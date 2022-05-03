package com.warehousemonitoringsystem.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class Nomen {
    @NotBlank
    private String polet;
    @NotBlank
    private String nom_n;
    @NotNull
    private int vn;
    private int qty;

//    public Nomen(String polet, String nom_n, int vn, int qty, List<Warehouse> whList, List<Movement> whMovement) {
//        this.polet = polet;
//        this.nom_n = nom_n;
//        this.vn = vn;
//        this.qty = qty;
//        this.whList = whList;
//        this.whMovement = whMovement;
//    }

    List<Warehouse> whList = new ArrayList<>();
    List<Movement> whMovement = new ArrayList<>();

    public void add(Warehouse wh) {
        whList.add(wh);
    }

}
