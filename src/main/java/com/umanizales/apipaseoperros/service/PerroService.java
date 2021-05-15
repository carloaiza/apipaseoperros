package com.umanizales.apipaseoperros.service;

import com.umanizales.apipaseoperros.model.dto.RespuestaDTO;
import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.repository.PerroRepository;
import com.umanizales.apipaseoperros.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;

@Service
public class PerroService {
   private PerroRepository perroRepository;
    @Autowired
    public PerroService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    public ResponseEntity<Object> getAllPerros()
    {
        RespuestaDTO respuesta= new RespuestaDTO(Constants.SUCCESSFUL,
                perroRepository.findAll(),null);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    public ResponseEntity<Object> getPerroByCode(String code)
    {
        if(perroRepository.existsById(code))
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.SUCCESSFUL,
                            perroRepository.findById(code).get()
                            ,null),HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new RespuestaDTO(Constants.DATA_NOT_FOUND,
                        null
                        ,Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> savePerro(Perro perro)
    {
        try {
            Perro perroAlmacenado=perroRepository.save(perro);
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.SUCCESSFUL,perroAlmacenado
                            ,null),HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE,
                            null
                            ,ex.getMessage()),HttpStatus.CONFLICT);
        }

    }

    public ResponseEntity<Object> deletePerroById(String code)
    {
        if(perroRepository.existsById(code))
        {
            perroRepository.deleteById(code);
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.SUCCESSFUL,code
                            ,null),HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new RespuestaDTO(Constants.DATA_NOT_FOUND,
                        null
                        ,Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> updatePerro(Perro perro)
    {
        if(perroRepository.existsById(perro.getCodigo()))
        {
            try {
                Perro perroAlmacenado=perroRepository.save(perro);
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.SUCCESSFUL,perroAlmacenado
                                ,null),HttpStatus.ACCEPTED);
            }
            catch (Exception ex)
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE,
                                null
                                ,ex.getMessage()),HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(
                new RespuestaDTO(Constants.DATA_NOT_FOUND,
                        null
                        ,Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);

    }
}
