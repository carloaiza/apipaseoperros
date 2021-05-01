package com.umanizales.apipaseoperros.service;

import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.repository.PerroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerroService {
   private PerroRepository perroRepository;
    @Autowired
    public PerroService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    public Iterable<Perro> getAllPerros()
    {
        return perroRepository.findAll();
    }

    public Perro getPerroByCode(String code)
    {
        return perroRepository.findById(code).get();
    }

    public boolean savePerro(Perro perro)
    {
        perroRepository.save(perro);
        return true;
    }

    public boolean deletePerroById(String code)
    {
        perroRepository.deleteById(code);
        return true;
    }

    public Perro updatePerro(Perro perro)
    {
        //pendiente explicar el rollback de actualizacion
        return perroRepository.save(perro);
    }
}
