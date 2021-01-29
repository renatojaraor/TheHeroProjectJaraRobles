package com.example.theheroprojectjararobles;

import java.io.Serializable;
import java.util.HashMap;

public class Heroe implements Serializable {
    String id;
    String nombre;
    String alterego;
    HashMap habilidades=new HashMap();

    public Heroe(String id,String alterego,String nombre,HashMap habilidades) {
        this.id = id;
        this.alterego=alterego;
        this.nombre=nombre;
        this.habilidades=habilidades;
    }

    public Heroe() {
    }


    @Override
    public String toString() {
        return "Heroe{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", alterego='" + alterego + '\'' +
                ", habilidades=" + habilidades +
                '}';
    }
}

