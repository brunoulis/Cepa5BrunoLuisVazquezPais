/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author luisv
 */
@Entity
@Table(name = "Equipos", catalog = "f1")
public class Equipos implements Serializable {

	private Long idEquipo;
	private String nombre;
	private String pais;
	private Set<Pilotos> listapilotos = new HashSet<Pilotos>(0);

	public Equipos() {
	}

	public Equipos(Long idEquipo, String nombre, String pais) {
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.pais = pais;
	}

	public Equipos(Long idEquipo, String nombre, String pais, Pilotos pilotoses) {
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.pais = pais;
		this.listapilotos = (Set<Pilotos>) pilotoses;

	}

	@Id

	@Column(name = "idequipo", unique = true, nullable = false)
	public Long getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "pais", nullable = false, length = 50)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipos")
	public Set<Pilotos> getListaPilotos() {
		return this.listapilotos;
	}

	public void setPilotoses(Set<Pilotos> pilotoses) {
		this.listapilotos = pilotoses;
	}

}
