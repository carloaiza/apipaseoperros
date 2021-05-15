package com.umanizales.apipaseoperros.controller;


import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.service.ListaSEService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/paseoperros")
@Validated
public class PaseoPerrosController {
    private ListaSEService listaSEService;

    public PaseoPerrosController(ListaSEService listaSEService) {
        this.listaSEService = listaSEService;
    }

    @GetMapping
    public String initPerrosListaSE()
    {
        listaSEService.load();
        return listaSEService.listarNodos();
    }

    @GetMapping(path="/listar")
    public String getAllPerros()
    {
        return listaSEService.listarNodos();
    }

    @GetMapping(path="/{codigo}")
    public Perro encontrarPerroxCodigo(@PathVariable("codigo") String codigo)
    {
        return listaSEService.encontrarPerroxCodigo(codigo);
    }




}
