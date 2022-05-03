package com.warehousemonitoringsystem.repository;

import com.warehousemonitoringsystem.entity.Movement;
import com.warehousemonitoringsystem.entity.Nomen;
import com.warehousemonitoringsystem.entity.Warehouse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class ReportRepository {

    @Value("${dbfPath}")
    private String connString;
    private final JdbcTemplate jdbcTemplate;

    public ReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movement> getMovementsByNomen(int id) {
        String sql = """
                SELECT include.k7 as qty, inoutskl.datax as date, inoutskl.master, include.dtcreate as dtCreate, ware.name as nameFrom, wTo.name as nameTo
                FROM inoutskl
                INNER JOIN include ON include.di = inoutskl.vn
                INNER JOIN ware ON ware.vn = inoutskl.kwere
                INNER JOIN ware wTo ON wTo.vn = inoutskl.kwere1
                INNER JOIN nomen ON nomen.vn = include.knomen and nomen.vn= """ + id;

        return jdbcTemplate.query(sql, new MovementsMapper());
//        todo проверить эту версию
//                .stream().findAny().orElse(new Movement()).;
    }

    public List<Warehouse> getAllWarehouses() {
        return jdbcTemplate.query("SELECT vn, name, mask FROM ware",
                        new BeanPropertyRowMapper(Warehouse.class)).stream()
                .sorted(Comparator.comparing(Warehouse::getName)).toList();
    }

    public List<Nomen> getAllNomens() {
        return jdbcTemplate.query("SELECT polet, nom_n, vn FROM nomen",
                        new BeanPropertyRowMapper(Nomen.class)).stream()
                .sorted(Comparator.comparing(Nomen::getPolet)).toList();
    }

    public List<Warehouse> getAllWarehouses_old() {
        List<Warehouse> warehouseList = new ArrayList<>();
//        try {
//            Class.forName("com.hxtt.sql.dbf.DBFDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
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
                    wh.setVn(resultSet.getInt("wareVn"));
                    wh.setMask(resultSet.getBoolean("mask"));
                    warehouseList.add(wh);
                }

                nomen = new Nomen();
                wh.add(nomen);
                nomen.setPolet(resultSet.getString("polet"));
                nomen.setVn(resultSet.getInt("nomenVn"));
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
}
