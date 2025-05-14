package controlador;

import ventana.Editar;
import modelo.Inventario;
import modelo.Recetas;
import datos.DatosProducto;
import datos.DatosReceta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EditarC {
    
    private Editar editar;
    private Inventario inventario;
    
    public EditarC(Editar editar, Inventario inventario) {
        
        this.editar = editar;
        this.inventario = inventario;
        
        // carga los productos del archivo
        Map<String, List<Object>> productos = DatosProducto.cargarProductos();
        inventario.setProductos(productos);

        // carga las recetas del archivo
        Map<String, Recetas> recetas = DatosReceta.cargarRecetas();
        inventario.setRecetas(recetas);
    
        //boton para editar producto
        editar.getBeditarP().addActionListener(e -> editarProducto());

        // Botón para eliminar un producto
        editar.getBeliminarP().addActionListener(e -> eliminarProducto());

        // Botón para cargar una receta en la tabla
        editar.getCargarR().addActionListener(e -> cargarReceta());

        // Botón para agregar un ingrediente a una receta
        editar.getBnuevoP().addActionListener(e -> agregarIngredienteAReceta());

        // Botón para guardar los cambios realizados en una receta
        editar.getBguardarC().addActionListener(e -> guardarCambiosReceta());

        // Botón para eliminar una receta
        editar.getBeliminarR().addActionListener(e -> eliminarReceta());

        // Botón para eliminar un ingrediente de una receta
        editar.getBeliminarRP().addActionListener(e -> eliminarIngredienteDeReceta());
        
    }
    
    //metodo para editar productos
   private void editarProducto() {
        String nombre = editar.getTnombreEP().getText().trim().toLowerCase();
        String cantidadStr = editar.getTcantidadEP().getText().trim();
        String unidad = editar.getTunidadEP().getText().trim();
        
        if (nombre.isEmpty() || cantidadStr.isEmpty() || unidad.isEmpty()) {
        JOptionPane.showMessageDialog(editar, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

        if (!inventario.existeProducto(nombre)) {
            JOptionPane.showMessageDialog(editar, "Producto no encontrado.");
            return;
        }

        try {
            List<Object> productoValores = inventario.getProductos().get(nombre);
            double cantidadActual = (double) productoValores.get(0);
            double cantidadIngresada = Double.parseDouble(cantidadStr);
            double nuevaCantidad = cantidadActual + cantidadIngresada;
            List<Object> datos = inventario.buscarProducto(nombre);
            datos.set(0, nuevaCantidad);
            datos.set(1, unidad);
            
            DatosProducto.guardarProductos(inventario.getProductos());
            
            JOptionPane.showMessageDialog(editar, "Producto editado con éxito.");
            
            editar.getTnombreEP().setText("");
            editar.getTcantidadEP().setText("");
            editar.getTunidadEP().setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(editar, "Cantidad inválida.");
        }
    }
   
   //metodo para eliminar producto de recetas
   private void eliminarProducto() {
    String producto = editar.getTnombreEP().getText().trim().toLowerCase();

    if (!inventario.existeProducto(producto)) {
        JOptionPane.showMessageDialog(editar, "El producto no existe en el inventario.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(editar,
            "¿Estás seguro de que deseas eliminar el producto \"" + producto + "\"?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        boolean eliminado = inventario.eliminarProducto(producto);
        if (eliminado) {
            DatosProducto.guardarProductos(inventario.getProductos());
            JOptionPane.showMessageDialog(editar, "Producto eliminado correctamente.");
            editar.getTnombreEP().setText("");
            editar.getTcantidadEP().setText("");
            editar.getTunidadEP().setText("");
        } else {
            JOptionPane.showMessageDialog(editar, "No se pudo eliminar el producto.");
        }
    }
}

   // metodo para cargar recetas
    private void cargarReceta() {
        String nombreReceta = editar.getTnombreER().getText().trim().toLowerCase();

        Recetas receta = inventario.getRecetas().get(nombreReceta);
        if (receta == null) {
            JOptionPane.showMessageDialog(editar, "La receta no existe.");
            return;
        }

        DefaultTableModel modelo = editar.getModeloTabla();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar
        for (Map.Entry<String, List<Object>> entry : receta.getIngredientes().entrySet()) {
            String ingrediente = entry.getKey();
            double cantidad = (double) entry.getValue().get(0);
            String unidad = (String) entry.getValue().get(1);

            modelo.addRow(new Object[]{ingrediente, cantidad, unidad});
        }
    }

    //metodo para agregar ingredientes en la receta
    private void agregarIngredienteAReceta() {
        String nombreIngrediente = editar.getTnuevoP().getText().trim().toLowerCase();
        String cantidadTexto = editar.getTcantidadER().getText().trim();
        String unidad = editar.getTunidadER().getText().trim();

        if (nombreIngrediente.isEmpty() || cantidadTexto.isEmpty() || unidad.isEmpty()) {
            JOptionPane.showMessageDialog(editar, "Por favor, complete todos los campos para agregar un ingrediente.");
            return;
        }

        try {
            double cantidad = Double.parseDouble(cantidadTexto);

            if (!inventario.getProductos().containsKey(nombreIngrediente)) {
                JOptionPane.showMessageDialog(editar, "El producto '" + nombreIngrediente + "' no existe en el inventario.");
                return;
            }

            DefaultTableModel modelo = editar.getModeloTabla();
            modelo.addRow(new Object[]{nombreIngrediente, cantidad, unidad});

            String nombreReceta = editar.getTnombreER().getText().trim().toLowerCase();
            Recetas receta = inventario.getRecetas().get(nombreReceta);

            if (receta != null) {
                List<Object> detalles = new ArrayList<>();
                detalles.add(cantidad);
                detalles.add(unidad);
                receta.getIngredientes().put(nombreIngrediente, detalles);
            } else {
                JOptionPane.showMessageDialog(editar, "La receta no existe en el inventario.");
            }

            editar.getTnuevoP().setText("");
            editar.getTcantidadER().setText("");
            editar.getTunidadER().setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(editar, "La cantidad debe ser un número válido.");
        }
    }

    // metodo para guardar guardar los cambios de la receta
    private void guardarCambiosReceta() {
        String nombreReceta = editar.getTnombreER().getText().trim().toLowerCase();

        if (nombreReceta.isEmpty()) {
            JOptionPane.showMessageDialog(editar, "Por favor, ingrese el nombre de la receta.");
            return;
        }

        Recetas receta = inventario.getRecetas().get(nombreReceta);
        if (receta == null) {
            JOptionPane.showMessageDialog(editar, "La receta no existe.");
            return;
        }

        DefaultTableModel modelo = editar.getModeloTabla();
        Map<String, List<Object>> nuevosIngredientes = new HashMap<>();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String ingrediente = (String) modelo.getValueAt(i, 0);
            double cantidad = Double.parseDouble(modelo.getValueAt(i, 1).toString());
            String unidad = (String) modelo.getValueAt(i, 2);

            List<Object> detalles = new ArrayList<>();
            detalles.add(cantidad);
            detalles.add(unidad);
            nuevosIngredientes.put(ingrediente, detalles);
        }

        receta.getIngredientes().clear();
        receta.getIngredientes().putAll(nuevosIngredientes);
        
        DatosReceta.guardarRecetas(inventario.getRecetas());

        JOptionPane.showMessageDialog(editar, "Los cambios se han guardado correctamente.");
    }

    //metodo para eliminar recetas
    private void eliminarReceta() {
        String nombreReceta = editar.getTnombreER().getText().trim().toLowerCase();

        if (nombreReceta.isEmpty()) {
            JOptionPane.showMessageDialog(editar, "Por favor, ingrese el nombre de la receta que desea eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(editar,
                "¿Está seguro de que desea eliminar la receta '" + nombreReceta + "'?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (inventario.getRecetas().remove(nombreReceta) != null) {
                DatosReceta.guardarRecetas(inventario.getRecetas());
                JOptionPane.showMessageDialog(editar, "La receta '" + nombreReceta + "' ha sido eliminada correctamente.");
                DefaultTableModel modelo = editar.getModeloTabla();
                modelo.setRowCount(0);
                editar.getTnombreER().setText("");
            } else {
                JOptionPane.showMessageDialog(editar, "La receta '" + nombreReceta + "' no existe.");
            }
        }
    }

    //metodo para eliminar ingredientes de las recetas
    private void eliminarIngredienteDeReceta() {
        int filaSeleccionada = editar.getTablaIngredientes().getSelectedRow();

        if (filaSeleccionada >= 0) {
            DefaultTableModel modelo = editar.getModeloTabla();
            String ingredienteEliminado = (String) modelo.getValueAt(filaSeleccionada, 0);

            modelo.removeRow(filaSeleccionada);

            String nombreReceta = editar.getTnombreER().getText().trim().toLowerCase();
            Recetas receta = inventario.getRecetas().get(nombreReceta);
            if (receta != null) {
                receta.getIngredientes().remove(ingredienteEliminado);
                DatosReceta.guardarRecetas(inventario.getRecetas());
            }

            JOptionPane.showMessageDialog(editar, "Ingrediente eliminado.");
        } else {
            JOptionPane.showMessageDialog(editar, "Por favor, seleccione un ingrediente para eliminar.");
        }
    }
    
}
