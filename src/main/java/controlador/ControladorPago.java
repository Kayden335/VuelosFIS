/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Boleto;
import modelo.Carrito;
import vista.VistaCarrito;
import vista.VistaPago;

public class ControladorPago {

    private Carrito carrito;
    private VistaCarrito vistaCarrito;
    private VistaPago vistaPago;

    public ControladorPago() {
        carrito = Boleto.crearCarritoPrueba(); // ✅ AQUÍ

        vistaCarrito = new VistaCarrito(carrito);
        vistaCarrito.setVisible(true);


        // Listener del botón continuar del carrito
        vistaCarrito.getBtnContinuar().addActionListener(e -> irAPago());
    }

    private void irAPago() {
        vistaPago = new VistaPago(carrito);
        vistaPago.setVisible(true);
        vistaCarrito.dispose();

        // Botones de VistaPago
        vistaPago.getBtnRegresar().addActionListener(e -> volverACarrito());
        vistaPago.getBtnPagar().addActionListener(e -> pagar());
        vistaPago.getBtnCancelar().addActionListener(e -> cancelar());
    }

    private void volverACarrito() {
        vistaCarrito = new VistaCarrito(carrito);
        vistaCarrito.setVisible(true);
        vistaPago.dispose();
    }

    private void pagar() {
        javax.swing.JOptionPane.showMessageDialog(vistaPago,
            "Pago realizado con éxito ✈️💳\nTotal: $" + carrito.getTotal());
        //vistaPago.dispose();
    }

    private void cancelar() {
        int op = javax.swing.JOptionPane.showConfirmDialog(vistaPago,
            "¿Desea cancelar el pago?",
            "Confirmar",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (op == javax.swing.JOptionPane.YES_OPTION) {
            volverACarrito();
        }
    }
}

