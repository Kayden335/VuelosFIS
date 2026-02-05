package controlador;

import javax.swing.JOptionPane;
import vista.VistaDetallesCompra;
import modelo.ModeloCompra;
import modelo.ModeloBoleto;
import modelo.ModeloRegistro;
import modelo.ModeloCarrito;
import vista.VistaCarrito;

public class ControladorDetallesCompras {

    private VistaDetallesCompra vista;
    private ModeloCompra compra;
    private ModeloRegistro usuario;
    private int numPasajeros;
    private String tipoVuelo;
    private String vueloSeleccionado;

    public ControladorDetallesCompras(VistaDetallesCompra vista, ModeloRegistro usuario, int numPasajeros) {
        this.vista = vista;
        this.usuario = usuario;
        this.numPasajeros = numPasajeros;
        
        this.compra = new ModeloCompra(numPasajeros);
        
        vista.getTxtNumeroPasajeros().setText(String.valueOf(numPasajeros));
        vista.getTxtNumeroPasajeros().setEditable(false);
        
        // Configurar listeners
        vista.getBtnSiguiente().addActionListener(e -> {
            System.out.println("DEBUG: Botón siguiente presionado en DetallesCompra");
            irACarrito();
        });
        
        vista.getComboClase().addActionListener(e -> {
            String clase = (String) vista.getComboClase().getSelectedItem();
            actualizarTablaServicios(clase);
        });
        
        // Configurar botones toggle para cambiar texto
        configurarBotonesToggle();
        
        // Configurar botón guardar
        vista.getBtnGuardarInformacion().addActionListener(e -> {
            System.out.println("DEBUG: Guardar información presionado");
            procesarBoleto();
        });
        
        // Configurar clase por defecto
        vista.getComboClase().setSelectedItem("Económica");
        actualizarTablaServicios("Económica");
        
        actualizarEstado();
    }
    
    private void configurarBotonesToggle() {
        // Configurar botón de maletas
        vista.getBtnMaletas().addActionListener(e -> {
            if (vista.getBtnMaletas().isSelected()) {
                vista.getBtnMaletas().setText("SÍ");
            } else {
                vista.getBtnMaletas().setText("NO");
            }
        });
        
        // Configurar botón de vuelo regreso
        vista.getBtnVueloRegreso().addActionListener(e -> {
            if (vista.getBtnVueloRegreso().isSelected()) {
                vista.getBtnVueloRegreso().setText("SÍ");
            } else {
                vista.getBtnVueloRegreso().setText("NO");
            }
        });
        
        // Inicializar texto
        vista.getBtnMaletas().setText("NO");
        vista.getBtnVueloRegreso().setText("NO");
    }
    
    public void setTipoVuelo(String tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }
    
    public void setVueloSeleccionado(String vueloSeleccionado) {
        this.vueloSeleccionado = vueloSeleccionado;
    }
    
    private void actualizarEstado() {
        if (vueloSeleccionado != null) {
            String infoVuelo = "Vuelo: " + vueloSeleccionado;
            if (tipoVuelo != null) {
                infoVuelo += " | Tipo: " + tipoVuelo;
            }
            System.out.println(infoVuelo);
        }
    }

    private void procesarBoleto() {
        try {
            String nombre = vista.getTxtNombre().getText().trim();
            String cedula = vista.getTxtCedula().getText().trim();
            String pasaporte = vista.getTxtPasaporte().getText().trim();
            boolean maletas = vista.getBtnMaletas().isSelected();
            boolean regreso = vista.getBtnVueloRegreso().isSelected();
            String clase = (String) vista.getComboClase().getSelectedItem();

            if (nombre.isEmpty() || cedula.isEmpty() || pasaporte.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos");
                return;
            }

            if (!cedulaValida(cedula)) {
                JOptionPane.showMessageDialog(vista, "Cédula inválida");
                return;
            }

            // Crear boleto
            ModeloBoleto boleto = new ModeloBoleto(
                nombre, 
                maletas, 
                regreso, 
                clase, 
                tipoVuelo, 
                vueloSeleccionado
            );
            
            compra.agregarBoleto(boleto);
            
            System.out.println("Boleto agregado: " + boleto.getDescripcionCompleta());
            System.out.println("Pasajeros registrados: " + compra.getPasajerosIngresados() + " de " + numPasajeros);

            // Limpiar campos para siguiente pasajero
            vista.getTxtNombre().setText("");
            vista.getTxtCedula().setText("");
            vista.getTxtPasaporte().setText("");

            if (compra.estaCompleta()) {
                JOptionPane.showMessageDialog(vista, 
                    "✅ Todos los " + numPasajeros + " pasajeros registrados.\nProcediendo al carrito...");
                
                // Ir directamente al carrito
                irACarrito();
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Pasajero registrado. " + 
                    (numPasajeros - compra.getPasajerosIngresados()) + 
                    " restantes.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void irACarrito() {
    System.out.println("DEBUG: Intentando ir al carrito...");
    System.out.println("DEBUG: Pasajeros registrados: " + compra.getPasajerosIngresados() + "/" + numPasajeros);
    
    if (compra.estaCompleta()) {
        ModeloCarrito carrito = new ModeloCarrito();
        for (ModeloBoleto b : compra.getBoletos()) {
            carrito.agregar(b);
        }
        
        System.out.println("DEBUG: Carrito creado con " + carrito.getBoletos().size() + " boletos");
        
        // 1. Crear la vista del carrito
        VistaCarrito vistaCarrito = new VistaCarrito(carrito);
        
        // 2. Crear ControladorPago PASANDO la vista ya creada
        ControladorPago controladorPago = new ControladorPago(carrito, usuario, vistaCarrito);
        
        // 3. Mostrar SOLO la vista del carrito
        vistaCarrito.setVisible(true);
        
        // 4. Cerrar detalles compra
        vista.dispose();
        
        System.out.println("DEBUG: Ventana DetallesCompra cerrada, Carrito abierto");
    } else {
        JOptionPane.showMessageDialog(vista, 
            "Debe registrar información para todos los " + numPasajeros + " pasajeros primero.");
        JOptionPane.showMessageDialog(vista, 
            "Registrados: " + compra.getPasajerosIngresados() + " de " + numPasajeros);
    }
}
    private boolean cedulaValida(String cedula) {
        return cedula.matches("\\d{10,11}");
    }

    private void actualizarTablaServicios(String claseVuelo) {
        String[] columnas = {"Servicio", "Disponible"};
        Object[][] datos;

        switch (claseVuelo) {
            case "Económica":
                datos = new Object[][]{{"Bebidas", "Sí"}, {"Boleto", "$175.46"}};
                break;
            case "Premium":
                datos = new Object[][]{{"Comida", "Sí"}, {"Boleto", "$210.46"}};
                break;
            case "Ejecutiva":
                datos = new Object[][]{{"Acceso VIP", "Sí"}, {"Boleto", "$246.76"}};
                break;
            default:
                datos = new Object[][]{};
        }

        vista.getTblServicios().setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}