package com.parcial.reporte.service;

import java.util.List;

import com.parcial.reporte.model.Reporte;

public interface IReporteService {
	List<Reporte> findAll();
	Reporte findById(Long id);
	void save(Reporte reporte);
	void deleteById(Long id);
}
