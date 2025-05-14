package ventana;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Crear extends JPanel {
    
    private JLabel LnombreP;
    private JTextField TnombreP;
    private JLabel LcantidadP;
    private JTextField TcantidadP;
    private JLabel LunidadP;
    private JTextField TunidadP;
    private JButton BguardarP;
    
    private JLabel LnombreR;
    private JTextField TnombreR;
    private JLabel LnombreEP;
    private JComboBox Cproducto;
    private JLabel LcantidadR;
    private JTextField TcantidadR;
    private JButton btnGuardarP;
    private JButton BguardarR;
    private JTable tablaIngredientes;
    private DefaultTableModel modeloTabla;

    public Crear() {
        
        setLayout(new GridLayout(1, 2, 20, 0));
        
        //panel agregar producto
        JPanel agregarP = new JPanel();
        agregarP = new Bordes(30, 30);
        agregarP.setBackground(Color.white);
        agregarP.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10); // Margenes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        LnombreP = new JLabel("Nombre del producto");
        LnombreP.setFont(new Font("Arial", Font.PLAIN, 20));
        agregarP.add(LnombreP, gbc);
        
        gbc.gridy = 1;
        TnombreP = new JTextField(20);
        TnombreP.setPreferredSize(new Dimension(100, 25));
        agregarP.add(TnombreP, gbc);
        
        gbc.gridy = 2;
        LcantidadP = new JLabel("Cantidad de producto");
        LcantidadP.setFont(new Font("Arial", Font.PLAIN, 20));
        agregarP.add(LcantidadP, gbc);
        
        gbc.gridy = 3;
        TcantidadP = new JTextField(20);
        TcantidadP.setPreferredSize(new Dimension(20, 25));
        agregarP.add(TcantidadP, gbc);
        
        gbc.gridy = 4;
        LunidadP = new JLabel("Gramos/Litros/Unidad");
        LunidadP.setFont(new Font("Arial", Font.PLAIN, 20));
        agregarP.add(LunidadP, gbc);
        
        gbc.gridy = 5;
        TunidadP = new JTextField(20);
        TunidadP.setPreferredSize(new Dimension(100, 25));
        agregarP.add(TunidadP, gbc);
        
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        BguardarP = new JButton("Guardar producto");
        agregarP.add(BguardarP, gbc);
        
        add(agregarP);
        
        //panel crear receta
        JPanel crearR = new JPanel();
        crearR = new Bordes(30, 30);
        crearR.setBackground(Color.white);
        crearR.setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        LnombreR = new JLabel("Nombre de la receta");
        LnombreR.setFont(new Font("Arial", Font.PLAIN, 20));
        crearR.add(LnombreR, gbc);
        
        gbc.gridy = 1;
        TnombreR = new JTextField(20);
        TnombreR.setPreferredSize(new Dimension(100, 25));
        crearR.add(TnombreR, gbc);
        
        gbc.gridy = 2;
        LnombreEP = new JLabel("Elegir producto");
        LnombreEP.setFont(new Font("Arial", Font.PLAIN, 20));
        crearR.add(LnombreEP, gbc);
        
        gbc.gridy = 3;
        Cproducto = new JComboBox<>();
        crearR.add(Cproducto, gbc);
        
        gbc.gridy = 4;
        LcantidadR = new JLabel("Cantidad de producto");
        LcantidadR.setFont(new Font("Arial", Font.PLAIN, 20));
        crearR.add(LcantidadR, gbc);
        
        gbc.gridy = 5;
        TcantidadR = new JTextField(20);
        TcantidadR.setPreferredSize(new Dimension(100, 25));
        crearR.add(TcantidadR, gbc);
        
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardarP = new JButton("Guardar producto");
        crearR.add(btnGuardarP, gbc);
        
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        BguardarR = new JButton("Guardar receta");
        crearR.add(BguardarR, gbc);
        
         // tabla para los ingredientes de la receta
        modeloTabla = new DefaultTableModel(new String[]{"Producto", "Cantidad"}, 0);
        tablaIngredientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaIngredientes);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        crearR.add(scrollPane, gbc);
        
        add(crearR);
        
    }
    
    public JLabel getLnombreP() { return LnombreP;}
    public JTextField getTnombreP() { return TnombreP;}
    public JLabel getLcantidadP() { return LcantidadP;}
    public JTextField getTcantidadP() { return TcantidadP;}
    public JLabel getLunidadP() { return LunidadP;}
    public JTextField getTunidadP() { return TunidadP;}
    public JButton getBguardarP() { return BguardarP;}
    public JLabel getLnombreR() { return LnombreR;}
    public JTextField getTnombreR() { return TnombreR;}
    public JComboBox getCproducto() { return Cproducto;}
    public JLabel getLcantidadR() { return LcantidadR;}
    public JTextField getTcantidadR() { return TcantidadR;}
    public JButton getBtnGuardarP() { return btnGuardarP;}
    public JButton getBguardarR() { return BguardarR;}
    public JTable getTablaIngredientes() { return tablaIngredientes; }


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
