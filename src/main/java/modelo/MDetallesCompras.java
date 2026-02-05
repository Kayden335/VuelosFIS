package modelo;

public class MDetallesCompras {

    private int numeroPasajeros;
    private boolean tieneMaletas;
    private boolean vueloRegreso;

    public MDetallesCompras() {
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
