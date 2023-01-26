package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pilotos;

@Repository
@Transactional
public interface PilotosRepository extends JpaRepository<Pilotos, Long>, CustomPilotosRepository {

    @Query(value = "select p from Pilotos p where p.equipos.id = :idequipo")
    public Pilotos getPilotosByEquipo(@Param("idequipo") Long idEquipo);

}
