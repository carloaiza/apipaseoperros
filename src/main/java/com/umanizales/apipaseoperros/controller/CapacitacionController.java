package com.umanizales.apipaseoperros.controller;


import com.umanizales.apipaseoperros.model.Empleado;
import com.umanizales.apipaseoperros.service.ListaSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/capacitacion")
@Validated
public class CapacitacionController {

    private ListaSEService listaSEService;

    @Autowired
    public CapacitacionController(ListaSEService listaSEService) {
        this.listaSEService = listaSEService;
    }

    @GetMapping
    public Empleado getEmployee()
    {
        Empleado orozco= new Empleado("Andrés Orozco","1053478965",
                3500000);
        return orozco;
    }
    @GetMapping(path="/empleados")
    public Empleado[] getAllEmployees(){
        Empleado[] empleados= new Empleado[3];
        empleados[0]= new Empleado("Andrés Orozco","1053478965",
                3500000);
        empleados[1]= new Empleado("Carlos Loaiza","75147569",
                1500000);
        empleados[2]= new Empleado("Camila Castaño","24987547",
                2500000);
        return empleados;
    }
    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado employee)
    {
        //simular que recibo un empleado y lo mando a guardar
        //en bds y además le recalcular bonificacion del 15%
        //Guarda en bds
        employee.setSalario(employee.getSalario()*1.15);
        return employee;
    }

    @GetMapping(path="/count")
    public int getCountEmployees()
    {
        return listaSEService.contarNodos();
    }

    @GetMapping(path="/listar")
    public String getTotalEmployees()
    {
        return listaSEService.listarNodos();
    }
}
