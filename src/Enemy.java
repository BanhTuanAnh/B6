import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemy {
    public Vector2D position ;
    public Vector2D velocity;
    public int width;
    public int height;
    public BufferedImage image;
    public List<List<BulletEnemy>> bulletEnemies;
    public int timeIntervalBullet=0;
    public short numberBullet;
    public Enemy() { // constructer alt+fn+insret
        numberBullet = 12;
        position = new Vector2D();
        velocity = new Vector2D();
        this.bulletEnemies=new ArrayList<>();
        for (int i = 0; i < numberBullet; i++) {
            List<BulletEnemy> temp;
            temp = new ArrayList<>();
            bulletEnemies.add(temp);
        }
    }
    public void run(){
        this.position.addUp(this.velocity);
        if(this.position.y<0||this.position.y>600){
            this.velocity.y=-this.velocity.y;
        }
        if(this.position.x<0||this.position.x>1024){
            this.velocity.x=-this.velocity.x;
        }
        this.shoot();
    }
    public void shoot(){
        if(this.timeIntervalBullet==100){
            Vector2D tempVelocity=new Vector2D();
            tempVelocity.set(2,0);
            this.bulletEnemies.forEach(bulletEnemies1 -> {
                BulletEnemy bulletEnemy = new BulletEnemy();
                try{
                    bulletEnemy.image=ImageIO.read((new File( "resources-rocket-master/resources/images/circle.png")));
                }catch (IOException e){
                    e.printStackTrace();
                }
                bulletEnemy.position.set(this.position);
                bulletEnemy.velocity.set(tempVelocity);
                tempVelocity.set(tempVelocity.rotate(360/(numberBullet)));
                bulletEnemies1.add(bulletEnemy);
            });
            this.timeIntervalBullet=0;
        }else
            this.timeIntervalBullet++;
        this.bulletEnemies.forEach(bulletEnemies1 -> bulletEnemies1.forEach(bulletEnemy -> bulletEnemy.run()));
    }
    public void render(Graphics graphics){
        if(this.image !=null)
            graphics.drawImage(this.image,(int)this.position.x,(int)this.position.y,this.width,this.height,null);
        this.bulletEnemies.forEach(bulletEnemies1 -> bulletEnemies1.forEach(bulletEnemy -> bulletEnemy.render(graphics)));
    }


}
