package modelo;

public class Productos {

private String nombre;
    private double cantidad;
    private String unidad;

    public Productos(String nombre, double cantidad, String unidad) {

        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;

    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidad() {
        return unidad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;

    }
}

