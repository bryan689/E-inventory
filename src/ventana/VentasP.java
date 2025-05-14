package ventana;

import javax.swing.*;
import java.awt.*; 

public class VentasP extends JPanel {
    
    private JLabel LRvendida;
    private JTextField TRvendida;
    private JLabel LCvendida;
    private JTextField TCvendida;
    private JButton venta;
    
    private JTextArea Ahistorial;

    public VentasP() {
        
        setLayout(new GridLayout(1, 2, 20, 0));
        
        //panel de ventas
        JPanel ventasP = new JPanel();
        ventasP = new Bordes(30, 30);
        ventasP.setBackground(Color.white);
        ventasP.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10); // Margenes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        LRvendida = new JLabel("Nombre de la receta");
        LRvendida.setFont(new Font("Arial", Font.PLAIN, 20));
        ventasP.add(LRvendida, gbc);
        
        gbc.gridy = 1;
        TRvendida = new JTextField(20);
        TRvendida.setPreferredSize(new Dimension(100, 25));
        ventasP.add(TRvendida, gbc);
        
        gbc.gridy = 2;
        LCvendida = new JLabel("Cantidad de recetas vendida");
        LCvendida.setFont(new Font("Arial", Font.PLAIN, 20));
        ventasP.add(LCvendida, gbc);
        
        gbc.gridy = 3;
        TCvendida = new JTextField(20);
        TCvendida.setPreferredSize(new Dimension(100, 25));
        ventasP.add(TCvendida, gbc);
        
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        venta = new JButton("Procesar ventas");
       ventasP.add(venta, gbc);
        
        
        add(ventasP);
        
        //panel historial
        JPanel historialR = new JPanel();
        historialR = new Bordes(30, 30);
        historialR.setBackground(Color.white);
        historialR.setLayout(new GridBagLayout());
        
        Ahistorial = new JTextArea("");
        historialR.add(Ahistorial);
        
        add(historialR);
       
    }
    
    public JLabel getLRvendida() { return LRvendida;}
    public JTextField getTRvendida() { return TRvendida;}
    public JLabel getLCvendida() { return LCvendida;}
    public JTextField getTCvendida() { return TCvendida;}
    public JButton getVenta() {return venta;}
    public JTextArea getAhistorial() { return Ahistorial;}


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
