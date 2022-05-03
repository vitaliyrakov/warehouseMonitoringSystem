package com.warehousemonitoringsystem.service;

import com.warehousemonitoringsystem.entity.Movement;
import com.warehousemonitoringsystem.entity.Nomen;
import com.warehousemonitoringsystem.entity.Report;
import com.warehousemonitoringsystem.entity.Warehouse;

import java.util.List;

public interface ReportService {
    List<Warehouse> getState();

    List<Warehouse> getAllWarehouse();

    Warehouse getWarehouseByID(int id);

    Report getErrors();

    List<Warehouse> findByName(String id);

    Report getReports();

    List<Movement> getMovementsByNomen(int id);

    List<Nomen> getAllNomens();
}
