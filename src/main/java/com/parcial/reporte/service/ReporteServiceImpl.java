package com.parcial.reporte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.reporte.model.Reporte;
import com.parcial.reporte.repository.IReporteRepository;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IReporteRepository repository;
	@Override
	public List<Reporte> findAll() {
		return repository.findAll();
	}

	@Override
	public Reporte findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public void save(Reporte reporte) {
		repository.save(reporte);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
