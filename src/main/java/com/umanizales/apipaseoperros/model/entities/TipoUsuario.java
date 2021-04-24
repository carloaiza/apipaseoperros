package com.umanizales.apipaseoperros.model.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name = "tipo_usuario", schema = "public", catalog = "paseo_perros")
public class TipoUsuario {
    private short codigo;
    private String descripcion;
    //private List<Usuario> usuarios;

    @Id
    @Column(name = "codigo", nullable = false)
    public short getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "descripcion", nullable = false, length = 50)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoUsuario that = (TipoUsuario) o;

        if (codigo != that.codigo) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) codigo;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
/*
    @OneToMany(mappedBy = "tipoUsuario",fetch = FetchType.EAGER)
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

 */
}
