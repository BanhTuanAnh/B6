import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {// van la class tach roi chua dc dua vafo window
    //cach dat ten bien
    //snake case user_name
    //camel case: userName
    private Graphics graphics;
    private BufferedImage backBuffered;
    private List<Star> stars;
    private List<Enemy> enemies;
    private List<EnemyLevel1> enemyLevel1s;
    public Player player;
    public Background background;
    public int timeIntervalStar=0;
    public int timeIntervalEnemy=0;
    public int timeIntervalEnemyLevel1=0;
    private Random random=new Random();
    public GameCanvas(){
        this.setSize(1024,600);
        this.setUpBackBuffered();
        this.setUpBackground();
        this.setCharacter();
    }
    @Override
    protected void paintComponent(Graphics g) {// noi ve tat ca moi thu
        // ve
        g.drawImage(this.backBuffered,0,0,null);
    }
    private void playerRun(){
        this.player.run();
    }
    private void enemyLevel1Run(){this.enemyLevel1s.forEach(enemyLevel1 -> enemyLevel1.run(this.player.position));}
    private void enemyRun(){
        this.enemies.forEach(enemy -> enemy.run());
    }
    private void starRun(){
        this.stars.forEach(star -> star.run());
    }
    public void runAll(){
        this.enemyRun();
        this.enemyLevel1Run();
        this.starRun();
        this.playerRun();
    }
    public void createAll(){
        this.createStar();
        this.createEnemy();
        this.createEnemyLevel1();
    }
    public void renderAll(){
        //1 ve nen
        this.background.render(graphics);
        this.stars.forEach(star ->star.render(graphics));
        this.enemyLevel1s.forEach(enemyLevel1 -> enemyLevel1.render(graphics));
        this.enemies.forEach(enemy -> enemy.render(graphics));
        this.player.render(graphics);
        this.repaint();// goi luon ham ve laij man hinh trong renderAll
    }
    private BufferedImage loadImage(String path){
        try{
            return ImageIO.read((new File(path)));
        }catch (IOException e){
            return  null;
        }
    }
    private void createEnemyLevel1(){
        if(this.timeIntervalEnemyLevel1==100){
            EnemyLevel1 enemyLevel1=new EnemyLevel1();
            enemyLevel1.position.x=40;
            enemyLevel1.position.y=this.random.nextInt(590);
            enemyLevel1.image=this.loadImage("resources-rocket-master/resources/images/star.png");
            enemyLevel1.velocity.x=random.nextInt(10);
            enemyLevel1.velocity.y=enemyLevel1.velocity.x;
            enemyLevel1.width=random.nextInt(20)+10;
            enemyLevel1.height=enemyLevel1.width;
            this.enemyLevel1s.add(enemyLevel1);
            this.timeIntervalEnemyLevel1=0;
        }
        else{
            this.timeIntervalEnemyLevel1++;
        }
    }
    private void createEnemy(){
        if(this.timeIntervalEnemy==100){
            Enemy enemy=new Enemy();
            enemy.position.x=1020;
            enemy.position.y=this.random.nextInt(590);
            enemy.image=this.loadImage("resources-rocket-master/resources/images/circle.png");
            enemy.velocity.x=random.nextInt(10);
            enemy.velocity.y=enemy.velocity.x;
            enemy.width=random.nextInt(20)+10;
            enemy.height=enemy.width;
            this.enemies.add(enemy);
            this.timeIntervalEnemy=0;
        }
        else{
            this.timeIntervalEnemy++;
        }
    }
    private  void createStar(){
        if(this.timeIntervalStar==20){
            Star star=new Star();
            star.position.set(1024,this.random.nextInt(600));
            star.height=5;
            star.width=5;
            star.image=this.loadImage("resources-rocket-master/resources/images/star.png");
            star.velocity.set(random.nextInt(10)+1,0);
            this.stars.add(star);
            this.timeIntervalStar=0;
        }else{
            this.timeIntervalStar++;
        }
    }
    private void setUpBackground(){ this.background=new Background(); }
    private void setUpEnemies(){
        this.enemies=new ArrayList<>();
    }
    private void setUpEnemyLevel1(){this.enemyLevel1s=new ArrayList<>();}
    private void setUpStar(){
        this.stars=new ArrayList<>();
    }
    private void setUpBackBuffered(){
        this.backBuffered=new BufferedImage(1024,600,BufferedImage.TYPE_INT_ARGB);
        this.graphics=this.backBuffered.getGraphics();// co ve sex ve len backbuffer
    }
    private  void setUpPlayer(){
        this.player=new Player();
        this.player.position.set(100,200);
        this.player.velocity.set(3.5f,0);
    }
    private void setCharacter(){
        this.setUpStar();
        this.setUpEnemies();
        this.setUpEnemyLevel1();
        this.setUpPlayer();
    }
}
