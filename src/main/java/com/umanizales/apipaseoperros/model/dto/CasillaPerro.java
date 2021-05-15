package com.umanizales.apipaseoperros.model.dto;

import com.umanizales.apipaseoperros.model.entities.Perro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CasillaPerro implements Serializable {
    private Perro perro;
    private boolean marcada;

}
