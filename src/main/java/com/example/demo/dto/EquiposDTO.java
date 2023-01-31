package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.demo.model.Equipos;
import com.example.demo.model.Pilotos;

import lombok.Data;
import lombok.ToString;

@Data
public class EquiposDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idEquipo;
    private String nombre;
    private String pais;

    @ToString.Exclude
    @JsonManagedReference("listaPilotos")
    private List<PilotosDTO> listaPilotos = new ArrayList<>();

    // Convierte una entidad a un objeto DTO con todos los datos
    public static EquiposDTO converToDTO(Equipos equipo) {
        // Creamos el equipoDTO y asignamos los valores basicos
        EquiposDTO equipoDTO = new EquiposDTO();
        equipoDTO.setIdEquipo(equipo.getIdEquipo());
        equipoDTO.setNombre(equipo.getNombre());
        equipoDTO.setPais(equipo.getPais());

        /*
         * Cargamos la lista de pilotos. Versión original. Error con NULL
         * for(int i=0;i<equipo.getListaPilotos().size();i++) {
         * PilotosDTO pilotosdto =
         * PilotosDTO.convertToDTO(equipo.getListaPilotos().get(i), equipoDTO);
         * equipoDTO.getListaPilotos().add(pilotosdto);
         * }
         */
        for (Pilotos lospilotos : equipo.getListapilotos()) {
            PilotosDTO pilotosdto = PilotosDTO.convertToDTO(lospilotos, equipoDTO);
            equipoDTO.getListaPilotos().add(pilotosdto);
        }

        return equipoDTO;

    }

    // Convierte un objeto en un entidad
    public static Equipos convertToEntity(EquiposDTO equipoDTO) {
        // Creamos el equipo y asignamos los valores basicos
        Equipos equipo = new Equipos();
        equipo.setIdEquipo(equipoDTO.getIdEquipo());
        equipo.setNombre(equipoDTO.getNombre());
        equipo.setPais(equipoDTO.getPais());

        // Cargamos la lista de pilotos
        for (int i = 0; i < equipoDTO.getListaPilotos().size(); i++) {
            Pilotos pilotos = PilotosDTO.convertToEntity(equipoDTO.getListaPilotos().get(i));
            equipo.getListapilotos().add(pilotos);
        }

        return equipo;
    }

    // Constructor vacio
    public EquiposDTO() {
        super();
        this.listaPilotos = new ArrayList<PilotosDTO>();
    }

}
