package modelo;

import java.util.*;

public class Inventario {

    private Map<String, List<Object>> productos;
    private Map<String, Recetas> recetas;

    public Inventario() {
        productos = new HashMap<>();
        recetas = new HashMap<>();
    }

    public Map<String, List<Object>> getProductos() {
        return productos;
    }

    public void setProductos(Map<String, List<Object>> productos) {
        this.productos = productos;
    }

    public Map<String, Recetas> getRecetas() {
        return recetas;
    }

    public void setRecetas(Map<String, Recetas> recetas) {
        this.recetas = recetas;
    }

    // métodos para Productos
    public boolean crearProducto(String nombre, double cantidad, String unidad) {
        nombre = nombre.toLowerCase();
        if (productos.containsKey(nombre)) {
            return false; // Ya existe el producto
        }
        List<Object> valores = Arrays.asList(cantidad, unidad);
        productos.put(nombre, valores);
        return true;
    }

    public boolean eliminarProducto(String nombre) {
        nombre = nombre.toLowerCase();
        if (productos.containsKey(nombre)) {
            productos.remove(nombre);
            return true;
        }
        return false;
    }

    public boolean existeProducto(String nombre) {
        return productos.containsKey(nombre.toLowerCase());
    }

    public List<Object> buscarProducto(String nombre) {
        return productos.get(nombre.toLowerCase());
    }


}
