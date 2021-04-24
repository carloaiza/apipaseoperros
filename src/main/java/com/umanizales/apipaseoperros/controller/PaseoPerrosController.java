package com.umanizales.apipaseoperros.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/paseoperros")
@Validated
public class PaseoPerrosController {

    @GetMapping
    public String getMessage(){
        return "Hola Campeones";
    }

    @GetMapping(path = "/message2")
    public String getMessage2(){
        return "Segundo Mensaje";
    }

    @GetMapping(path = "/message/{saludo}")
    public String getMessage3(@PathVariable("saludo") String saludo){
        return "Hola "+saludo;
    }

    @GetMapping(path = "/message/{saludo}/{saludo2}")
    public String getMessage4(@PathVariable("saludo") String saludo,
                              @PathVariable("saludo2") String saludo2){
        return saludo + saludo2;
    }

}
