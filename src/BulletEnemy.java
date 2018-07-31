import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletEnemy {
    public BufferedImage image;
    public  Vector2D position;
    public Vector2D velocity;

    public BulletEnemy() {
        this.position = new Vector2D();
        this.velocity = new Vector2D(2,0);
    }
    public void run(){
        this.position .addUp(this.velocity);
    }
    public void render(Graphics graphics){
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, 10, 5, null);
    }
}
