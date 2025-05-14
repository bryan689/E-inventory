package ventana;

import java.awt.*;

public class Degradado extends Bordes {
    private Color color1;
    private Color color2;
    private boolean usarDegradado = true; // Controla si se usa el degradado

    public Degradado(int arcWidth, int arcHeight, Color color1, Color color2) {
        super(arcWidth, arcHeight); // Llamamos al constructor de Bordes
        this.color1 = color1;
        this.color2 = color2;
    }

    public void setUsarDegradado(boolean usarDegradado) {
        this.usarDegradado = usarDegradado;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (usarDegradado) { // Si el degradado está activado
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

            g2.dispose();
        } else {
            super.paintComponent(g); // Usa el color de fondo normal si el degradado está desactivado
        }
    }
}

