package com.warehousemonitoringsystem.repository;

import com.warehousemonitoringsystem.entity.Movement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovementsMapper implements RowMapper<Movement> {
    @Override
    public Movement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movement mv = new Movement();
        mv.setQty(rs.getInt("qty"));
        mv.setWereNameFrom(rs.getString("nameFrom"));
        mv.setWereNameTo(rs.getString("nameTo"));
        mv.setMaster(rs.getString("master"));
        mv.setDate(rs.getDate("date"));
        return mv;
//        return MovementBuilder
//                .setQty(rs.getInt("qty"))
//                .setWereNameFrom(rs.getString("nameFrom"))
//                .setWereNameTo(rs.getString("nameTo"))
//                .setMaster(rs.getString("master"))
//                .setDate(rs.getDate("date"))
//                .build();
    }
}
