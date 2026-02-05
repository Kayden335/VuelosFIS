/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.ModeloAsientos;
import modelo.ModeloEstadoAsiento;

/**
 *
 * @author USUARIO
 */
public class ControladorAsientos {
    private int maxAsientos;
    private int seleccionados = 0;
    
    public ControladorAsientos(int maxAsientos) {
        this.maxAsientos = maxAsientos;
    }
    
    public boolean alternarEstado(ModeloAsientos asiento) {
        
        if (asiento.getEstado() == ModeloEstadoAsiento.DISPONIBLE) {
            if (seleccionados >= maxAsientos) {
                return false;
            }
            asiento.setEstado(ModeloEstadoAsiento.SELECCIONADO);
            seleccionados++;
            return true;
        } else if (asiento.getEstado() == ModeloEstadoAsiento.SELECCIONADO) {
            asiento.setEstado(ModeloEstadoAsiento.DISPONIBLE);
            seleccionados--;
            return true;
        }
        return false;
    }
    public boolean validarSeleccion() {
        return seleccionados == maxAsientos;
    }
}
