package controlador;

import ventana.Crear;
import datos.DatosProducto;
import datos.DatosReceta;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Recetas;
import modelo.Inventario;


public class CrearC {
    
    private Crear crear;
    private Inventario inventario;
    
    public CrearC(Crear crear, Inventario inventario) {
        
        this.crear = crear;
        this.inventario = inventario;
        
        // carga los productos del archivo
        Map<String, List<Object>> productos = DatosProducto.cargarProductos();
        inventario.setProductos(productos);

        // carga las recetas del archivo
        Map<String, Recetas> recetas = DatosReceta.cargarRecetas();
        inventario.setRecetas(recetas);
    
        //accion de los botones para guardar producto y recetas
        crear.getBguardarP().addActionListener(e -> agregarProducto());
        crear.getBtnGuardarP().addActionListener(e -> agregarIngredienteATabla());
        crear.getBguardarR().addActionListener(e -> guardarReceta());
        cargarProductosEnComboBox();
        
    }
    
    // metodo para agregar productos
    private void agregarProducto() {
    String nombre = crear.getTnombreP().getText().trim();
    String cantidadTexto = crear.getTcantidadP().getText().trim();
    String unidad = crear.getTunidadP().getText().trim();

    if (nombre.isEmpty() || cantidadTexto.isEmpty() || unidad.isEmpty()) {
        JOptionPane.showMessageDialog(crear, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int cantidad = Integer.parseInt(cantidadTexto);
        if (cantidad <= 0) throw new NumberFormatException();

        if (inventario.crearProducto(nombre, cantidad, unidad)) {
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
            cargarProductosEnComboBox(); // cargar productos en el combo box después de agregarlo
            
            //guardar productos en el archivo
            DatosProducto.guardarProductos(inventario.getProductos());
        } else {
            JOptionPane.showMessageDialog(null, "El producto ya existe");
        }

        crear.getTnombreP().setText("");
        crear.getTcantidadP().setText("");
        crear.getTunidadP().setText("");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(crear, "La cantidad debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    //metodos para el panel receta
    private void agregarIngredienteATabla() {
        String producto = (String) crear.getCproducto().getSelectedItem();
        String cantidadTexto = crear.getTcantidadR().getText().trim();

        if (producto == null || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completa todos los campos del ingrediente.");
            return;
        }

        try {
            double cantidad = Double.parseDouble(cantidadTexto);
            if (cantidad <= 0) throw new NumberFormatException();

            DefaultTableModel modelo = (DefaultTableModel) crear.getTablaIngredientes().getModel();
            modelo.addRow(new Object[]{producto, cantidad});
            crear.getTcantidadR().setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar ingrediente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // método para guardar la receta
    private void guardarReceta() {
        String nombreReceta = crear.getTnombreR().getText().trim();
        if (nombreReceta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la receta no puede estar vacío.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) crear.getTablaIngredientes().getModel();
        int rowCount = modelo.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(null, "Debes agregar al menos un ingrediente.");
            return;
        }

        if (inventario.getRecetas().containsKey(nombreReceta.toLowerCase())) {
            JOptionPane.showMessageDialog(null, "Ya existe una receta con ese nombre.");
            return;
        }

        Recetas receta = new Recetas(nombreReceta);
        try {
            for (int i = 0; i < rowCount; i++) {
                String producto = modelo.getValueAt(i, 0).toString();
                double cantidad = Double.parseDouble(modelo.getValueAt(i, 1).toString());

                List<Object> datosProducto = inventario.buscarProducto(producto);
                if (datosProducto == null) {
                    JOptionPane.showMessageDialog(null, "El producto '" + producto + "' no existe en el inventario.");
                    return;
                }

                String unidad = datosProducto.get(1).toString();
                receta.agregarIngredientes(producto, cantidad, unidad);
            }

            inventario.getRecetas().put(nombreReceta.toLowerCase(), receta);
            
            //guardar receta en el archivo
            DatosReceta.guardarRecetas(inventario.getRecetas());
            JOptionPane.showMessageDialog(null, "Receta creada exitosamente.");
            crear.getTnombreR().setText("");
            modelo.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la receta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // método para cargar productos en el combo box
    private void cargarProductosEnComboBox() {
        JComboBox<String> combo = crear.getCproducto();
        combo.removeAllItems();
        Map<String, List<Object>> productos = inventario.getProductos();
        for (String nombre : productos.keySet()) {
            combo.addItem(nombre);
        }
    }
    
}
