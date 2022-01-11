package com.example.warehousemonitoringsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final Messages reportMessage;

    @Override
    public Messages getErrors() {
        checkStructure();
        checkMovements();
        checkRests();
        checkRequirements();
        return reportMessage;
    }

    private void checkStructure() {
        checkStructurePhis();
        checkStructureLog();
        checkStructureDiffLogPhis();
    }

    private void checkStructureDiffLogPhis() {
    }

    private void checkStructureLog() {
    }

    private void checkStructurePhis() {
    }

    private void checkRests() {
        checkRestsPhis();
        checkRestsLog();
        checkRestsDiffLogPhis();
    }

    private void checkRestsDiffLogPhis() {
    }

    private void checkRestsLog() {
        checkRestsLogClose();
        checkRestsLogExt();
    }

    private void checkRestsLogExt() {
    }

    private void checkRestsLogClose() {
    }

    private void checkRestsPhis() {

    }

    private void checkRequirements() {
    }

    private void checkMovements() {
        checkMovementsPhis();
        checkMovementsLog();
        checkMovementsDiffLogPhis();
    }

    private void checkMovementsDiffLogPhis() {
        reportMessage.add(reportMessage.getMess("checkMovementsDiffLogPhis", "OK", "проверка движений физика-логика"));
    }

    private void checkMovementsLog() {
        reportMessage.add(reportMessage.getMess("checkMovementsLog", "OK", "проверка движений по логическому складу"));
    }

    private void checkMovementsPhis() {
        reportMessage.add(reportMessage.getMess("checkMovementsPhis", "OK", "проверка движений по физическому складу"));
    }

    @Override
    public Object getState() {
        return null;
    }

    @Override
    public Object getReports() {
        return null;
    }

}
