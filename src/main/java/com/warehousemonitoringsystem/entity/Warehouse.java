package com.warehousemonitoringsystem.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class Warehouse {
    @NotNull
    public int vn;
    @NotBlank
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
