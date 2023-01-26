package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Equipos;

import java.util.List;

@Repository
@Transactional
public interface EquiposRepository {

    List<Equipos> findByNombre(String nombre);

    List<Equipos> findByNombreAndPais(String nombre, String pais);

}
