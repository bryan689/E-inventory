package e.inventory;

import ventana.Inicio;
import controlador.Cinicio;
import datos.VerificarA;
import datos.DatosUsuario;
import modelo.Usuarios;

import java.util.List;

public class EInventory {

    public static void main(String[] args) {
        // verificar y crear archivos de persistencia
        VerificarA.verificarA("usuarios.csv");

        // cargar los datos de usuario
        List<Usuarios> usuarios = DatosUsuario.cargarUsuarios();

        // inicia la interfaz gráfica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // crear la ventana de inicio
                Inicio login = new Inicio();

                // crear el controlador de inicio
                Cinicio controlador = new Cinicio(login, usuarios);

                // mostrar la ventana
                login.setVisible(true);
                login.setLocationRelativeTo(null); // centrar la ventana
            }
        });
    }
}

