package com.umanizales.apipaseoperros.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class Coordenada implements Serializable {
    private int fila;
    private int col;
}
