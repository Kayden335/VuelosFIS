package controlador;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ModeloBoleto;
import modelo.ModeloFactura;
import modelo.ModeloPago;
import modelo.ModeloRegistro;
import vista.VistaFactura;

public class ControladorFactura {

    private VistaFactura vista;
    private ModeloFactura modelo;

    public ControladorFactura(VistaFactura vista, ModeloFactura modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void iniciar() {
        vista.setTitle("Comprobante de Venta - Vuelos FIS");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setTextoTicket("--- Esperando confirmación de compra ---");
    }

    public void mostrarFactura(ModeloRegistro usuario, ModeloPago pago, List<ModeloBoleto> boletos, double subtotal) {
        String idVenta = "TKT-" + (int)(Math.random() * 9000 + 1000);
        
        String textoTicket = modelo.generarFactura(
                idVenta, 
                LocalDate.now(), 
                usuario, 
                pago, 
                boletos, 
                subtotal
        );

        vista.setTextoTicket(textoTicket);
        vista.setVisible(true);
        
        // Guardar automáticamente en archivo
        modelo.guardarTicketEnArchivo(textoTicket, idVenta);
        
        JOptionPane.showMessageDialog(vista,
            "✅ Factura generada exitosamente\n" +
            "Número: " + idVenta + "\n" +
            "Guardada como: ticket_" + idVenta + ".txt\n\n" +
            "¡Gracias por su compra! ✈️",
            "Compra Finalizada",
            JOptionPane.INFORMATION_MESSAGE);
    }
}