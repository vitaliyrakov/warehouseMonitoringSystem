package com.example.warehousemonitoringsystem.repository;

import com.example.warehousemonitoringsystem.entity.Movement;
import com.example.warehousemonitoringsystem.entity.Nomen;
import com.example.warehousemonitoringsystem.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepository {

    @Value("${dbfPath}")
    private String connString;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouseList = new ArrayList<>();

        try {
            Class.forName("com.hxtt.sql.dbf.DBFDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(connString);
            String sql = """
                    SELECT nomen.vn as nomenVn, nomen.polet, nomen.nom_n, sklad.kwere, sklad.qty, ware.name, ware.vn as wareVn, ware.mask
                    FROM sklad
                    INNER JOIN ware ON ware.vn = sklad.kwere
                    INNER JOIN nomen ON nomen.vn = sklad.knomen""";
//                    ORDER BY wareVn """;
            //                    "WHERE ware.mask  AND sklad.qty <> 0 ";

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            Warehouse wh = null;
            Nomen nomen;
            while (resultSet.next()) {
                String newWareVn = resultSet.getString("name");
                wh = warehouseList.stream().filter(w -> w.getName().equals(newWareVn)).findFirst().orElse(new Warehouse());
                if (wh.getName() == null) {
                    //(wh == null || wh.getId() != resultSet.getInt("wareVn")) {
//                    wh = new Warehouse();
                    wh.setName(resultSet.getString("name"));
                    wh.setId(resultSet.getInt("wareVn"));
                    wh.setMask(resultSet.getBoolean("mask"));
                    warehouseList.add(wh);
                }

                nomen = new Nomen();
                wh.add(nomen);
                nomen.setName(resultSet.getString("polet"));
                nomen.setId(resultSet.getInt("nomenVn"));
                nomen.setQty(resultSet.getInt("qty"));
                nomen.setNom_n(resultSet.getString("nom_n"));
                nomen.add(wh);
//                wh.setQty(resultSet.getInt("qty"));

            }
            resultSet.close();
            stmt.close();
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return warehouseList;
    }

    public List<Movement> getMovementsByNomen(int id) {
        String sql = """
                SELECT include.k7 as qty, inoutskl.datax as date, inoutskl.master, include.dtcreate as dtCreate, ware.name as nameFrom, wTo.name as nameTo
                FROM inoutskl
                INNER JOIN include ON include.di = inoutskl.vn
                INNER JOIN ware ON ware.vn = inoutskl.kwere
                INNER JOIN ware wTo ON wTo.vn = inoutskl.kwere1
                INNER JOIN nomen ON nomen.vn = include.knomen and nomen.vn== """+id;
        return jdbcTemplate.query(sql, new MovementsMapper());
//                .stream().findAny().orElse(new Movement()).;
    }

//    public int getRestByNomenWarehouse(int nomenId, int warehouseId) {
//
//        return 1;
//    }
}
