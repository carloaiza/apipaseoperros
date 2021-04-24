package com.umanizales.apipaseoperros.model;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Empleado implements Serializable {
    private String nombre;
    private String identificacion;
    private double salario;

}
