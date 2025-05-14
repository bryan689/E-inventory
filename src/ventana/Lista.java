package ventana;

import javax.swing.*;
import java.awt.*;


public class Lista extends JPanel {

    private JTextArea AlistaP;
    
    private JTextArea AlistaR;
    
    public Lista() {
        
        setLayout(new GridLayout(1, 2, 20, 0));
        
        //panel lista de productos
        JPanel listaP = new JPanel();
        listaP = new Bordes(30, 30);
        listaP.setBackground(Color.white);
        listaP.setLayout(new GridBagLayout());
        
        AlistaP = new JTextArea("");
        listaP.add(AlistaP);
        
        add(listaP);
        
        //panel lista de recetas
        JPanel listaR = new JPanel();
        listaR = new Bordes(30, 30);
        listaR.setBackground(Color.white);
        listaR.setLayout(new GridBagLayout());
        
        AlistaR = new JTextArea("");
        listaR.add(AlistaR);
        
        add(listaR);
        
        
    }

    public JTextArea getAlistaP() { return AlistaP;}
    public JTextArea getAlistaR() { return AlistaR;}
    
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
