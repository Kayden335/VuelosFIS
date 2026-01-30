/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 package modelo;

public class Boleto {

    private String ruta;
    private double precio;

    public Boleto(String ruta, double precio) {
        this.ruta = ruta;
        this.precio = precio;
    }

    public double getPrecio() {   // 👈 ESTE FALTABA
        return precio;
    }

    @Override
    public String toString() {
        return ruta + " - $" + precio;
    }

    public static Carrito crearCarritoPrueba() {
        Carrito c = new Carrito();
        c.agregar(new Boleto("Quito → Guayaquil", 120));
        c.agregar(new Boleto("Guayaquil → Cuenca", 90));
        c.agregar(new Boleto("Cuenca → Quito", 110));
        return c;
    }
}

