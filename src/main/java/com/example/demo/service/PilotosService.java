package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.dto.PilotosDTO;

@Transactional
public interface PilotosService {

    PilotosDTO savePilotos(PilotosDTO pilotosdto);

    PilotosDTO getPilotosById(Long id);

    List<PilotosDTO> listAllPilotos();

    void deletePilotos(Long id);

}
