package com.example.warehousemonitoringsystem.service;

import com.example.warehousemonitoringsystem.entity.Message;
import com.example.warehousemonitoringsystem.entity.Movement;
import com.example.warehousemonitoringsystem.entity.Report;
import com.example.warehousemonitoringsystem.entity.Warehouse;
import com.example.warehousemonitoringsystem.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final Report report;
    private final ReportRepository repository;

    @Override
    public Report getErrors() {
        report.clear();

        checkStructure();
        checkMovements();
        checkRests();
        checkRequirements();
        return report;
    }

    private void checkStructure() {
        checkStructurePhis();
        checkStructureLog();
        checkStructureDiffLogPhis();
    }

    private void checkStructurePhis() {
        report.add(new Message("checkStructurePhis", "OK", "структура физического склада"));
    }

    private void checkStructureLog() {
        report.add(new Message("checkStructureLog", "OK", "структура логического склада"));
    }

    private void checkStructureDiffLogPhis() {
        report.add(new Message("checkStructureDiffLogPhis", "OK", "различия структуры физика-логика"));
    }

    private void checkMovements() {
        checkMovementsPhis();
        checkMovementsLog();
        checkMovementsDiffLogPhis();
    }

    private void checkMovementsPhis() {
        report.add(new Message("checkMovementsPhis", "OK", "движения по физическому складу"));
    }

    private void checkMovementsLog() {
        report.add(new Message("checkMovementsLog", "OK", "движения по логическому складу"));
    }

    private void checkMovementsDiffLogPhis() {
        report.add(new Message("checkMovementsDiffLogPhis", "OK", "различия движений физика-логика"));
    }

    private void checkRests() {
        checkRestsPhis();
        checkRestsLog();
        checkRestsDiffLogPhis();
    }

    private void checkRestsPhis() {
        report.add(new Message("checkRestsPhis", "OK", "остатки по физическому складу"));
    }

    private void checkRestsLog() {
        checkRestsMoveLog();
        checkRestsLogClose();
        checkRestsLogExt();
    }

    private List<Warehouse> checkRestsMoveLog() {
        List<Warehouse> whList = repository.getAllWarehouses().stream()
//                .filter(w -> w.getQty() > 0 && w.mask)
                .filter(w -> w.mask)
                .toList();

        report.add(new Message("checkRestsMoveLog", whList.isEmpty() ? "OK" : "", "остатки-движения по логическим складам"));
        return whList;
    }

    private List<Warehouse> checkRestsLogClose() {
        List<Warehouse> whList = repository.getAllWarehouses().stream()
//                .filter(w -> w.getQty() > 0 && w.mask)
                .filter(w -> w.mask)
                .toList();
        //                    "WHERE ware.mask  AND sklad.qty <> 0 ";

        report.add(new Message("checkRestsLogClose", whList.isEmpty() ? "OK" : "", "остатки по закрытым логическим складам"));
        return whList;
    }

    private List<Warehouse> checkRestsLogExt() {
        List<Warehouse> whList = repository.getAllWarehouses().stream()
//                .filter(w -> w.getQty() > 0 && w.mask)
                .filter(w -> w.mask)
                .toList();

        report.add(new Message("checkRestsLogExt", whList.isEmpty() ? "OK" : "", "остатки по удаленным логическим складам"));
        return whList;
    }

    private void checkRestsDiffLogPhis() {
        report.add(new Message("checkRestsDiffLogPhis", "OK", "различия остатков по физическим-логическим складам"));
    }

    private void checkRequirements() {
        report.add(new Message("checkRequirements", "OK", "требования"));
    }

    @Override
    public List<Warehouse> getState() {
        return repository.getAllWarehouses().stream()
                .sorted(Comparator.comparing(Warehouse::getName)).toList();
    }

    @Override
    public List<Warehouse> getAllWarehouse() {
        return repository.getAllWarehouses().stream()
        .sorted(Comparator.comparing(Warehouse::getName)).toList();
    }

    @Override
    public Report getReports() {
        return report;
    }

    @Override
    public List<Warehouse> findByName(String name) {
        if (name.equals("checkRestsLogClose")) {
            return checkRestsLogClose();
        } else {
            return repository.getAllWarehouses().stream()
                    .sorted(Comparator.comparing(Warehouse::getName)).toList();
        }
    }

    @Override
    public Warehouse getWarehouseByID(int id) {
        Optional<Warehouse> wh = getAllWarehouse().stream().filter(w -> w.getId() == id).findAny();
        return wh.orElse(new Warehouse());
    }

    @Override
    public List<Movement> getMovementsByNomen(int id) {
//        List<Movement> mv = repository.getMovementsByNomen(id);
        return repository.getMovementsByNomen(id);
    }

//    public int getRestByNomenWarehouse(int nomenId, int warehouseId) {
//        int rest = repository.getRestByNomenWarehouse(nomenId, warehouseId);
//        return rest;
//    }

}
