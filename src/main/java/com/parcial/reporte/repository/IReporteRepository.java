package com.parcial.reporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parcial.reporte.model.Reporte;

@Repository
public interface IReporteRepository extends JpaRepository<Reporte, Long>{

}
