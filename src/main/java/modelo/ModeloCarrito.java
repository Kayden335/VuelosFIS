/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

public class ModeloCarrito {
        private ArrayList<ModeloBoleto> boletos = new ArrayList<>();
    private ModeloPago pago;              
    private boolean pagoRealizado;  

    public void agregar(ModeloBoleto b) {
        boletos.add(b);
    }

    public void eliminar(int index) {
        boletos.remove(index);
    }

    public ArrayList<ModeloBoleto> getBoletos() {
        return boletos;
    }

    public double getTotal() {
        double total = 0;
        for (ModeloBoleto b : boletos) {
            total += b.getPrecio();
        }
        return total;
    }

    // ====== PAGO ======

    public void setPago(ModeloPago pago) {
        this.pago = pago;
        this.pagoRealizado = true;
    }

    public ModeloPago getPago() {
        return pago;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }
}
