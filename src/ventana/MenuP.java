package ventana;

import javax.swing.*;
import java.awt.*;
import controlador.Cmenu;
import controlador.CrearC;
import controlador.BuscarC;
import controlador.EditarC;
import modelo.Inventario;
import modelo.Ventas;


public class MenuP extends javax.swing.JFrame {
    
    private JLabel titulo;
    private JPanel panelC; 
    private Cmenu controladorMenu; 
    private CrearC controladorC;
    private BuscarC controladorB;
    private EditarC controladorE;

    public MenuP() {
        setTitle("E-inventory");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        setLayout(new BorderLayout());

        // Menú lateral
        JPanel MLateral = new JPanel();
        MLateral = new Degradado(40, 40, new Color(0xFF6A00), new Color(0xFF8C22));
        MLateral.setPreferredSize(new Dimension(125, getHeight()));
        MLateral.setLayout(new GridLayout(6, 1, 0, 10));

        // Ícono principal
        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon iconoOriginal = new ImageIcon("src/recursos/image-Photoroom.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(imagenEscalada));
        MLateral.add(iconLabel);

        // botones
        JButton botonCrear = crearBotones("src/recursos/ima.png");
        MLateral.add(botonCrear);
        
        JButton botonEditar = crearBotones("src/recursos/pen-solid.png");
        MLateral.add(botonEditar);
        
        JButton botonBuscar = crearBotones("src/recursos/magnifying-glass-solid.png");
        MLateral.add(botonBuscar);
        
        JButton botonLista = crearBotones("src/recursos/clipboard-list-solid.png");
        MLateral.add(botonLista);
        
        JButton botonVentas = crearBotones("src/recursos/tags-solid.png");
        MLateral.add(botonVentas);

        // Contenedor del menú lateral
        JPanel contenedorML = new JPanel(new BorderLayout());
        contenedorML.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenedorML.add(MLateral, BorderLayout.WEST);

        // Título de los paneles
        titulo = new JLabel("Inicio");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.LEFT);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));

        JPanel contenedorT = new JPanel(new BorderLayout());
        contenedorT.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        contenedorT.add(titulo, BorderLayout.NORTH);
        contenedorML.add(contenedorT, BorderLayout.CENTER);

        add(contenedorML, BorderLayout.WEST);

        // Panel central
        panelC = new JPanel(new CardLayout());
        panelC.setBorder(BorderFactory.createEmptyBorder(150, 0, 60, 50));
        add(panelC, BorderLayout.CENTER);
        
        //panel vacio
        JPanel panelV = new JPanel(new CardLayout());
        
        //agregar paneles
        Crear crear = new Crear();
        Buscar buscar = new Buscar();
        Lista lista = new Lista();
        Editar editar = new Editar();
        VentasP ventasP = new VentasP();

        panelC.add(panelV, "Inicio");
        panelC.add(crear, "Agregar/Crear");
        panelC.add(editar, "Editar");
        panelC.add(buscar, "Buscar"); 
        panelC.add(lista, "Lista"); 
        panelC.add(ventasP, "Ventas");

        // llamar al controlador
        Inventario inventario = new Inventario();
        Ventas ventas = new Ventas();
        controladorMenu = new Cmenu((CardLayout) panelC.getLayout(), panelC, titulo, inventario, lista, ventasP, ventas);
        controladorC = new CrearC(crear, inventario);
        controladorB = new BuscarC(buscar, inventario);
        controladorE = new EditarC(editar, inventario);

        // asignar acciones de los botones para cambiar paneles
        controladorMenu.asignarAcciones(botonCrear, botonEditar, botonBuscar, botonLista, botonVentas);
        
        //mostrar el panel vacio
        CardLayout cl = (CardLayout) panelC.getLayout();
        cl.show(panelC, "Inicio");
}
    
    //ajustar los botones 
    private JButton crearBotones(String path){
        JButton boton = new JButton();
        ImageIcon icono = new ImageIcon(path);
        Image imagen = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagen));
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(60, 60));
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        return boton;
    }
    
      

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
