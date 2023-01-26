package com.example.demo.service;

import com.example.demo.dto.EquiposDTO;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.dto.PilotosDTO;

@Transactional
public interface PilotosService {

    void savePilotos(PilotosDTO pilotosdto);

    void getPilotosById(Long id);

    List<PilotosDTO> listAllPilotos(EquiposDTO equipodto);

    void deletePilotos(Long id);

}
