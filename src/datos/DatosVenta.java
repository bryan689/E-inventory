package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DatosVenta {
    private static final String ARCHIVO_VENTAS = "ventas.csv";

    public static void guardarVentas(List<String> historialVentas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS))) {
            for (String venta : historialVentas) {
                bw.write(venta);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las ventas: " + e.getMessage());
        }
    }

    public static List<String> cargarVentas() {
    List<String> historialVentas = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VENTAS))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            historialVentas.add(linea);
        }
    } catch (IOException e) {
        System.out.println("Error al cargar las ventas: " + e.getMessage());
    }
    return historialVentas;
}
}