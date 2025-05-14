package controlador;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Inventario;
import ventana.Lista;
import ventana.VentasP;
import modelo.Recetas;
import modelo.Ventas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import datos.*;

public class Cmenu {
    private CardLayout cardLayout;
    private JPanel panelC; 
    private JLabel titulo;
    private Inventario inventario;
    private Lista lista;
    private VentasP ventasP;
    private Ventas ventas;

    public Cmenu(CardLayout cardLayout, JPanel panelC, JLabel titulo, Inventario inventario, Lista lista, VentasP ventasP, Ventas ventas) {
        this.cardLayout = cardLayout;
        this.panelC = panelC;
        this.titulo = titulo;
        this.inventario = inventario;
        this.lista = lista;
        this.ventasP = ventasP;
        this.ventas = ventas;
        
        // carga los productos del archivo
        Map<String, List<Object>> productos = DatosProducto.cargarProductos();
        inventario.setProductos(productos);

        // carga las recetas del archivo
        Map<String, Recetas> recetas = DatosReceta.cargarRecetas();
        inventario.setRecetas(recetas);

        // carga el historial desde el archivo
        List<String> historialVentas = DatosVenta.cargarVentas();
        ventas.setHistorial(historialVentas);
        
        // botón para procesar las ventas
        ventasP.getVenta().addActionListener(e -> procesarVenta());
    }

    //acciones de los botones del menu lateral
    public void asignarAcciones(JButton botonCrear, JButton botonEditar, JButton botonBuscar, JButton botonLista, JButton botonVentas) {
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("Agregar/Crear", "Agregar/Crear");
            }
        });

        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("Editar", "Editar");
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("Buscar", "Buscar");
            }
        });

        botonLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("Lista", "Lista");
                mostrarListaProductos();
                mostrarListaRecetas();
            }
        });

        botonVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("Ventas", "Ventas");
                mostrarHistorialVentas();
            }
        });
    }
    
    
    // metodos
    
    //cambiar paneles
    public void cambiarPanel(String nombrePanel, String nuevoTitulo) {
        cardLayout.show(panelC, nombrePanel);
        titulo.setText(nuevoTitulo);
    }
    
    //metodo para la lista de productos
    public void mostrarListaProductos() {
        Map<String, List<Object>> productos = inventario.getProductos();
        StringBuilder listaProductos = new StringBuilder();

        if (productos.isEmpty()) {
            listaProductos.append("No hay productos en el inventario.");
        } else {
            listaProductos.append(" LISTA DE PRODUCTOS \n");
            for (Map.Entry<String, List<Object>> entry : productos.entrySet()) {
                String nombre = entry.getKey();
                double cantidad = (double) entry.getValue().get(0);
                String unidad = (String) entry.getValue().get(1);
                listaProductos.append("- ").append(nombre).append(": ").append(cantidad).append(" ").append(unidad).append("\n");
            }
        }

        lista.getAlistaP().setText(listaProductos.toString());
    }

    // método para la lista de recetas
    public void mostrarListaRecetas() {
        Map<String, Recetas> recetas = inventario.getRecetas();
        StringBuilder listaRecetas = new StringBuilder();

        if (recetas.isEmpty()) {
            listaRecetas.append("No hay recetas registradas.");
        } else {
            listaRecetas.append(" LISTA DE RECETAS \n");
            for (Map.Entry<String, Recetas> entry : recetas.entrySet()) {
                String nombreReceta = entry.getKey();
                Recetas receta = entry.getValue();

                listaRecetas.append("- ").append(nombreReceta).append("\n");

                // listar ingredientes con unidades
                listaRecetas.append("  Ingredientes:\n");
                for (Map.Entry<String, List<Object>> ingrediente : receta.getIngredientes().entrySet()) {
                    String nombreIngrediente = ingrediente.getKey();
                    double cantidad = (double) ingrediente.getValue().get(0);
                    String unidad = (String) ingrediente.getValue().get(1);

                    listaRecetas.append("    • ").append(nombreIngrediente).append(": ")
                                .append(cantidad).append(" ").append(unidad).append("\n");
                }
            }
        }

        lista.getAlistaR().setText(listaRecetas.toString());
    }
    
    // método para procesar una venta
    private void procesarVenta() {
        String nombreReceta = ventasP.getTRvendida().getText().trim().toLowerCase();
        String cantidadStr = ventasP.getTCvendida().getText().trim();

        if (nombreReceta.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(ventasP, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cantidadVendida = Integer.parseInt(cantidadStr);
            if (cantidadVendida <= 0) {
                JOptionPane.showMessageDialog(ventasP, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!inventario.getRecetas().containsKey(nombreReceta)) {
                JOptionPane.showMessageDialog(ventasP, "La receta no existe en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Recetas receta = inventario.getRecetas().get(nombreReceta);
            Map<String, Double> faltantes = new HashMap<>();

            for (Map.Entry<String, List<Object>> entry : receta.getIngredientes().entrySet()) {
                String productoNombre = entry.getKey();
                double cantidadNecesaria = (double) entry.getValue().get(0) * cantidadVendida;

                if (!inventario.getProductos().containsKey(productoNombre)) {
                    JOptionPane.showMessageDialog(ventasP, "El producto '" + productoNombre + "' no está en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Object> productoValores = inventario.getProductos().get(productoNombre);
                double cantidadActual = (double) productoValores.get(0);

                if (cantidadActual < cantidadNecesaria) {
                    faltantes.put(productoNombre, cantidadNecesaria - cantidadActual);
                } else {
                    productoValores.set(0, cantidadActual - cantidadNecesaria);
                }

                // avisar si un producto está agotándo
                if (cantidadActual - cantidadNecesaria < 5) {
                    JOptionPane.showMessageDialog(ventasP, "El producto '" + productoNombre + "' está agotándose.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (!faltantes.isEmpty()) {
                StringBuilder mensaje = new StringBuilder("No se puede procesar la venta. Faltan los siguientes productos:\n");
                faltantes.forEach((producto, cantidadFaltante) -> mensaje.append("- ").append(producto).append(": ").append(cantidadFaltante).append("\n"));
                JOptionPane.showMessageDialog(ventasP, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // registrar la venta
            LocalDateTime fecha = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = fecha.format(formatter);

            ventas.getHistorial().add("Receta: " + nombreReceta + ", Cantidad: " + cantidadVendida + ", Fecha: " + fechaFormateada);
            
            // guardar ventas en el archivo
            DatosVenta.guardarVentas(ventas.getHistorial());
            DatosProducto.guardarProductos(inventario.getProductos());
            JOptionPane.showMessageDialog(ventasP, "Venta registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            ventasP.getTRvendida().setText("");
            ventasP.getTCvendida().setText("");

            mostrarHistorialVentas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventasP, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // método para mostrar el historial de ventas
    private void mostrarHistorialVentas() {
        StringBuilder historial = new StringBuilder();
        DatosVenta.cargarVentas();

        if (ventas.getHistorial().isEmpty()) {
            historial.append("No hay ventas registradas.");
        } else {
            for (String registro : ventas.getHistorial()) {
                historial.append(registro).append("\n");
            }
        }
        ventasP.getAhistorial().setText(historial.toString());
    }
    
}
