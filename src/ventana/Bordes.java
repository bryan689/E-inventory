package ventana;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Bordes extends JPanel {
    protected int arcWidth;
    protected int arcHeight;

    public Bordes(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // Para que los bordes redondeados se vean correctamente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo con color normal
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        g2.dispose();
    }
}
