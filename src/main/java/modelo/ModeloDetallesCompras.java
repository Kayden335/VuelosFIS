package modelo;

public class ModeloDetallesCompras {

    private int numeroPasajeros;
    private boolean tieneMaletas;
    private boolean vueloRegreso;

    public ModeloDetallesCompras() {
        this.numeroPasajeros = 0;
        this.tieneMaletas = false;
        this.vueloRegreso = false;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public boolean isTieneMaletas() {
        return tieneMaletas;
    }

    public void setTieneMaletas(boolean tieneMaletas) {
        this.tieneMaletas = tieneMaletas;
    }

    public boolean isVueloRegreso() {
        return vueloRegreso;
    }

    public void setVueloRegreso(boolean vueloRegreso) {
        this.vueloRegreso = vueloRegreso;
    }
}
