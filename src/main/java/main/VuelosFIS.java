/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controlador.ControladorPago;
import java.security.Principal;
import modelo.Carrito;
import vista.VistaCarrito;

/**
 *
 * @author Lenovo
 */
public class VuelosFIS {

    public static void main(String[] args) {
       
  java.awt.EventQueue.invokeLater(() -> {
        new ControladorPago();
    });
}
}

