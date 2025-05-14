package ventana;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Editar extends JPanel {
    
    private JLabel LnombreEP;
    private JTextField TnombreEP;
    private JLabel LcantidadEP;
    private JTextField TcantidadEP;
    private JLabel LunidadEP;
    private JTextField TunidadEP;
    private JButton BeditarP;
    private JButton BeliminarP;

    private JLabel LnombreER;
    private JTextField TnombreER;
    private JButton cargarR;
    private JTable tablaIngredientes;
    private DefaultTableModel modeloTabla;
    private JLabel LnuevoP;
    private JTextField TnuevoP;
    private JLabel LcantidadER;
    private JTextField TcantidadER;
    private JLabel LunidadER;
    private JTextField TunidadER;
    private JButton BnuevoP;
    private JButton BguardarC;
    private JButton BeliminarR;
    private JButton BeliminarRP;

    public Editar() {
        setLayout(new BorderLayout());

        // crear el TabbedPane para cambiar entre editar productos y recetas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para editar productos
        JPanel editarProductosPanel = crearEditarProductosPanel();
        tabbedPane.addTab("Editar Productos", editarProductosPanel);

        // panel para editar recetas
        JPanel editarRecetasPanel = crearEditarRecetasPanel();
        tabbedPane.addTab("Editar Recetas", editarRecetasPanel);

        // agregar el TabbedPane al panel principal
        add(tabbedPane, BorderLayout.CENTER);
    }

    // metodo para el panel editar producto
    private JPanel crearEditarProductosPanel() {
        JPanel editarP = new JPanel();
        editarP.setLayout(new GridBagLayout());
        editarP.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        LnombreEP = new JLabel("Nombre del producto a editar");
        LnombreEP.setFont(new Font("Arial", Font.PLAIN, 20));
        editarP.add(LnombreEP, gbc);

        gbc.gridy = 1;
        TnombreEP = new JTextField(20);
        editarP.add(TnombreEP, gbc);

        gbc.gridy = 2;
        LcantidadEP = new JLabel("Cantidad de producto");
        LcantidadEP.setFont(new Font("Arial", Font.PLAIN, 20));
        editarP.add(LcantidadEP, gbc);

        gbc.gridy = 3;
        TcantidadEP = new JTextField(20);
        editarP.add(TcantidadEP, gbc);

        gbc.gridy = 4;
        LunidadEP = new JLabel("Gramos/Litros/Cantidad");
        LunidadEP.setFont(new Font("Arial", Font.PLAIN, 20));
        editarP.add(LunidadEP, gbc);

        gbc.gridy = 5;
        TunidadEP = new JTextField(20);
        editarP.add(TunidadEP, gbc);

        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        BeditarP = new JButton("Guardar Producto");
        editarP.add(BeditarP, gbc);

        gbc.gridy = 7;
        BeliminarP = new JButton("Eliminar Producto");
        editarP.add(BeliminarP, gbc);

        return editarP;
    }

    // metodo para el panel editar recetas
    private JPanel crearEditarRecetasPanel() {
        JPanel editarR = new JPanel();
        editarR.setLayout(new BorderLayout(10, 10));

        // panel superior nombre de la receta y cargar botón
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        LnombreER = new JLabel("Nombre de la receta:");
        TnombreER = new JTextField(20);
        cargarR = new JButton("Cargar Receta");
        panelSuperior.add(LnombreER);
        panelSuperior.add(TnombreER);
        panelSuperior.add(cargarR);

        // Tabla de ingredientes
        modeloTabla = new DefaultTableModel(new String[]{"Ingrediente", "Cantidad", "Unidad"}, 0);
        tablaIngredientes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaIngredientes);

        // Panel inferior agregar y editar ingredientes
        JPanel panelInferior = new JPanel(new GridLayout(2, 1, 10, 10));

        // sub-panel para agregar nuevo ingrediente
        JPanel subPanelAgregar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        LnuevoP = new JLabel("Nuevo Ingrediente:");
        TnuevoP = new JTextField(15);
        LcantidadER = new JLabel("Cantidad:");
        TcantidadER = new JTextField(5);
        LunidadER = new JLabel("Unidad:");
        TunidadER = new JTextField(8);
        BnuevoP = new JButton("Agregar Ingrediente");
        subPanelAgregar.add(LnuevoP);
        subPanelAgregar.add(TnuevoP);
        subPanelAgregar.add(LcantidadER);
        subPanelAgregar.add(TcantidadER);
        subPanelAgregar.add(LunidadER);
        subPanelAgregar.add(TunidadER);
        subPanelAgregar.add(BnuevoP);

        // sub-panel para botones de guardar y eliminar
        JPanel subPanelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        BguardarC = new JButton("Guardar Cambios");
        BeliminarR = new JButton("Eliminar Receta");
        BeliminarRP = new JButton("Eliminar Ingrediente");
        subPanelBotones.add(BguardarC);
        subPanelBotones.add(BeliminarR);
        subPanelBotones.add(BeliminarRP);

        // agregar los sub-paneles al panel inferior
        panelInferior.add(subPanelAgregar);
        panelInferior.add(subPanelBotones);

        // agregar los paneles al panel de edición de recetas
        editarR.add(panelSuperior, BorderLayout.NORTH);
        editarR.add(scrollTabla, BorderLayout.CENTER);
        editarR.add(panelInferior, BorderLayout.SOUTH);

        return editarR;
    }

    public JTextField getTnombreEP() { return TnombreEP; }
    public JTextField getTcantidadEP() { return TcantidadEP; }
    public JTextField getTunidadEP() { return TunidadEP; }
    public JButton getBeditarP() { return BeditarP; }
    public JButton getBeliminarP() { return BeliminarP; }
    public JTextField getTnombreER() { return TnombreER; }
    public JButton getCargarR() { return cargarR; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JTable getTablaIngredientes() { return tablaIngredientes; }
    public JTextField getTnuevoP() { return TnuevoP; }
    public JTextField getTcantidadER() { return TcantidadER; }
    public JTextField getTunidadER() { return TunidadER; }
    public JButton getBnuevoP() { return BnuevoP; }
    public JButton getBguardarC() { return BguardarC; }
    public JButton getBeliminarR() { return BeliminarR; }
    public JButton getBeliminarRP() { return BeliminarRP; }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
