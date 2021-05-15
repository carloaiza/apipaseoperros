package com.umanizales.apipaseoperros.controller;

import com.umanizales.apipaseoperros.model.dto.Coordenada;
import com.umanizales.apipaseoperros.model.dto.RequestPerroCoordenadaDTO;
import com.umanizales.apipaseoperros.service.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "escondetuperro")
@Validated
public class EscondiendoPerrosController {
    private TableroService tableroService;
    @Autowired
    public EscondiendoPerrosController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @PostMapping(path = "iniciar_tablero")
    public @ResponseBody  ResponseEntity<Object> iniciarTablero(@RequestBody Coordenada coordenada)
    {
        return tableroService.inicializarTablero(coordenada.getFila(), coordenada.getCol());
    }

    @GetMapping(path="ver_tablero")
    public @ResponseBody ResponseEntity<Object> visualizarTablero()
    {
        return tableroService.visualizarTablero();
    }

    @PostMapping(path="esconderperro")
    public @ResponseBody ResponseEntity<Object> esconderPerro(@RequestBody
                                                              RequestPerroCoordenadaDTO request)
    {
        return tableroService.esconderPerro(request.getCodigo(),request.getCoordenada());
    }


    @PostMapping(path="buscarperro")
    public @ResponseBody ResponseEntity<Object> buscarPerro(@RequestBody Coordenada coordenada  )
    {
        return tableroService.buscarPerro(coordenada);
    }

}
