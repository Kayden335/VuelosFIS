/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class ModeloPago {

    private String metodo;

    // Tarjeta
    private String nombre;
    private String numero;
    private String cvv;
    private String fecha;

    // Transferencia
    private String cuenta;
    private String banco;

    // Paypal
    private String correo;
    private String password;

    public ModeloPago(String metodo) {
        this.metodo = metodo;
    }

    public void setTarjeta(String nombre, String numero, String cvv, String fecha) {
        this.nombre = nombre;
        this.numero = numero;
        this.cvv = cvv;
        this.fecha = fecha;
    }

    public void setTransferencia(String cuenta, String banco) {
        this.cuenta = cuenta;
        this.banco = banco;
    }

    public void setPaypal(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public boolean validar() {
        switch (metodo) {
            case "Tarjeta":
                return numero.matches("\\d{16}") &&
                       cvv.matches("\\d{3}") &&
                       nombre.length() > 3;
            case "Transferencia":
                return cuenta.matches("\\d{10,}") &&
                       banco.length() > 3;
            case "Paypal":
                return correo.contains("@") &&
                       password.length() >= 6;
        }
        return false;
    }

    public String getMetodo() {
        return metodo;
    }
}
