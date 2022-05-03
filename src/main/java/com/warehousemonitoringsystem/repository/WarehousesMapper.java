package com.warehousemonitoringsystem.repository;

import com.warehousemonitoringsystem.entity.Warehouse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WarehousesMapper implements RowMapper<Warehouse> {
    @Override
    public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Warehouse wh = new Warehouse();
        wh.setVn(rs.getInt("vn"));
        wh.setName(rs.getString("name"));
        wh.setMask(rs.getBoolean("mask"));
        return wh;
    }
}