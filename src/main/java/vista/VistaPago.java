/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelo.ModeloCarrito;
import modelo.ModeloPago;

/**
 *
 * @author Lenovo
 */
public class VistaPago extends javax.swing.JFrame {
   

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaPago.class.getName());
  // CAMPOS GLOBALES
    private JTextField txtBanco;
    private JTextField txtCuenta;
    private JTextField txtReferencia;
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JTextField txtNombre;
    private JTextField txtNumero;
    private JPasswordField txtCVV; // Cambié esto a JPasswordField
    private JTextField txtFecha;
    private boolean pagoRealizado = false;
    private ModeloCarrito carrito;
    
    // CONSTRUCTORES - ¡AÑADE ESTO!
    
    // Constructor con ModeloCarrito
    public VistaPago(ModeloCarrito carrito) {
        initComponents();
        setLocationRelativeTo(null);
        this.carrito = carrito;
        if (lblTotal != null && carrito != null) {
            lblTotal.setText("Total: $" + String.format("%.2f", carrito.getTotal()));
        }
    }
    
    // Constructor vacío por si acaso
    public VistaPago() {
        initComponents();
        setLocationRelativeTo(null);
        if (lblTotal != null) {
            lblTotal.setText("Total: $0.00");
        }
    }
    
    // AGREGAR ESTOS GETTERS PÚBLICOS PARA LOS RADIO BUTTONS
    public JRadioButton getRbTarjeta() {
        return rbTarjeta;
    }
    
    public JRadioButton getRbTransferencia() {
        return rbTransferencia;
    }
    
    public JRadioButton getRbPaypal() {
        return rbPaypal;
    }
    
    public void marcarPagoRealizado() {
        pagoRealizado = true;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }
    
    public javax.swing.JButton getBtnPagar() {
        return btnPagar;
    }

    public javax.swing.JButton getBtnCancelar() {
        return btnCancelar;
    }

    public javax.swing.JButton getBtnRegresar() {
        return btnRegresar;
    }

    public javax.swing.JButton getBtnContinuar() {
        return btnContinuar;
    }
    
    public void bloquearPago() {
        btnPagar.setEnabled(false);
        btnPagar.setText("✅ PAGADO");
    }

    public ModeloPago obtenerPago() {
        if (rbTarjeta.isSelected()) {
            ModeloPago p = new ModeloPago("Tarjeta");
            p.setTarjeta(
                txtNombre.getText(),
                txtNumero.getText(),
                txtCVV.getText(), // txtCVV es JPasswordField, pero getText() funciona
                txtFecha.getText()
            );
            return p;
        }

        if (rbTransferencia.isSelected()) {
            ModeloPago p = new ModeloPago("Transferencia");
            p.setTransferencia(
                txtCuenta.getText(),
                txtBanco.getText()
            );
            return p;
        }

        if (rbPaypal.isSelected()) {
            ModeloPago p = new ModeloPago("Paypal");
            p.setPaypal(
                txtCorreo.getText(),
                new String(txtPassword.getPassword())
            );
            return p;
        }

        return null;
    }
    
    // AGREGAR MÉTODO PARA VERIFICAR SI HAY MÉTODO DE PAGO SELECCIONADO
    public boolean tieneMetodoPagoSeleccionado() {
       return rbTarjeta.isSelected() || rbTransferencia.isSelected() || rbPaypal.isSelected();
    }
    public boolean hayCamposVacios() {
    // Verificar según el método seleccionado
    if (rbTarjeta.isSelected()) {
        return txtNombre.getText().trim().isEmpty() ||
               txtNumero.getText().trim().isEmpty() ||
               new String(txtCVV.getPassword()).trim().isEmpty() ||
               txtFecha.getText().trim().isEmpty();
    }
    
    if (rbTransferencia.isSelected()) {
        return txtBanco.getText().trim().isEmpty() ||
               txtCuenta.getText().trim().isEmpty();
    }
    
    if (rbPaypal.isSelected()) {
        return txtCorreo.getText().trim().isEmpty() ||
               new String(txtPassword.getPassword()).trim().isEmpty();
    }
    
    return true; // Si no hay método seleccionado
}
    // También añade este método setter por si acaso
    public void setCarrito(ModeloCarrito carrito) {
        this.carrito = carrito;
        if (lblTotal != null && carrito != null) {
            lblTotal.setText("Total: $" + String.format("%.2f", carrito.getTotal()));
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        rbTransferencia = new javax.swing.JRadioButton();
        rbPaypal = new javax.swing.JRadioButton();
        rbTarjeta = new javax.swing.JRadioButton();
        panelMetodo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        lblFondo2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VuelosFis.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(940, 30, 100, 80);

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 48)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/8494.jpg"))); // NOI18N
        jLabel1.setText("MÉTODO DE PAGO");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 30, 1190, 90);

        lblTotal.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Total: ");
        lblTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel1.add(lblTotal);
        lblTotal.setBounds(60, 160, 310, 40);

        buttonGroup1.add(rbTransferencia);
        rbTransferencia.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        rbTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        rbTransferencia.setText("Transferencia");
        rbTransferencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbTransferencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rbTransferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonS.png"))); // NOI18N
        rbTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTransferenciaActionPerformed(evt);
            }
        });
        jPanel1.add(rbTransferencia);
        rbTransferencia.setBounds(470, 230, 140, 40);

        buttonGroup1.add(rbPaypal);
        rbPaypal.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        rbPaypal.setForeground(new java.awt.Color(255, 255, 255));
        rbPaypal.setText("Paypal");
        rbPaypal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbPaypal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rbPaypal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonS.png"))); // NOI18N
        rbPaypal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPaypalActionPerformed(evt);
            }
        });
        jPanel1.add(rbPaypal);
        rbPaypal.setBounds(820, 230, 140, 40);

        buttonGroup1.add(rbTarjeta);
        rbTarjeta.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        rbTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        rbTarjeta.setText("Tarjeta");
        rbTarjeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbTarjeta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rbTarjeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonS.png"))); // NOI18N
        rbTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTarjetaActionPerformed(evt);
            }
        });
        jPanel1.add(rbTarjeta);
        rbTarjeta.setBounds(110, 230, 130, 40);

        panelMetodo.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/9938414.jpg"))); // NOI18N

        javax.swing.GroupLayout panelMetodoLayout = new javax.swing.GroupLayout(panelMetodo);
        panelMetodo.setLayout(panelMetodoLayout);
        panelMetodoLayout.setHorizontalGroup(
            panelMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMetodoLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 669, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMetodoLayout.setVerticalGroup(
            panelMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMetodoLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelMetodo);
        panelMetodo.setBounds(210, 300, 670, 300);

        btnContinuar.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Boton.jpg"))); // NOI18N
        btnContinuar.setText("Continuar");
        btnContinuar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        jPanel1.add(btnContinuar);
        btnContinuar.setBounds(810, 640, 210, 50);

        btnCancelar.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonT (1).png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(330, 630, 150, 40);

        btnRegresar.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Boton.jpg"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar);
        btnRegresar.setBounds(40, 640, 190, 40);

        btnPagar.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonT (1).png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPagar);
        btnPagar.setBounds(590, 630, 150, 40);

        lblFondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pagobase.jpg"))); // NOI18N
        jPanel1.add(lblFondo2);
        lblFondo2.setBounds(0, 0, 1080, 730);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTransferenciaActionPerformed
    panelMetodo.removeAll();
        panelMetodo.setLayout(new java.awt.GridBagLayout());
        panelMetodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        txtBanco = new JTextField(15);
        txtCuenta = new JTextField(15);
        txtReferencia = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        panelMetodo.add(new JLabel("Banco:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtBanco, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelMetodo.add(new JLabel("Cuenta:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtCuenta, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelMetodo.add(new JLabel("Referencia:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtReferencia, gbc);

        panelMetodo.revalidate();
        panelMetodo.repaint();

    }//GEN-LAST:event_rbTransferenciaActionPerformed

    private void rbPaypalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPaypalActionPerformed
     panelMetodo.removeAll();
        panelMetodo.setLayout(new java.awt.GridBagLayout());
        panelMetodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        txtCorreo = new JTextField(15);
        txtPassword = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        panelMetodo.add(new JLabel("Correo PayPal:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelMetodo.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtPassword, gbc);

        panelMetodo.revalidate();
        panelMetodo.repaint();

    }//GEN-LAST:event_rbPaypalActionPerformed

    private void rbTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTarjetaActionPerformed
    panelMetodo.removeAll();
panelMetodo.setLayout(new java.awt.GridBagLayout());
panelMetodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));

 panelMetodo.removeAll();
        panelMetodo.setLayout(new java.awt.GridBagLayout());
        panelMetodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(15);
        txtNumero = new JTextField(15);
        txtCVV = new JPasswordField(4); // Cambiado a JPasswordField
        txtFecha = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        panelMetodo.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelMetodo.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelMetodo.add(new JLabel("CVV:"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtCVV, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelMetodo.add(new JLabel("Fecha (MM/AA):"), gbc);
        gbc.gridx = 1;
        panelMetodo.add(txtFecha, gbc);

        panelMetodo.revalidate();
        panelMetodo.repaint();
    }//GEN-LAST:event_rbTarjetaActionPerformed
  

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        
  // 1. Verificar que ya se haya pagado
    if (!isPagoRealizado()) {
        JOptionPane.showMessageDialog(this,
            "Primero debes realizar el pago");
        return;
    }

    // 2. Abrir la factura pasando el carrito
    VistaFactura factura = new VistaFactura(carrito);
    factura.setVisible(true);

    // 3. Cerrar esta ventana
    this.dispose();
    }//GEN-LAST:event_btnContinuarActionPerformed
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
this.dispose(); // cierra pago
    new VistaCarrito(carrito).setVisible(true); // vuelve al carrito    
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        if (!tieneMetodoPagoSeleccionado()) {
        JOptionPane.showMessageDialog(this,
            "⚠️ Selecciona un método de pago primero",
            "Advertencia",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 2. Verificar que NO haya campos vacíos (NUEVA VALIDACIÓN)
    if (hayCamposVacios()) {
        JOptionPane.showMessageDialog(this,
            "❌ Complete todos los campos requeridos\n" +
            "No deje espacios en blanco",
            "Campos incompletos",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    ModeloPago pago = obtenerPago();

    if (pago == null) {
        JOptionPane.showMessageDialog(this,
            "❌ Datos de pago incompletos",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 3. Validar que los datos sean correctos
    if (!pago.validar()) {
        JOptionPane.showMessageDialog(this,
            "❌ Datos inválidos\n" +
            "Revise la información ingresada",
            "Error de validación",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    marcarPagoRealizado();
    bloquearPago();

    JOptionPane.showMessageDialog(this,
        "✅ Pago realizado con éxito");
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
      this.dispose();
    new VistaCarrito(carrito).setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    java.awt.EventQueue.invokeLater(() -> {
    ModeloCarrito carrito = new ModeloCarrito();
    new VistaPago(carrito).setVisible(true);
    });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondo2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelMetodo;
    private javax.swing.JRadioButton rbPaypal;
    private javax.swing.JRadioButton rbTarjeta;
    private javax.swing.JRadioButton rbTransferencia;
    // End of variables declaration//GEN-END:variables

    
}
