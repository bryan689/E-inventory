package datos;

import java.io.*;
import java.util.*;

public class DatosProducto {
    private static final String ARCHIVO_PRODUCTOS = "productos.csv";

    public static void guardarProductos(Map<String, List<Object>> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS))) {
            for (Map.Entry<String, List<Object>> entry : productos.entrySet()) {
                String nombre = entry.getKey();
                List<Object> detalles = entry.getValue();
                double cantidad = (double) detalles.get(0);
                String unidad = (String) detalles.get(1);
                bw.write(nombre + "," + cantidad + "," + unidad);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los productos: " + e.getMessage());
        }
    }

    public static Map<String, List<Object>> cargarProductos() {
        Map<String, List<Object>> productos = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PRODUCTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String nombre = datos[0];
                    double cantidad = Double.parseDouble(datos[1]);
                    String unidad = datos[2];

                    List<Object> detalles = Arrays.asList(cantidad, unidad);
                    productos.put(nombre, detalles);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los productos: " + e.getMessage());
        }
        return productos;
    }
}