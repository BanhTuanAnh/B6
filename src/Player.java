//if(x[0]<0){
//            y[0]=random.nextInt(500);
//            x[0]= 950;
//        }
//        if(x[0]>950){
//            y[0]=random.nextInt(500);
//            x[0]=0;
//        }
//        if(y[0]>500){
//            y[0]= 0;
//            x[0]= random.nextInt(950);
//        }
//        if(y[0]<0){
//            x[0]=random.nextInt(950);
//            y[0]= 500;
//        }

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    // public BufferedImage image; // mac dinh ko khai bao truy xuat thi la publi
    public Vector2D position ;
    public Vector2D velocity;
    private Polygon polygon;
    private List<Vector2D> vectices;
    public int angle;
    public int timeIntervalBullet;
    public List<BulletPlayer> bulletPlayers;
    private Random random=new Random();
    public Player() { // constructer alt+fn+insret
        position=new Vector2D();
        velocity=new Vector2D();
        this.vectices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0,16),
                new Vector2D(20,8)
        );

        this.polygon=new Polygon();
        this.bulletPlayers =new ArrayList<>();
    }
    public void run(){
        this.position.addUp(this.velocity);
        this.shoot();
        if(this.position.x<0){
            this.position.y=random.nextInt(600);
            this.position.x=1024;
        }
        if(this.position.x>1024){
            position.y=random.nextInt(500);
            position.x= 0;
        }
        if(position.y>600){
            this.position.y= 0;
            this.position.x= random.nextInt(1024);
        }
        if(this.position.y<0){
            this.position.x=random.nextInt(1024);
            this.position.y= 600;
        }

        this.velocity.set(
                new Vector2D(3.5f,0).rotate(this.angle)
        );
        // xu ly player run
    }
    public void updateTriangle(){
        this.polygon.reset();
        Vector2D center = this.vectices
                .stream()
                .reduce(new Vector2D(),((vector2D, vector2D2) ->vector2D.add(vector2D2) ))
                .multiply(1.0f/(float) this.vectices.size())
                .rotate(angle);// tinh vetoe GG'
        Vector2D translate = this.position.subtract(center);
//        List<Vector2D> list = new ArrayList<>();
//        this.vectices.forEach(vector2D ->list.add(vector2D.add(translate)));
        this.vectices
                .stream()
                .map(vector2D -> vector2D.rotate(angle))
                .map(vector2D -> vector2D.add(translate))
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x,(int) vector2D.y));//keo 3 dinh ra vi tri can tinh

    }
    public void render(Graphics graphics){
        graphics.setColor(Color.blue);
        this.updateTriangle();
        graphics.fillPolygon(this.polygon);
        this.bulletPlayers.forEach(bulletPlayer -> bulletPlayer.render(graphics));
    }
    public void shoot(){
        if(this.timeIntervalBullet==50){
            BulletPlayer bulletPlayer = new BulletPlayer();
            try{
                bulletPlayer.image=ImageIO.read((new File( "resources-rocket-master/resources/images/circle.png")));
            }catch (IOException e){
                e.printStackTrace();
            }
            bulletPlayer.position.set(this.position);
            bulletPlayer.velocity.set(new Vector2D(30,0).rotate(angle));
            this.bulletPlayers.add(bulletPlayer);
            this.timeIntervalBullet=0;
        }else
            this.timeIntervalBullet++;
        this.bulletPlayers.forEach(bulletPlayer -> bulletPlayer.run());
    }

}









