/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class ModeloDestino {
    private int cantAdultos = 0;
    private int cantNinios = 0;
    private int cantAdultosMayores = 0;
    private int cantDiscapacitados = 0;

    // Lógica para sumar/restar (Reglas de negocio)
    public void modificarAdulto(int cantidad) {
        if (cantAdultos + cantidad >= 0) cantAdultos += cantidad;
    }
    
    public void modificarNinio(int cantidad) {
        if (cantNinios + cantidad >= 0) cantNinios += cantidad;
    }
    
    public void modificarAdultoMayor(int cantidad) {
        if (cantAdultosMayores + cantidad >= 0) cantAdultosMayores += cantidad;
    }
    
    public void modificarDiscapacitado(int cantidad) {
        if (cantDiscapacitados + cantidad >= 0) cantDiscapacitados += cantidad;
    }

    // Método para calcular el total
    public int getTotalPasajeros() {
        return cantAdultos + cantNinios + cantAdultosMayores + cantDiscapacitados;
    }

    public int getCantAdultos() {
        return cantAdultos;
    }

    public int getCantNinios() {
        return cantNinios;
    }

    public int getCantAdultosMayores() {
        return cantAdultosMayores;
    }

    public int getCantDiscapacitados() {
        return cantDiscapacitados;
    }

}
