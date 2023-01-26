/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PilotosDTO;
import com.example.demo.dto.EquiposDTO;
import com.example.demo.model.Equipos;
import com.example.demo.model.Pilotos;
import com.example.demo.repository.PilotosRepository;
import com.example.demo.repository.EquiposRepository;

/**
 *
 * @author luisv
 */
@Service
public class EquiposServiceImpl implements EquiposService {
    @Autowired
    private EquiposRepository equiposRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public EquiposDTO saveEquipos(EquiposDTO equiposdto) {
                                                                    
        Equipos equipo = EquiposDTO.convertToEntity(equiposdto);
        equiposRepository.save(equipo);
        return EquiposDTO.converToDTO(equipo);
    }

                                                                       
    @Override
    public EquiposDTO getEquiposById(Long id) {
        Optional<Equipos> equipo = equiposRepository.findById(id);
        if (equipo.isPresent()) {
            return EquiposDTO.converToDTO(equipo.get());
        }else{
            return null;
        }
        
    }

                                               
    @Override
    public List<EquiposDTO> listAllEquipos() {
        List<Equipos> lista = equiposRepository.findAll();
        List<EquiposDTO> listaResultado = new ArrayList<EquiposDTO>();
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(EquiposDTO.converToDTO(lista.get(i)));
        }
        return listaResultado;

    }

    @Override
    public void deleteEquipos(Long id) {
        equiposRepository.deleteById(id);
    }

    
}
