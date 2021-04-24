package com.umanizales.apipaseoperros.service;

import com.umanizales.apipaseoperros.model.entities.TipoUsuario;
import com.umanizales.apipaseoperros.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioService {
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public Iterable<TipoUsuario> getAllTipoUsuarios()
    {
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario createTipoUsuario(TipoUsuario tipoUsuario)
    {
        return tipoUsuarioRepository.save(tipoUsuario);
    }
}
