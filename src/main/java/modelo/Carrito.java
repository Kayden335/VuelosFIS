/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Boleto> boletos = new ArrayList<>();

    public void agregar(Boleto b) {
        boletos.add(b);
    }

    public void eliminar(int index) {
        boletos.remove(index);
    }

    public ArrayList<Boleto> getBoletos() {
        return boletos;
    }

    public double getTotal() {
        double total = 0;
        for (Boleto b : boletos) {
            total += b.getPrecio();
        }
        return total;
    }
}
