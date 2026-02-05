package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ModeloDestino;
import modelo.ModeloRegistro;
import vista.VistaDestino;
import vista.VistaSeleccionVuelo;

public class ControladorDestino {
    private VistaDestino vista;
    private ModeloDestino modelo;
    private ModeloRegistro usuario;

    public ControladorDestino(VistaDestino vista, ModeloDestino modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnMasAdulto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelo.modificarAdulto(1);
                actualizarInterfaz();
            }
        });

        this.vista.btnMenosAdulto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelo.modificarAdulto(-1);
                actualizarInterfaz();
            }
        });
        
        this.vista.btnBuscarVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarVuelo();
            }
        });

        this.vista.btnMasNinio.addActionListener(e -> {
            modelo.modificarNinio(1);
            actualizarInterfaz();
        });
        
        this.vista.btnMenosNinio.addActionListener(e -> {
            modelo.modificarNinio(-1);
            actualizarInterfaz();
        });

        this.vista.btnMasAdultoMayor.addActionListener(e -> {
            modelo.modificarAdultoMayor(1);
            actualizarInterfaz();
        });
        
        this.vista.btnMenosAdultoMayor.addActionListener(e -> {
            modelo.modificarAdultoMayor(-1);
            actualizarInterfaz();
        });

        this.vista.btnMasDiscapacitado.addActionListener(e -> {
            modelo.modificarDiscapacitado(1);
            actualizarInterfaz();
        });
        
        this.vista.btnMenosDiscapacitado.addActionListener(e -> {
            modelo.modificarDiscapacitado(-1);
            actualizarInterfaz();
        });
        
        actualizarInterfaz();
    }
    
    public void setUsuario(ModeloRegistro usuario) {
        this.usuario = usuario;
        System.out.println("Usuario recibido en Destino: " + 
                          (usuario != null ? usuario.getNombre() : "null"));
    }

    private void actualizarInterfaz() {
        int total = modelo.getTotalPasajeros();
        vista.txtTotalPasajeros.setText(String.valueOf(total));
    }

    private void validarVuelo() {
        String origen = vista.cbOrigen.getSelectedItem().toString();
        String destino = vista.cbDestino.getSelectedItem().toString();
        int totalPasajeros = modelo.getTotalPasajeros();
        
        boolean todoValido = true;
        String mensajeError = "";
        
        if (origen.equals(destino)) {
            mensajeError += "El origen y el destino no pueden ser iguales.\n";
            todoValido = false;
        }
        if(totalPasajeros <= 0) {
            mensajeError += "Debe seleccionar al menos un pasajero.\n";
            todoValido = false;
        }
        if (vista.getFechaVuelo() == null) {
            mensajeError += "Debe seleccionar una fecha de vuelo.\n";
            todoValido = false;
        }
        
        if (!todoValido) {
            JOptionPane.showMessageDialog(vista, mensajeError, "Errores", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Si todo está válido, continuar con SeleccionVuelo
        if (todoValido) {
            System.out.println("=== PASANDO A SELECCIÓN VUELO ===");
            System.out.println("Pasajeros: " + totalPasajeros);
            System.out.println("Usuario: " + (usuario != null ? usuario.getNombre() : "null"));
            
            VistaSeleccionVuelo vistaSeleccion = new VistaSeleccionVuelo();
            ControladorSeleccionVuelo controladorSeleccion = 
                new ControladorSeleccionVuelo(vistaSeleccion, totalPasajeros);
            
            // Pasar usuario al siguiente controlador
            if (this.usuario != null) {
                controladorSeleccion.setUsuario(this.usuario);
            }
            
            vistaSeleccion.setVisible(true);
            vista.dispose(); // CERRAR VENTANA ACTUAL
        }
    }
}