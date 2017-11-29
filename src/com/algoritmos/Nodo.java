/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algoritmos;

import java.util.ArrayList;

/**
 *
 * @author ROJAS
 */
public class Nodo {
    int actividad;
    float duracion;
    ArrayList<Integer> lista = new ArrayList<Integer>();
    
    Nodo(int a, float d, ArrayList<Integer> l){
        this.actividad = a;
        this.duracion = d;
        this.lista = l;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public ArrayList<Integer> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Integer> lista) {
        this.lista = lista;
    }
    
    
}
