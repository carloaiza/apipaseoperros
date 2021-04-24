package com.umanizales.apipaseoperros.service;

import com.umanizales.apipaseoperros.model.Empleado;
import com.umanizales.apipaseoperros.model.ListaSE;
import com.umanizales.apipaseoperros.model.excepcion.ListaSEExcepcion;
import org.springframework.stereotype.Service;

@Service
public class ListaSEService {
    private ListaSE listaSE;

    public ListaSEService() {
        this.listaSE= new ListaSE();
        ///Bds llenara la lista
        listaSE.adicionarNodo(new Empleado("Pedro Pérez",
                    "12345646"
                    , 4000000));
        listaSE.adicionarNodo(new Empleado("Juan Juarez",
                "4546464"
                , 4500000));
    }

    public int contarNodos()
    {
        return listaSE.getCont();
    }

    public String listarNodos()
    {
        return listaSE.listadoNodos();
    }

}
