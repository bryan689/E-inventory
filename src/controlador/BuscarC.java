package controlador;

import ventana.Buscar;
import modelo.Inventario;
import datos.DatosProducto;
import datos.DatosReceta;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Recetas;

public class BuscarC {
    
    private Buscar buscar;
    private Inventario inventario;
    
    public BuscarC(Buscar buscar, Inventario inventario) {
        
        this.buscar = buscar;
        this.inventario = inventario;
    
        // carga los productos del archivo
        Map<String, List<Object>> productos = DatosProducto.cargarProductos();
        inventario.setProductos(productos);

        // carga las recetas del archivo
        Map<String, Recetas> recetas = DatosReceta.cargarRecetas();
        inventario.setRecetas(recetas);
    
        //accion de los botones para buscar productos y recetas
        buscar.getBbuscarP().addActionListener(e -> buscarP());
        buscar.getBbuscarR().addActionListener(e -> buscarR());
        
    }
    
    // metodos del panel buscar
    private void buscarP() {
        String nombre = buscar.getTBnombreP().getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa el nombre del producto a buscar.");
            return;
        }

        List<Object> producto = inventario.buscarProducto(nombre);

        if (producto != null) {
            double cantidad = (double) producto.get(0);
            String unidad = (String) producto.get(1);
            buscar.getLresultadoP().setText(nombre + ": " + cantidad + " " + unidad);
        } else {
            buscar.getLresultadoP().setText("Producto no encontrado.");
        }
        
        buscar.getTBnombreP().setText("");
    }
    
    // método para buscar una receta en el inventario
    private void buscarR() {
        String nombreReceta = buscar.getTBnombreR().getText().trim().toLowerCase();

        if (nombreReceta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa el nombre de la receta a buscar.");
            return;
        }

        Map<String, Recetas> recetas = inventario.getRecetas();
        Recetas receta = recetas.get(nombreReceta);

        if (receta != null) {
            StringBuilder resultado = new StringBuilder();
            resultado.append("Receta: ").append(receta.getNombreR()).append("\n");
            resultado.append("Ingredientes:\n");

            for (Map.Entry<String, List<Object>> entry : receta.getIngredientes().entrySet()) {
                String ingrediente = entry.getKey();
                double cantidad = (double) entry.getValue().get(0);
                String unidad = (String) entry.getValue().get(1);
                resultado.append("- ").append(ingrediente).append(": ").append(cantidad).append(" ").append(unidad).append("\n");
            }

            buscar.getAresultadoR().setText(resultado.toString());
               
        } else {
            buscar.getAresultadoR().setText("Receta no encontrada.");
        }
        
        buscar.getTBnombreR().setText("");
        
    }
    
}
