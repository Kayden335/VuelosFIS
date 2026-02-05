/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Lenovo
 */
public class ModeloPasajero {
    private String nombre;
    private String apellido;
    private String cedula;
    private int edad;
    private ModeloTipoPasajero tipo;
    private boolean equipajeExtra;
    private String asiento;

    // Constructor vacío (muy importante para MVC y formularios)
    public ModeloPasajero() {
    }

    // Constructor completo
    public ModeloPasajero(String nombre, String apellido, String cedula, int edad,
                     ModeloTipoPasajero tipo, boolean equipajeExtra, String asiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;
        this.tipo = tipo;
        this.equipajeExtra = equipajeExtra;
        this.asiento = asiento;
    }

    // Getters y Setters (PROFESIONAL)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ModeloTipoPasajero getTipo() {
        return tipo;
    }

    public void setTipo(ModeloTipoPasajero tipo) {
        this.tipo = tipo;
    }

    public boolean isEquipajeExtra() {
        return equipajeExtra;
    }

    public void setEquipajeExtra(boolean equipajeExtra) {
        this.equipajeExtra = equipajeExtra;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
}
