package ventana;

import java.awt.*;
import javax.swing.*;

public class Buscar extends JPanel {
    
    private JLabel LBnombreP;
    private JTextField TBnombreP;
    private JButton BbuscarP;
    private JLabel LresultadoP;
    
    private JLabel LBnombreR;
    private JTextField TBnombreR;
    private JButton BbuscarR;
    private JTextArea AresultadoR;

    public Buscar() {
        
        setLayout(new GridLayout(1, 2, 20, 0));
        
        //panel buscar porducto
        JPanel buscarP = new JPanel();
        buscarP = new Bordes(30, 30);
        buscarP.setBackground(Color.white);
        buscarP.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10); // Margenes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        LBnombreP = new JLabel("Ingrese el nombre del producto");
        LBnombreP.setFont(new Font("Arial", Font.PLAIN, 20));
        buscarP.add(LBnombreP, gbc);
        
        gbc.gridy = 1;
        TBnombreP = new JTextField(20);
        TBnombreP.setPreferredSize(new Dimension(100, 25));
        buscarP.add(TBnombreP, gbc);
        
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        BbuscarP = new JButton("Buscar producto");
        buscarP.add(BbuscarP, gbc);
        
        gbc.gridy = 3;
        LresultadoP = new JLabel("");
        LresultadoP.setFont(new Font("Arial", Font.PLAIN, 20));
        buscarP.add(LresultadoP, gbc);
        
        
        
        add(buscarP);
        
        //panel buscar receta
        JPanel buscarR = new JPanel();
        buscarR = new Bordes(30, 30);
        buscarR.setBackground(Color.white);
        buscarR.setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        LBnombreR = new JLabel("Ingrese el nombre de la receta");
        LBnombreR.setFont(new Font("Arial", Font.PLAIN, 20));
        buscarR.add(LBnombreR, gbc);
        
        gbc.gridy = 2;
        TBnombreR = new JTextField(20);
        TBnombreR.setPreferredSize(new Dimension(100, 25));
        buscarR.add(TBnombreR, gbc);
        
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        BbuscarR = new JButton("Buscar receta");
        buscarR.add(BbuscarR, gbc);
        
        gbc.gridy = 4;
        AresultadoR = new JTextArea("");
        AresultadoR.setFont(new Font("Arial", Font.PLAIN, 16));
        buscarR.add(AresultadoR, gbc);
        
        add(buscarR);
        
    }

    public JLabel getLBnombreP() { return LBnombreP;}
    public JTextField getTBnombreP() { return TBnombreP;}
    public JButton getBbuscarP() { return BbuscarP;}
    public JLabel getLresultadoP() { return LresultadoP;}
    public JLabel getLBnombreR() { return LBnombreR;}
    public JTextField getTBnombreR() { return TBnombreR;}
    public JButton getBbuscarR() { return BbuscarR;}
    public JTextArea getAresultadoR() { return AresultadoR;}
    
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
