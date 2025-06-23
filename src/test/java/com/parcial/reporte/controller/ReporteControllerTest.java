package com.parcial.reporte.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcial.reporte.controller.ReporteController;
import com.parcial.reporte.model.Reporte;
import com.parcial.reporte.service.IReporteService;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IReporteService reporteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Reporte reporte;

    @BeforeEach
    void setUp() {
        reporte = Reporte.builder()
                .id_reporte(1L)
                .id_venta(100L)
                .descripcion("Reporte de ventas")
                .tipo("Ventas")
                .fecha_generacion(LocalDate.now())
                .build();
    }

    @Test
    void testObtenerTodos() throws Exception {
        List<Reporte> reportes = Arrays.asList(reporte);
        when(reporteService.findAll()).thenReturn(reportes);
        
        mockMvc.perform(get("/api/phoenix_dinamic/reporte/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_reporte").value(1))
                .andExpect(jsonPath("$[0].tipo").value("Ventas"));
        
        verify(reporteService, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() throws Exception {
        when(reporteService.findById(1L)).thenReturn(reporte);
        
        mockMvc.perform(get("/api/phoenix_dinamic/reporte/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_venta").value(100))
                .andExpect(jsonPath("$.descripcion").value("Reporte de ventas"));
        
        verify(reporteService, times(1)).findById(1L);
    }

    @Test
    void testGenerarReporte() throws Exception {
        doNothing().when(reporteService).save(any(Reporte.class));
        
        mockMvc.perform(post("/api/phoenix_dinamic/reporte/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha_generacion").exists());
        
        verify(reporteService, times(1)).save(any(Reporte.class));
    }

    @Test
    void testActualizarReporte() throws Exception {
        Reporte reporteActualizado = Reporte.builder()
                .tipo("Ventas Actualizado")
                .descripcion("Nueva descripción")
                .build();

        when(reporteService.findById(1L)).thenReturn(reporte);
        doNothing().when(reporteService).save(any(Reporte.class));
        
        mockMvc.perform(put("/api/phoenix_dinamic/reporte/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporteActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Ventas Actualizado"))
                .andExpect(jsonPath("$.descripcion").value("Nueva descripción"));
        
        verify(reporteService, times(1)).findById(1L);
        verify(reporteService, times(1)).save(any(Reporte.class));
    }
}