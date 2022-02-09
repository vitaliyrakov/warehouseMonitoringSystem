package com.example.warehousemonitoringsystem.controller;

import com.example.warehousemonitoringsystem.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ReportController {

    private final ReportService reportService;

    @GetMapping()
    public String getState(Model model) {
        model.addAttribute("warehouseList", reportService.getAllWarehouse());
        return "warehouseList";
    }

    @GetMapping("/reports")
    public String getReports(Model model) {
        model.addAttribute("warehouseList", reportService.getAllWarehouse());
        return "reports";
    }

    @GetMapping("/errors")
    public String getErrors(Model model) {
        model.addAttribute("reportErrors", reportService.getErrors());
        return "errorsList";
    }

    @GetMapping("/reports/{name}")
    public String getReport(@PathVariable("name") String name, Model model) {
        model.addAttribute("warehouseList", reportService.findByName(name));
        return "errors";
    }

    @GetMapping("/warehouse/{id}")
    public String getWarehouse(@PathVariable("id") int id, Model model) {
        model.addAttribute("warehouse", reportService.getWarehouseByID(id));
        return "nomenList";
    }

    @GetMapping("/movement/{id}")
    public String getMovements(@PathVariable("id") int id, Model model) {
        model.addAttribute("movements", reportService.getMovementsByNomen(id));
        return "movementList";
    }

}
