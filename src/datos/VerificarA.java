package datos;

import java.io.File;
import java.io.IOException;

public class VerificarA {
    
    public static void verificarA(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado: " + rutaArchivo);
                } else {
                    System.out.println("No se pudo crear el archivo: " + rutaArchivo);
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }
    
}
