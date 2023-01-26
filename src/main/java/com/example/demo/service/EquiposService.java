package com.example.demo.service;

import java.util.List;
import javax.transaction.Transactional;
import com.example.demo.dto.EquiposDTO;


@Transactional
public interface EquiposService {

    EquiposDTO saveEquipos(EquiposDTO equiposdto);

    EquiposDTO getEquiposById(Long id);

    List<EquiposDTO> listAllEquipos();

    void deleteEquipos(Long id);
}
