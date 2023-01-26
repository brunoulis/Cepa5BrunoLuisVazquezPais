package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.Cepa5BrunoLuisVazquezPaisApplication;
import com.example.demo.dto.PilotosDTO;
import com.example.demo.dto.EquiposDTO;
import com.example.demo.service.PilotosService;

import com.example.demo.service.EquiposService;

@RestController
public class PilotosController {

    private static final Logger log = LoggerFactory.getLogger(Cepa5BrunoLuisVazquezPaisApplication.class);

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private PilotosService pilotosService;

    @Autowired
    private EquiposService equiposService;

    @PutMapping("/equipos/{idEquipo}/pilotos")
    public ResponseEntity<EquiposDTO> addPiloto(@PathVariable Long idEquipo,
            @RequestBody PilotosDTO newPilotoDTO) {
        log.info(context.getMethod() + context.getRequestURI());
        EquiposDTO equipoDTO = equiposService.getEquiposById(idEquipo);
        if (equipoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Asignamos el equipo al piloto
            newPilotoDTO.setEquipo(equipoDTO);
            // Guardamos el piloto (Generara ID)
            pilotosService.savePilotos(newPilotoDTO);

            PilotosDTO elpilotoUPD = pilotosService.getPilotosById(idEquipo);
            return new ResponseEntity<>(elpilotoUPD, HttpStatus.OK);
        }
    }

    @GetMapping("/equipos/{idEquipo}/pilotos")
    public ResponseEntity<List<PilotosDTO>> getEquiposPilotos(@PathVariable Long idEquipo) {
        // Obtenemos el equipo
        EquiposDTO equipoDTO = equiposService.getEquiposById(idEquipo);

        if (equipoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Obtenemos la lista de pilotos del equipo
            List<PilotosDTO> listaPilotos = pilotosService.listAllPilotos(equipoDTO);
            if (listaPilotos.isEmpty() || listaPilotos == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(listaPilotos, HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/equipos/{idPiloto}")
    public ResponseEntity<String> removePiloto(@PathVariable Long idPiloto) {
        pilotosService.deletePilotos(idPiloto);
        return new ResponseEntity<>("Piloto eliminado", HttpStatus.OK);
    }
}
