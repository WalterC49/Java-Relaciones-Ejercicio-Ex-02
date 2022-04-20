/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_ex_02.Entidades;

/**
 *
 * @author Walter
 */
public class Cine {
    private Asiento sala[][];
    private Pelicula peli;
    private Integer precio;
    
    public Cine(){
    }

    public Cine(Asiento[][] sala, Pelicula peli, Integer precio) {
        this.sala = sala;
        this.peli = peli;
        this.precio = precio;
    }

    public Asiento[][] getSala() {
        return sala;
    }

    public void setSala(Asiento[][] sala) {
        this.sala = sala;
    }

    public Pelicula getPeli() {
        return peli;
    }

    public void setPeli(Pelicula peli) {
        this.peli = peli;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Cine{" + "sala=" + sala + ", peli=" + peli + ", precio=" + precio + '}';
    }
}
