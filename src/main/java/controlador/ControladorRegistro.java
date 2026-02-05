package controlador;

import modelo.ModeloRegistro;
import vista.VistaRegistro;
import modelo.ModeloRegistroDAO;
import vista.VistaDestino;
import modelo.ModeloDestino;

public class ControladorRegistro {

    private VistaRegistro vista;
    private ModeloRegistroDAO modelo;

    public ControladorRegistro(VistaRegistro vista, ModeloRegistroDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void iniciar() {
        vista.setVisible(true);
        vista.getBtnRegistrar().addActionListener(e -> registrar());
    }

    private void registrar() {
        if (!vista.validarFormulario()) {
            return;
        }

        ModeloRegistro u = vista.getUsuarioDesdeFormulario();

        if (modelo.guardar(u)) {
            vista.mostrarMensaje("Registro exitoso");

            // Cerrar la ventana actual
            vista.dispose();

            // Abrir DESTINO PASANDO EL USUARIO
            VistaDestino vistaDestino = new VistaDestino();
            ModeloDestino modeloDestino = new ModeloDestino();
            ControladorDestino controladorDestino = new ControladorDestino(vistaDestino, modeloDestino);
            
            // PASAR EL USUARIO REGISTRADO
            controladorDestino.setUsuario(u);
            
            vistaDestino.setVisible(true);

        } else {
            vista.mostrarMensaje("Error al registrar usuario");
        }
    }
}