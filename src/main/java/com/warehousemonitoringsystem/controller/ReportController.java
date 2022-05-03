package com.warehousemonitoringsystem.controller;

import com.warehousemonitoringsystem.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;

@Controller
@Validated
@RequiredArgsConstructor
@RequestMapping("/")
//@RolesAllowed({"ADMIN"})
public class ReportController {

    private final ReportService reportService;

    @GetMapping()
    public String getRoot() {
        return "redirect:/warehouse";
    }

    @GetMapping("/warehouses")
    public String getWarehouses(Model model) {
        model.addAttribute("warehouseList", reportService.getAllWarehouse());
        return "warehouseList";
    }

    @GetMapping("/nomens")
    public String getNomens(Model model) {
        model.addAttribute("nomensList", reportService.getAllNomens());
        return "nomensList";
    }

    @GetMapping("/warehouse/{id}")
    public String getWarehouse(@PathVariable("id") @Min(1) int id, Model model) {
        model.addAttribute("warehouse", reportService.getWarehouseByID(id));
        return "nomenList";
    }

    @GetMapping("/errors")
    public String getErrors(Model model) {
        model.addAttribute("reportErrors", reportService.getErrors());
        return "errorsList";
    }

    @GetMapping("/reports")
    public String getReports(Model model) {
        model.addAttribute("warehouseList", reportService.getAllWarehouse());
        return "reports";
    }

    @GetMapping("/reports/{name}")
    public String getReport(@PathVariable("name") String name, Model model) {
        model.addAttribute("warehouseList", reportService.findByName(name));
        return "errors";
    }

    @GetMapping("/movement/{id}")
    public String getMovements(@PathVariable("id") @Min(1) int id, Model model) {
        model.addAttribute("movements", reportService.getMovementsByNomen(id));
        return "movementList";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
