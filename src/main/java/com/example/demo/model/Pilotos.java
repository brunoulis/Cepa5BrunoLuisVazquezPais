/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author luisv
 */

/*
 * CREATE TABLE Equipos(
 * id_equipo INT NOT NULL AUTO_INCREMENT,
 * nombre VARCHAR(50) NOT NULL,
 * pais VARCHAR(50) NOT NULL,
 * PRIMARY KEY(id_equipo)
 * );
 * 
 * CREATE TABLE pilotos(
 * id_piloto INT NOT NULL AUTO_INCREMENT,
 * nombre VARCHAR(50) NOT NULL,
 * apellido VARCHAR(50) NOT NULL,
 * id_equipo INT NOT NULL,
 * edad int NOT NULL,
 * foreign key(id_equipo) references equipos(idequipo),
 * PRIMARY KEY(id_piloto)
 * );
 */

@Data
@Entity
@Table(name = "Pilotos")
public class Pilotos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPiloto;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "edad")
	private int edad;

	@ManyToOne
	@JoinColumn(name = "id_equipo")
	@ToString.Exclude
	private Equipos equipo;

}
