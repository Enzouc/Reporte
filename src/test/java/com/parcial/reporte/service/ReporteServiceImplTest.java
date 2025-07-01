package com.parcial.reporte.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parcial.reporte.model.Reporte;
import com.parcial.reporte.repository.IReporteRepository;
import com.parcial.reporte.service.ReporteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceImplTest {

    @Mock
    private IReporteRepository repository;

    @InjectMocks
    private ReporteServiceImpl reporteService;

    private Reporte reporte1;
    private Reporte reporte2;

    @BeforeEach
    void setUp() {
        reporte1 = Reporte.builder()
                .id_reporte(1L)
                .id_venta(100L)
                .descripcion("Reporte de ventas Q1")
                .tipo("Ventas")
                .fecha_generacion(LocalDate.now())
                .build();

        reporte2 = Reporte.builder()
                .id_reporte(2L)
                .id_venta(200L)
                .descripcion("Reporte de inventario")
                .tipo("Inventario")
                .fecha_generacion(LocalDate.now().minusDays(1))
                .build();
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(reporte1, reporte2));
        
        List<Reporte> result = reporteService.findAll();
        
        assertEquals(2, result.size());
        assertEquals("Ventas", result.get(0).getTipo());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Existente() {
        when(repository.findById(1L)).thenReturn(Optional.of(reporte1));
        
        Reporte result = reporteService.findById(1L);
        
        assertEquals(100L, result.getId_venta());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NoExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> reporteService.findById(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void testSave() {
    Reporte reporte = new Reporte();    
        when(repository.save(any(Reporte.class))).thenReturn(reporte); 
        reporteService.save(reporte);
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(1L);
        
        reporteService.deleteById(1L);
        
        verify(repository, times(1)).deleteById(1L);
    }

}