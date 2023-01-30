package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.demo.model.Equipos;
import com.example.demo.model.Pilotos;
import java.util.HashSet;
import lombok.Data;
import lombok.ToString;

@Data
public class PilotosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idPiloto;
    private String nombre;
    private String apellido;
    private int edad;

    @ToString.Exclude
    @JsonManagedReference // Solo puedes estar en un equipo
    private EquiposDTO equipo;

    // Convierte una entidad a un objeto DTO con todos los datos
    public static PilotosDTO convertToDTO(Pilotos piloto, EquiposDTO equipo) {
        // Creamos el pilotoDTO y asignamos los valores basicos
        PilotosDTO pilotoDTO = new PilotosDTO();
        pilotoDTO.setIdPiloto(piloto.getIdPiloto());
        pilotoDTO.setNombre(piloto.getNombre());
        pilotoDTO.setApellido(piloto.getApellido());
        pilotoDTO.setEdad(piloto.getEdad());
        pilotoDTO.setEquipo(equipo);

        return pilotoDTO;
    }

    public static Pilotos convertToEntity(PilotosDTO pilotoDTO) {
        // Creamos el piloto y asignamos los valores basicos
        Pilotos piloto = new Pilotos();
        piloto.setIdPiloto(pilotoDTO.getIdPiloto());
        piloto.setNombre(pilotoDTO.getNombre());
        piloto.setApellido(pilotoDTO.getApellido());
        piloto.setEdad(pilotoDTO.getEdad());
        Equipos equipo = EquiposDTO.convertToEntity(pilotoDTO.getEquipo());
        piloto.setEquipo(equipo);

        return piloto;
    }

    // Constructor vacio
    public PilotosDTO() {
        super();
        this.equipo = new EquiposDTO();
    }

}
