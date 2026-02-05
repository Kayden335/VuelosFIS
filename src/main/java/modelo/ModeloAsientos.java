/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class ModeloAsientos {
    private int fila;
    private char columna;
    private ModeloEstadoAsiento estado;

    public ModeloAsientos(int fila, char columna, ModeloEstadoAsiento estado) {
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public int getFila(){ 
        return fila; 
    }
    
    public char getColumna(){
        return columna; 
    }
    
    public ModeloEstadoAsiento getEstado(){
        return estado; 
    }
    
    public void setEstado(ModeloEstadoAsiento estado){ 
        this.estado = estado; 
    }

    public String getCodigo() {
        return fila + String.valueOf(columna);
    }
}
