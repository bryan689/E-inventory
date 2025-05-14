package datos;

import modelo.Recetas;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatosReceta {
    
    private static final String ARCHIVO_RECETAS = "recetas.csv";

    public static void guardarRecetas(Map<String, Recetas> recetas) {
        VerificarA.verificarA(ARCHIVO_RECETAS);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RECETAS))) {
            for (Recetas receta : recetas.values()) {
                bw.write(receta.getNombreR());
                for (Map.Entry<String, List<Object>> entry : receta.getIngredientes().entrySet()) {
                    String ingrediente = entry.getKey();
                    double cantidad = (double) entry.getValue().get(0);
                    String unidad = (String) entry.getValue().get(1);
                    bw.write("," + ingrediente + ":" + cantidad + ":" + unidad);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las recetas: " + e.getMessage());
        }
    }

    public static Map<String, Recetas> cargarRecetas() {
        VerificarA.verificarA(ARCHIVO_RECETAS);
        Map<String, Recetas> recetas = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_RECETAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 1) {
                    Recetas receta = new Recetas(datos[0]);
                    for (int i = 1; i < datos.length; i++) {
                        String[] ingredienteData = datos[i].split(":");
                        if (ingredienteData.length == 3) {
                            String ingrediente = ingredienteData[0];
                            double cantidad = Double.parseDouble(ingredienteData[1]);
                            String unidad = ingredienteData[2];
                            receta.agregarIngredientes(ingrediente, cantidad, unidad);
                        }
                    }
                    recetas.put(datos[0], receta);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las recetas: " + e.getMessage());
        }
        return recetas;
    }
    
}
