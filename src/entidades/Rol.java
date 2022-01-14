/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author angel
 */
public class Rol {
    private int id;
    private String nombre;
    private String Descripcion;

    public Rol() {
    }

    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
   
    public Rol(int id, String nombre, String Descripion) {
        this.id = id;
        this.nombre = nombre;
        this.Descripcion = Descripion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripion() {
        return Descripcion;
    }

    public void setDescripion(String Descripion) {
        this.Descripcion = Descripion;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Objects.hashCode(this.Descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.Descripcion, other.Descripcion)) {
            return false;
        }
        return true;
    }
    
    
}
