/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controlador.ControladorRegistro;
import modelo.ModeloRegistroDAO;
import vista.VistaRegistro;

/**
 *
 * @author Lenovo
 */
public class VuelosFIS {
    public static void main(String[] args) {
        // Iniciar la aplicación desde el registro
        java.awt.EventQueue.invokeLater(() -> {
            VistaRegistro vista = new VistaRegistro();
            ModeloRegistroDAO modelo = new ModeloRegistroDAO();
            ControladorRegistro controlador = new ControladorRegistro(vista, modelo);
            controlador.iniciar();
        });
    }
}
