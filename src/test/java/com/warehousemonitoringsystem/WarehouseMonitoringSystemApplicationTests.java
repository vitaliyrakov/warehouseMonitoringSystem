package com.warehousemonitoringsystem;

import com.warehousemonitoringsystem.controller.ReportController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
class WarehouseMonitoringSystemApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnAllWarehouses() throws Exception {
        this.mvc.perform(get("/warehouses")).andExpect(status().isOk());
    }
    @Test
    public void shouldReturnAllNomens() throws Exception {
        this.mvc.perform(get("/nomens")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnWarehouse_ID_10() throws Exception {
        this.mvc.perform(get("/warehouse/10")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Бpак")));
//        andDo(print()).
    }


//    @Autowired
//    private ReportController controller;

//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(controller).isNotNull();
//    }

//    @Test
//    void contextLoads() {
//    }

}
