import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    public Color color;
    public Background() {
        this.color=Color.BLACK;
    }

    public void render(Graphics graphics){
        graphics.setColor(color); // set mau
        graphics.fillRect(0,0,1024,600);  // set hinh dang x, y , width,height
    }
}
