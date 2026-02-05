package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloCompra {

    private int totalPasajeros;
    private int pasajerosIngresados;
    private List<ModeloBoleto> boletos;

    public ModeloCompra(int totalPasajeros) {
        this.totalPasajeros = totalPasajeros;
        this.pasajerosIngresados = 0;
        this.boletos = new ArrayList<>();
    }

    public void agregarBoleto(ModeloBoleto boleto) {
        boletos.add(boleto);
        pasajerosIngresados++;
    }

    public boolean estaCompleta() {
        return pasajerosIngresados >= totalPasajeros;
    }

    public List<ModeloBoleto> getBoletos() {
        return boletos;
    }

    public int getPasajerosIngresados() {
        return pasajerosIngresados;
    }

    public int getTotalPasajeros() {
        return totalPasajeros;
    }
}
