package com.example.demo.repository;

import com.example.demo.dto.PilotosDTO;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.PilotosDTO;
import com.example.demo.model.*;

public class CustomPilotosRepositoyImpl implements CustomPilotosRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EquiposRepository equipoRepository;

    @Override
    public void saveCustomPilotos(PilotosDTO pilotosdto) {
        Optional<Equipos> equipo = equipoRepository.findById(pilotosdto.getEquipo().getIdEquipo());
        // Mapeamos el dto a una entidad
        Pilotos piloto = PilotosDTO.convertToEntity(pilotosdto);
        piloto.setEquipo(equipo.get());

        // Added
        equipo.get().getListapilotos().add(piloto);

        // Mandamos persistir el objeto
        entityManager.persist(equipo);

    }

}
