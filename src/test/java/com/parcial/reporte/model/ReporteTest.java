package com.parcial.reporte.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.parcial.reporte.model.Reporte;

public class ReporteTest {

    @Test
    void testBuilder() {
        Reporte reporte = Reporte.builder()
                .id_reporte(1L)
                .id_venta(100L)
                .descripcion("Reporte de prueba")
                .tipo("Test")
                .fecha_generacion(LocalDate.now())
                .build();
        
        assertEquals(1L, reporte.getId_reporte());
        assertEquals("Reporte de prueba", reporte.getDescripcion());
        assertNotNull(reporte.getFecha_generacion());
    }

    @Test
    void testEqualsAndHashCode() {
        Reporte reporte1 = Reporte.builder().id_reporte(1L).build();
        Reporte reporte2 = Reporte.builder().id_reporte(1L).build();
        Reporte reporte3 = Reporte.builder().id_reporte(2L).build();
        
        assertEquals(reporte1, reporte2);
        assertNotEquals(reporte1, reporte3);
        assertEquals(reporte1.hashCode(), reporte2.hashCode());
    }

    @Test
    void testToString() {
        Reporte reporte = Reporte.builder()
                .id_reporte(1L)
                .tipo("Ventas")
                .build();
        
        String toString = reporte.toString();
        assertTrue(toString.contains("Reporte"));
        assertTrue(toString.contains("id_reporte=1"));
        assertTrue(toString.contains("tipo=Ventas"));
    }
}