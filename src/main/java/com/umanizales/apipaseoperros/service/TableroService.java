package com.umanizales.apipaseoperros.service;
//Comportamientos

import com.umanizales.apipaseoperros.model.dto.CasillaPerro;
import com.umanizales.apipaseoperros.model.dto.Coordenada;
import com.umanizales.apipaseoperros.model.dto.RespuestaDTO;
import com.umanizales.apipaseoperros.model.entities.Perro;
import com.umanizales.apipaseoperros.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service  //Application 1 mismo tablero para los n usuarios
public class TableroService {
    private CasillaPerro[][] tableroPerros;
    private int contadorAciertos=0;
    private int contadorErrores=0;
    private int contEscondidos=0;
    private boolean estadoJuego=false;

    private ListaSEService listaSEService;

    @Autowired
    public TableroService(ListaSEService listaSEService) {
        this.listaSEService = listaSEService;
    }

    public ResponseEntity<Object> inicializarTablero(int filas, int cols)
    {
        if(filas <0 || cols <0)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                            Constants.ERROR_ROWS_COLS_POSITIVE)
                    , HttpStatus.CONFLICT);
        }
        tableroPerros = new CasillaPerro[filas][cols];
        return new ResponseEntity<>(
                new RespuestaDTO(Constants.SUCCESSFUL,null,null),HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> esconderPerro(String codigo, Coordenada coordenada)
    {
        if(coordenada.getFila() <0 || coordenada.getCol() <0)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                            Constants.ERROR_ROWS_COLS_POSITIVE)
                    , HttpStatus.CONFLICT);
        }
        ///buscar el perro en la lista
        Perro perroEsconder= listaSEService.encontrarPerroxCodigo(codigo);
        if(perroEsconder!=null)
        {
            //Validar coordena y espacio este libre
            if(validarCoordenada(coordenada))
            {
                //Validar que no este ocupada
                if(tableroPerros[coordenada.getFila()][coordenada.getCol()]==null)
                {
                    tableroPerros[coordenada.getFila()][coordenada.getCol()]=
                            new CasillaPerro(perroEsconder,false);
                    contEscondidos++;
                    if(contEscondidos == listaSEService.contarNodos())
                    {
                        estadoJuego=true;
                    }
                    return new ResponseEntity<>(
                            new RespuestaDTO(Constants.SUCCESSFUL,null,null),
                            HttpStatus.ACCEPTED
                    );

                }
                else
                {
                    return new ResponseEntity<>(
                            new RespuestaDTO(Constants.MESSAGE_BOX_OCUPATED,null,
                                    Constants.ERROR_BOX_OCUPATED)
                            , HttpStatus.CONFLICT);
                }
            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.MESSAGE_COORD_NOT_VALIDATE,null,
                                Constants.ERROR_COORD_NOT_VALIDATE)
                        , HttpStatus.CONFLICT);
            }
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.DATA_NOT_FOUND,
                    null
                    ,Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);
        }
    }


    private boolean validarCoordenada(Coordenada coord)
    {
        if(coord.getFila() < tableroPerros.length && coord.getCol() < tableroPerros[0].length)
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<Object> visualizarTablero()
    {
        if(tableroPerros == null)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_BOARD_VOID,null,
                            Constants.ERROR_BOARD_VOID)
                    , HttpStatus.CONFLICT);
        }
        else
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.SUCCESSFUL,tableroPerros,null),
                    HttpStatus.OK
            );
        }
    }

    public ResponseEntity<Object> buscarPerro(Coordenada coord)
    {
        if(estadoJuego)
        {
            if(validarCoordenada(coord))
            {
                if(tableroPerros[coord.getFila()][coord.getCol()]!=null
                && !tableroPerros[coord.getFila()][coord.getCol()].isMarcada())
                {
                    //eliminar el perro de la lista
                    //listaSEService.eliminarPerro();
                    tableroPerros[coord.getFila()][coord.getCol()].setMarcada(true);
                    contadorAciertos++;
                    return this.validarEstadoJuego(true,
                            tableroPerros[coord.getFila()][coord.getCol()].getPerro());
                }
                else
                {
                    contadorErrores++;
                    return this.validarEstadoJuego(false,null);
                }

            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.MESSAGE_COORD_NOT_VALIDATE,null,
                                Constants.ERROR_COORD_NOT_VALIDATE)
                        , HttpStatus.CONFLICT);
            }
        }
        else
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_STATE_GAME_INACTIVE,null,
                            Constants.ERROR_STATE_GAME_INACTIVE)
                    , HttpStatus.CONFLICT);
        }
    }


    private ResponseEntity<Object> validarEstadoJuego(boolean exito, Perro perro)
    {
        if(exito)
        {
            //Acabó de acertar
            if(contadorAciertos== listaSEService.contarNodos())
            {
                estadoJuego=false;
                tableroPerros=null;
                return new ResponseEntity<>(
                        new RespuestaDTO("Has ganado el juego",
                                null
                                ,null),
                        HttpStatus.OK
                );

            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.SUCCESSFUL,
                                perro
                                ,null),
                        HttpStatus.OK
                );
            }
        }
        else
        {
            //acabó de fallas
            if(contadorErrores >= this.listaSEService.contarNodos() * Constants.PERCENTAGE_ERROR_GAME)
            {
                estadoJuego=false;
                tableroPerros=null;
                return new ResponseEntity<>(
                        new RespuestaDTO("HAS PERDIDO",null,
                                "HA SUPERADO EL NUMERO DE OPCIONES POSIBLES")
                        , HttpStatus.CONFLICT);
            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO("Has fallado",null,null )
                        , HttpStatus.CONFLICT);
            }
        }
    }
}
