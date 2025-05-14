package modelo;

import java.util.*;

public class Recetas {
    
    private String nombreR;
    private Map<String, List<Object>> ingredientes;

    public Recetas(String nombreR) {
        this.nombreR = nombreR;
        this.ingredientes = new HashMap<>();
    }

    public String getNombreR() {
        return nombreR;
    }

    public Map<String, List<Object>> getIngredientes() {
        return ingredientes;
    }

    public void agregarIngredientes(String producto, double cantidad, String unidad) {
    List<Object> detalles = new ArrayList<>();
    detalles.add(cantidad); // almacena la cantidad
    detalles.add(unidad);  // almacena la unidad
    ingredientes.put(producto.toLowerCase(), detalles);
}
    
}
