package com.umanizales.apipaseoperros.service;

import com.umanizales.apipaseoperros.model.Empleado;
import com.umanizales.apipaseoperros.model.ListaSE;
import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.model.excepcion.ListaSEExcepcion;
import com.umanizales.apipaseoperros.repository.PerroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@Service  //Se comporta como un Bean de application
public class ListaSEService {
    private ListaSE listaSE;

    private PerroRepository perroRepository;

    @Autowired
    public ListaSEService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
        this.listaSE = new ListaSE();

    }

    @PostConstruct
    public void load()
    {
        Iterable<Perro> perros = perroRepository.findAll();
        for(Perro perrito:perros)
        {
            this.listaSE.adicionarNodo(perrito);
        }
        System.out.println("listaSE.getCont() = " + listaSE.getCont());

    }

    public int contarNodos()
    {
        return listaSE.getCont();
    }

    public String listarNodos()
    {
        return listaSE.listadoNodos();
    }


    public boolean adicionarNodo(Object dato)
    {
        this.listaSE.adicionarNodo(dato);
        if(dato instanceof Perro) {
            this.perroRepository.save((Perro)dato);
        }
        return true;
    }

}
