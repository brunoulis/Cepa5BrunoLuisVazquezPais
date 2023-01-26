package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pilotos;

@Repository
@Transactional
public interface PilotosRepository {

    Pilotos findByNombre(String nombre);

    Pilotos findByNombreAndApellidos(String nombre, String apellidos);

}
