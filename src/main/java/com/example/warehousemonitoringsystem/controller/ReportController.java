package com.example.warehousemonitoringsystem.controller;

import com.example.warehousemonitoringsystem.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ReportController {

    private final ReportService reportService;

    @GetMapping()
    public String getState(Model model) {
        model.addAttribute("state", reportService.getState());
        return "state";
    }

    @GetMapping("/errors")
    public String getErrors(Model model) {
        model.addAttribute("errors", reportService.getErrors());
        return "errors";
    }

    @GetMapping("/reports")
    public String getReports(Model model) {
        model.addAttribute("errors", reportService.getReports());
        return "reports";
    }
}
