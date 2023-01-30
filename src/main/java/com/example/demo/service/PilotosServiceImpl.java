package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PilotosDTO;
import com.example.demo.dto.EquiposDTO;
import com.example.demo.model.Equipos;
import com.example.demo.model.Pilotos;
import com.example.demo.repository.PilotosRepository;
import com.example.demo.repository.EquiposRepository;

@Service
public class PilotosServiceImpl implements PilotosService {

    @Autowired
    private PilotosRepository pilotosRepository;

    @Override
    public void savePilotos(PilotosDTO pilotosdto) {
        pilotosRepository.saveCustomPilotos(pilotosdto);
    }

    @Override
    public void getPilotosById(Long id) {
        pilotosRepository.getPilotosByEquipo(id);

    }

    @Override
    public List<PilotosDTO> listAllPilotos(EquiposDTO equipodto) {
        List<Pilotos> lista = pilotosRepository.findAll();
        List<PilotosDTO> listaResultado = new ArrayList<PilotosDTO>();
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(PilotosDTO.convertToDTO(lista.get(i), equipodto));
        }
        return listaResultado;
    }

    @Override
    public void deletePilotos(Long id) {
        pilotosRepository.deleteById(id);
    }

   

}
