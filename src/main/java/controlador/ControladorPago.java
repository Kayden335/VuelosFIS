package controlador;

import modelo.ModeloBoleto;
import modelo.ModeloCarrito;
import vista.VistaCarrito;
import vista.VistaPago;
import javax.swing.JOptionPane;
import modelo.ModeloPago;
import modelo.ModeloRegistro;
import vista.VistaFactura;
import modelo.ModeloFactura;
import java.util.List;

public class ControladorPago {

    private ModeloCarrito carrito;
    private VistaCarrito vistaCarrito;
    private VistaPago vistaPago;
    private ModeloRegistro usuario;
    private ModeloPago datosPago;
    private boolean pagoRealizado = false;
    private boolean vistaPagoCreada = false; // BANDERA NUEVA

    // Constructor original
    public ControladorPago(ModeloCarrito carrito, ModeloRegistro usuario) {
        this(carrito, usuario, new VistaCarrito(carrito));
    }

    // Constructor que recibe la VistaCarrito ya creada
    public ControladorPago(ModeloCarrito carrito, ModeloRegistro usuario, VistaCarrito vistaCarritoExistente) {
        this.carrito = carrito;
        this.usuario = usuario;
        this.vistaCarrito = vistaCarritoExistente;
        
        System.out.println("DEBUG: ControladorPago creado para usuario: " + 
                          (usuario != null ? usuario.getNombre() : "null"));

        // IMPORTANTE: NO crear VistaPago aquí todavía
        // Solo configurar listeners
        configurarListenersCarrito();
        
        System.out.println("DEBUG: ControladorPago inicializado, VistaPago NO creada aún");
    }

    private void configurarListenersCarrito() {
        vistaCarrito.getBtnContinuar().addActionListener(e -> irAPago());
    }

    private void irAPago() {
        System.out.println("DEBUG: Yendo a pago...");
        
        // Crear VistaPago SOLO si no existe
        if (!vistaPagoCreada) {
            vistaPago = new VistaPago(carrito);
            configurarListenersPago();
            vistaPagoCreada = true;
            System.out.println("DEBUG: VistaPago creada por primera vez");
        }
        
        vistaCarrito.dispose(); // CERRAR VistaCarrito
        vistaPago.setVisible(true); // MOSTRAR VistaPago
    }

    private void configurarListenersPago() {
        vistaPago.getBtnRegresar().addActionListener(e -> volverACarrito());
        vistaPago.getBtnCancelar().addActionListener(e -> cancelar());
        vistaPago.getBtnPagar().addActionListener(e -> procesarPago());
        vistaPago.getBtnContinuar().addActionListener(e -> continuarAFactura());
    }

    private void volverACarrito() {
        System.out.println("DEBUG: Volviendo al carrito...");
        vistaPago.dispose(); // CERRAR VistaPago
        vistaCarrito.setVisible(true); // ABRIR VistaCarrito
    }

    private void procesarPago() {
        System.out.println("DEBUG: Procesando pago...");
        
        if (!vistaPago.tieneMetodoPagoSeleccionado()) {
            JOptionPane.showMessageDialog(vistaPago, 
                "❌ Debe seleccionar un método de pago", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ModeloPago pago = vistaPago.obtenerPago();

        if (pago == null) {
            JOptionPane.showMessageDialog(vistaPago, 
                "❌ Error al obtener datos del pago", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!pago.validar()) {
            JOptionPane.showMessageDialog(vistaPago,
                "❌ Datos inválidos. Revise la información:\n" +
                "- Tarjeta: 16 dígitos, CVV 3 dígitos\n" +
                "- Transferencia: Cuenta mín 10 dígitos\n" +
                "- PayPal: Email válido, contraseña 6+ caracteres",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vistaPago,
            "¿Confirmar pago de $" + carrito.getTotal() + "?\n" +
            "Método: " + pago.getMetodo(),
            "Confirmar Pago",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            System.out.println("DEBUG: Pago cancelado por el usuario");
            return;
        }
        
        this.datosPago = pago;
        this.pagoRealizado = true;
        
        JOptionPane.showMessageDialog(
            vistaPago,
            "✅ PAGO REALIZADO CON ÉXITO 💳\n" +
            "Método: " + pago.getMetodo() + "\n" +
            "Total: $" + String.format("%.2f", carrito.getTotal()) + "\n" +
            "¡Gracias por su compra!",
            "Pago Exitoso",
            JOptionPane.INFORMATION_MESSAGE
        );

        vistaPago.bloquearPago();
        System.out.println("DEBUG: Pago realizado correctamente");
    }
    
    private void continuarAFactura() {
        System.out.println("DEBUG: Intentando continuar a factura...");
        
        if (!pagoRealizado) {
            JOptionPane.showMessageDialog(
                vistaPago,
                "❌ DEBE REALIZAR EL PAGO ANTES DE CONTINUAR\n" +
                "Por favor, seleccione un método de pago y presione 'PAGAR'",
                "Pago Pendiente",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double total = carrito.getTotal();
        
        // Crear Factura
        VistaFactura vFactura = new VistaFactura(carrito);
        ModeloFactura mFactura = new ModeloFactura();
        ControladorFactura cFactura = new ControladorFactura(vFactura, mFactura);
        
        List<ModeloBoleto> boletos = carrito.getBoletos();
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(vistaPago, 
                "Error: No hay información de usuario para la factura",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (datosPago == null) {
            datosPago = new ModeloPago("Efectivo");
        }
        
        System.out.println("DEBUG: Mostrando factura con " + boletos.size() + " boletos");
        
        // Mostrar factura
        cFactura.mostrarFactura(usuario, datosPago, boletos, total);
        
        // CERRAR VENTANA DE PAGO
        vistaPago.dispose(); // ← ESTO CIERRA VISTAPAGO
        // vistaPago = null; // Opcional: liberar referencia
        // vistaPagoCreada = false;
        
        System.out.println("DEBUG: Flujo completo completado exitosamente!");
    }

    private void cancelar() {
        int op = JOptionPane.showConfirmDialog(vistaPago,
            "¿Desea cancelar el proceso de pago?\n" +
            "Esta acción lo regresará al carrito de compras.",
            "Confirmar Cancelación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (op == JOptionPane.YES_OPTION) {
            System.out.println("DEBUG: Pago cancelado por el usuario");
            volverACarrito();
        }
    }
}