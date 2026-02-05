package controlador;

import vista.VistaSeleccionVuelo;
import vista.VistaDetallesCompra;
import modelo.ModeloRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorSeleccionVuelo implements ActionListener {

    private VistaSeleccionVuelo vista;
    private int numPasajeros;
    private ModeloRegistro usuario;
    private String vueloSeleccionado = "";

    public ControladorSeleccionVuelo(VistaSeleccionVuelo vista, int numPasajeros) {
        this.vista = vista;
        this.numPasajeros = numPasajeros;

        vista.getBtnVuelo1().addActionListener(this);
        vista.getBtnVuelo2().addActionListener(this);
        vista.getBtnVuelo3().addActionListener(this);
        vista.getBtnContinuar().addActionListener(this);
    }
    
    public void setUsuario(ModeloRegistro usuario) {
        this.usuario = usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnVuelo1()) {
            vista.getBtnVuelo1().setEnabled(false);
            vista.getBtnVuelo2().setEnabled(true);
            vista.getBtnVuelo3().setEnabled(true);
            vueloSeleccionado = "Vuelo1";
        }

        if (e.getSource() == vista.getBtnVuelo2()) {
            vista.getBtnVuelo2().setEnabled(false);
            vista.getBtnVuelo1().setEnabled(true);
            vista.getBtnVuelo3().setEnabled(true);
            vueloSeleccionado = "Vuelo2";
        }

        if (e.getSource() == vista.getBtnVuelo3()) {
            vista.getBtnVuelo3().setEnabled(false);
            vista.getBtnVuelo1().setEnabled(true);
            vista.getBtnVuelo2().setEnabled(true);
            vueloSeleccionado = "Vuelo3";
        }

        if (e.getSource() == vista.getBtnContinuar()) {
            if (vueloSeleccionado.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un vuelo primero");
                return;
            }

            String tipoViaje = vista.getRbIda().isSelected() ? "Solo ida" : "Ida y vuelta";

            System.out.println("=== DATOS SELECCIÓN VUELO ===");
            System.out.println("Vuelo: " + vueloSeleccionado);
            System.out.println("Tipo: " + tipoViaje);
            System.out.println("Pasajeros: " + numPasajeros);

            // Abrir vista de detalles
            VistaDetallesCompra vdc = new VistaDetallesCompra();
            
            // Pasar TODOS los datos al siguiente controlador
            ControladorDetallesCompras cdc = new ControladorDetallesCompras(vdc, usuario, numPasajeros);
            cdc.setTipoVuelo(tipoViaje);
            cdc.setVueloSeleccionado(vueloSeleccionado);
            
            vdc.setVisible(true);
            vista.dispose(); // CERRAR VENTANA ACTUAL
        }
    }
}