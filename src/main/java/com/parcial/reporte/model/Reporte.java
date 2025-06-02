package com.parcial.reporte.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "reporte")
public class Reporte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reporte;
	private Long id_venta;
	private String descripcion;
	private String tipo;
	private LocalDate fecha_generacion;
}
