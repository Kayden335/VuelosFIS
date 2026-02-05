package recursos.imagenes;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.net.URL;

//mofificado
public class PanelImagen extends JPanel {
    private Image imagen;

    public PanelImagen() {
        URL url = getClass().getResource("/imagenes/fondo-azul.jpg");
        
        if (url != null) {
            this.imagen = new ImageIcon(url).getImage();
        } else {
            System.err.println("No se encontró la imagen en la ruta especificada.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
