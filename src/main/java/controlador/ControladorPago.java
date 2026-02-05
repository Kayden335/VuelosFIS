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

    public ControladorPago(ModeloCarrito carrito, ModeloRegistro usuario) {
        this.carrito = carrito;
        this.usuario = usuario;
        
        System.out.println("DEBUG: ControladorPago creado para usuario: " + 
                          (usuario != null ? usuario.getNombre() : "null"));

        // Crear vistas
        vistaCarrito = new VistaCarrito(carrito);
        vistaPago = new VistaPago(carrito);

        vistaCarrito.setVisible(true);

        // Listeners
        vistaCarrito.getBtnContinuar().addActionListener(e -> irAPago());
        vistaPago.getBtnRegresar().addActionListener(e -> volverACarrito());
        vistaPago.getBtnCancelar().addActionListener(e -> cancelar());
        vistaPago.getBtnPagar().addActionListener(e -> procesarPago());
        vistaPago.getBtnContinuar().addActionListener(e -> continuarAFactura());
    }

    private void irAPago() {
        System.out.println("DEBUG: Yendo a pago...");
        vistaCarrito.dispose(); // CERRAR VistaCarrito
        vistaPago.setVisible(true); // ABRIR VistaPago
    }

    private void volverACarrito() {
        System.out.println("DEBUG: Volviendo al carrito...");
        vistaPago.dispose(); // CERRAR VistaPago
        vistaCarrito.setVisible(true); // ABRIR VistaCarrito
    }

    private void procesarPago() {
        System.out.println("DEBUG: Procesando pago...");
        
        // Verificar que se haya seleccionado un método de pago
        if (!vistaPago.tieneMetodoPagoSeleccionado()) {
            JOptionPane.showMessageDialog(vistaPago, 
                "❌ Debe seleccionar un método de pago", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener datos del pago según el método seleccionado
        ModeloPago pago = vistaPago.obtenerPago();

        // Validar los datos del pago
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
        
        // Confirmar pago
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
        
        // Guardar datos de pago
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

        // Bloquear el botón de pago
        vistaPago.bloquearPago();
        
        System.out.println("DEBUG: Pago realizado correctamente con método: " + pago.getMetodo());
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

        // Calcular total
        double total = carrito.getTotal();
        
        // Crear Factura
        VistaFactura vFactura = new VistaFactura(carrito);

        ModeloFactura mFactura = new ModeloFactura();
        ControladorFactura cFactura = new ControladorFactura(vFactura, mFactura);
        
        // Obtener boletos del carrito
        List<ModeloBoleto> boletos = carrito.getBoletos();
        
        // Verificar que tenemos todos los datos
        if (usuario == null) {
            JOptionPane.showMessageDialog(vistaPago, 
                "Error: No hay información de usuario para la factura",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (datosPago == null) {
            datosPago = new ModeloPago("Efectivo"); // Por defecto
        }
        
        System.out.println("DEBUG: Mostrando factura con " + boletos.size() + " boletos");
        
        // Mostrar factura con TODOS los datos
        cFactura.mostrarFactura(usuario, datosPago, boletos, total);
        
        // CERRAR VENTANA DE PAGO
        vistaPago.dispose();
        // NO es necesario cerrar vistaCarrito porque ya se cerró en irAPago()
        
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