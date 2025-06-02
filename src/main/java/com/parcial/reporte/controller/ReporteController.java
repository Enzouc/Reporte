package com.parcial.reporte.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.reporte.model.Reporte;
import com.parcial.reporte.service.IReporteService;

@RestController
@RequestMapping("/api/phoenix_dinamic/reporte")
public class ReporteController {
	@Autowired
	private IReporteService service;
	
	@GetMapping("/findAll")
	public List<Reporte> obtenerTodos(){
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Reporte obtenerPorId(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/save")
	public Reporte generarReporte(@RequestBody Reporte reporte) {
		reporte.setFecha_generacion(LocalDate.now());
		service.save(reporte);
		return reporte;
	}
	@PutMapping("/actualizar/{id}")
	public Reporte actualizarReporte(@PathVariable("id")Long id, @RequestBody Reporte r) {
		Reporte reporte=service.findById(id);
		reporte.setTipo(r.getTipo());
		reporte.setDescripcion(r.getDescripcion());
		service.save(reporte);
		return reporte;
		
	}
}
