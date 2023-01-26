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

import ch.qos.logback.core.Context;

import com.example.demo.service.EquiposService;

@RestController
public class EquiposController {
    private static final Logger log = LoggerFactory.getLogger(Cepa5BrunoLuisVazquezPaisApplication.class);

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private PilotosService pilotosService;

    @Autowired
    private EquiposService equiposService;

    @Value("${app.nombre}")
    private String nombreAplicacion;

    @GetMapping("/equipos")
    public ResponseEntity<List<EquiposDTO>> getAllEquipos() {
        log.info(context.getMethod() + "from" + context.getRemoteHost());
        List<EquiposDTO> listaEquipos = equiposService.listAllEquipos();
        if (listaEquipos.isEmpty() || listaEquipos == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(listaEquipos, HttpStatus.OK);
        }
    }

    @GetMapping("/equipos/{idEquipo}")
    public ResponseEntity<EquiposDTO> getEquipoById(@PathVariable("idEquipo") Long idEquipo) {
        log.info(context.getMethod() + "from" + context.getRequestURI());
        EquiposDTO equipo = equiposService.getEquiposById(idEquipo);
        if (equipo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(equipo, HttpStatus.OK);
        }
    }

    @PostMapping("/equipos")
    public ResponseEntity<EquiposDTO> createEquipo(@RequestBody EquiposDTO newequipo) {
        log.info(context.getMethod() + context.getRequestURI());
        EquiposDTO equipoCreado = equiposService.saveEquipos(newequipo);
        if (equipoCreado == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(equipoCreado, HttpStatus.OK);
        }
    }

    @PutMapping("/equipos")
    public ResponseEntity<EquiposDTO> updateEquipo(@RequestBody EquiposDTO updequipo) {
        log.info(context.getMethod() + context.getRequestURI());
        EquiposDTO equipoActualizado = equiposService.getEquiposById(updequipo.getIdEquipo());
        if (equipoActualizado == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            EquiposDTO elEquipoUPD = equiposService.saveEquipos(updequipo);
            return new ResponseEntity<>(elEquipoUPD, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/prova", consumes = { "application/json" })
    public void prova(@RequestBody PilotosDTO newpiloto) {
        log.info(context.getMethod() + context.getRequestURI());
        log.info(newpiloto.toString());
    }

    @DeleteMapping("/equipos/{idEquipo}")
    public ResponseEntity<String> deleteEquipo(@PathVariable Long idEquipo) {
        equiposService.deleteEquipos(idEquipo);
        return new ResponseEntity<>("Equipo borrado", HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleError(MethodArgumentTypeMismatchException ex) {
        log.warn("Error en la llamada al servicio", ex);
        String message = String.format("Method Argument Type Mismatch: %s", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
