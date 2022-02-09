package com.example.warehousemonitoringsystem.service;

import com.example.warehousemonitoringsystem.entity.Movement;
import com.example.warehousemonitoringsystem.entity.Report;
import com.example.warehousemonitoringsystem.entity.Warehouse;

import java.util.List;

public interface ReportService {
    List<Warehouse> getState();

    List<Warehouse> getAllWarehouse();

    Warehouse getWarehouseByID(int id);

    Report getErrors();

    List<Warehouse> findByName(String id);

    Report getReports();

    List<Movement> getMovementsByNomen(int id);
}
