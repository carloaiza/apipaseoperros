package com.umanizales.apipaseoperros.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paseo_perro", schema = "public", catalog = "paseo_perros")
public class PaseoPerro {
    private int id;
    private Date fecha;
    private Perro perro;
    private Usuario paseador;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaseoPerro that = (PaseoPerro) o;

        if (id != that.id) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "perro", referencedColumnName = "codigo", nullable = false)
    public Perro getPerro() {
        return perro;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
    }

    @ManyToOne
    @JoinColumn(name = "paseador", referencedColumnName = "id", nullable = false)
    public Usuario getPaseador() {
        return paseador;
    }

    public void setPaseador(Usuario paseador) {
        this.paseador = paseador;
    }
}
