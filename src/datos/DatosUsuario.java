package datos;

import modelo.Usuarios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatosUsuario {
    
    private static final String ARCHIVO_USUARIOS = "usuarios.csv";

    public static void guardarUsuario(Usuarios usuario) {
        VerificarA.verificarA(ARCHIVO_USUARIOS);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            bw.write(usuario.getUsuario() + "," + usuario.getContraseña());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public static List<Usuarios> cargarUsuarios() {
        VerificarA.verificarA(ARCHIVO_USUARIOS);
        List<Usuarios> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    usuarios.add(new Usuarios(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
}
