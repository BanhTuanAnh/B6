import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class Star {
    public BufferedImage image; // mac dinh ko khai bao truy xuat thi la publi
    public Vector2D position;
    public int width;
    public int height;
    public Vector2D velocity;
    public Star() { // constructer alt+fn+insret
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }

    public void run() {
        this.position.subtractBy(this.velocity);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, this.width, this.height, null);
    }
}
