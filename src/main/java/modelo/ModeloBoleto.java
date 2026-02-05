package modelo;

public class ModeloBoleto {

    private String nombre;
    private boolean tieneMaletas;
    private boolean vueloRegreso;
    private String claseVuelo;
    private double precio;
    private String tipoVuelo; // "Solo ida" o "Ida y vuelta"
    private String vueloSeleccionado; // "Vuelo1", "Vuelo2", "Vuelo3"

    // Constructor COMPLETO
    public ModeloBoleto(String nombre, boolean tieneMaletas, boolean vueloRegreso, 
                       String claseVuelo, String tipoVuelo, String vueloSeleccionado) {
        this.nombre = nombre;
        this.tieneMaletas = tieneMaletas;
        this.vueloRegreso = vueloRegreso;
        this.claseVuelo = claseVuelo;
        this.tipoVuelo = tipoVuelo;
        this.vueloSeleccionado = vueloSeleccionado;
        this.precio = calcularPrecio();
    }

    // Método para calcular precio automáticamente
    private double calcularPrecio() {
        double base = 0;

        // Precio según vuelo seleccionado
        if (vueloSeleccionado != null) {
            switch (vueloSeleccionado) {
                case "Vuelo1":
                    base = 246.76;
                    break;
                case "Vuelo2":
                    base = 175.46;
                    break;
                case "Vuelo3":
                    base = 210.46;
                    break;
                default:
                    base = 200;
            }
        }

        // Modificar por clase
        switch (claseVuelo) {
            case "Económica":
                // base = base; // Precio base
                break;
            case "Premium":
                base = base * 1.5; // 50% más
                break;
            case "Ejecutiva":
                base = base * 2.0; // 100% más
                break;
        }

        // Si es vuelo de regreso, duplica
        if (vueloRegreso) {
            base *= 2;
        }

        // Si tiene maletas, agrega extra
        if (tieneMaletas) {
            base += 20;
        }

        return base;
    }

    // ===== Getters =====
    public String getNombre() {
        return nombre;
    }

    public boolean isTieneMaletas() {
        return tieneMaletas;
    }

    public boolean isVueloRegreso() {
        return vueloRegreso;
    }

    public String getClaseVuelo() {
        return claseVuelo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

    public String getVueloSeleccionado() {
        return vueloSeleccionado;
    }
    
    public String getDescripcionCompleta() {
        return nombre + " | " + claseVuelo + " | " + vueloSeleccionado + 
               " | " + (vueloRegreso ? "Ida y Vuelta" : "Solo Ida") + 
               " | $" + String.format("%.2f", precio);
    }
}