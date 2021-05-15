package com.umanizales.apipaseoperros.controller;


import com.umanizales.apipaseoperros.model.dto.RespuestaDTO;
import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.service.PerroService;
import com.umanizales.apipaseoperros.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/perro")
@Validated
public class PerroController {

    private PerroService perroService;

    @Autowired
    public PerroController(PerroService perroService) {
        this.perroService = perroService;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Object> getAllPerros()
    {
        return perroService.getAllPerros();
    }

    @GetMapping(path="/{code}")
    public @ResponseBody ResponseEntity<Object> getPerroById(@PathVariable("code") String code)    {

        return perroService.getPerroByCode(code);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object>
    savePerro(@RequestBody Perro perro)
    {
        return perroService.savePerro(perro);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Object> updatePerro(@RequestBody Perro perro)
    {
        return perroService.updatePerro(perro);
    }

    @DeleteMapping(path="/{code}")
    public @ResponseBody ResponseEntity<Object> deletePerro(@PathVariable("code") String code)
    {
        return perroService.deletePerroById(code);
    }



}
