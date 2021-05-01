package com.umanizales.apipaseoperros.controller;


import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.service.PerroService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<Perro> getAllPerros()
    {
        return perroService.getAllPerros();
    }

    @GetMapping(path="/{code}")
    public Perro getPerroById(@PathVariable("code") String code)
    {
        return perroService.getPerroByCode(code);
    }

    @PostMapping
    public boolean savePerro(@RequestBody Perro perro)
    {
        return perroService.savePerro(perro);
    }

    @PutMapping
    public Perro updatePerro(@RequestBody Perro perro)
    {
        return perroService.updatePerro(perro);
    }

    @DeleteMapping(path="/{code}")
    public boolean deletePerro(@PathVariable("code") String code)
    {
        return perroService.deletePerroById(code);
    }
}
