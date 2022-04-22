/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_ex_02.Entidades;

import java.util.ArrayList;

/**
 *
 * @author Walter
 */
public class Cine {
    private Asiento sala[][];
    private Pelicula pelicula;
    private Float precio;
    private ArrayList<String> asientosLibres;
    
    public Cine(){
    }

    public Cine(Asiento[][] sala, Pelicula pelicula, Float precio, ArrayList<String> asientosLibres) {
        this.sala = sala;
        this.pelicula = pelicula;
        this.precio = precio;
        this.asientosLibres = asientosLibres;
    }

    public Asiento[][] getSala() {
        return sala;
    }

    public void setSala(Asiento[][] sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPeli(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public ArrayList<String> getAsientosLibres() {
        return asientosLibres;
    }

    public void setAsientosLibres(ArrayList<String> asientosLibres) {
        this.asientosLibres = asientosLibres;
    }

    @Override
    public String toString() {
        return "Cine{" + "sala=" + sala + ", pelicula=" + pelicula + ", precio=" + precio + ", asientosLibres=" + asientosLibres + '}';
    }
}
