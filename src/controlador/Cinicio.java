package controlador;

import modelo.Usuarios;
import ventana.Inicio;
import ventana.MenuP;
import datos.DatosUsuario;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cinicio {

    private Map<String, Usuarios> usuarios;
    private Inicio login;

    public Cinicio(Inicio login, List<Usuarios> usuariosList) {
        this.login = login;
        this.usuarios = new HashMap<>();

        // convertir la lista de usuarios a un mapa
        for (Usuarios usuario : usuariosList) {
            usuarios.put(usuario.getUsuario(), usuario);
        }

        //botones para iniciar y registrar
        login.jButton7.addActionListener(e -> validar());
        login.jButton6.addActionListener(e -> registrar());
    }

    private void validar() {
        String usuarioIngresado = login.getUsuario1().getText();
        String contraseñaIngresada = new String(login.getContraseña1().getPassword());

        if (usuarios.containsKey(usuarioIngresado) &&
            usuarios.get(usuarioIngresado).getContraseña().equals(contraseñaIngresada)) {

            login.dispose();
            new MenuP().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(login, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrar() {
        String nuevoUsuario = login.getUsuario2().getText();
        if (nuevoUsuario == null || nuevoUsuario.trim().isEmpty()) return;

        String nuevaContraseña = new String(login.getContraseña2().getPassword());
        if (nuevaContraseña == null || nuevaContraseña.trim().isEmpty()) return;

        if (usuarios.containsKey(nuevoUsuario)) {
            JOptionPane.showMessageDialog(login, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Usuarios usuario = new Usuarios(nuevoUsuario, nuevaContraseña);
            usuarios.put(nuevoUsuario, usuario);

            // guardar el usuario en el archivo
            DatosUsuario.guardarUsuario(usuario);

            JOptionPane.showMessageDialog(login, "Usuario registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}