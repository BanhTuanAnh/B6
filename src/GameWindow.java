import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class GameWindow extends JFrame {// de hien thi ra man hinh window
    private  GameCanvas gameCanvas;
    private long lastTime = 0;
    public GameWindow(){
        this.setSize(1024,600);// dai - rong set kich thuoc man hinh
        this.setUpGameCanvas();
        this.event();
        this.setVisible(true);// cho phep cua so window hien len. dat o cuoi
    }
    private void setUpGameCanvas(){
        this.gameCanvas= new GameCanvas();
        this.add(this.gameCanvas); // dua game vanvas vao window
    }
    private void event(){
        this.keyBoardEvent();
        this.windowEvent();
    }
    private  void windowEvent(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
    private  void keyBoardEvent(){
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT){
                    gameCanvas.player.angle += 5;

                }
                if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT){
                    gameCanvas.player.angle -= 5;

                }
                if(keyEvent.getKeyCode()==KeyEvent.VK_DOWN){
                    //    gameCanvas.player.y[0]+=gameCanvas.player.velocityY;
                }

                if(keyEvent.getKeyCode()==KeyEvent.VK_UP){
                    gameCanvas.player.velocity.multiply(5);
                }
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
    }
    public void gameLoop(){
        while(true){
            long currentTime = System.nanoTime();
            if(currentTime-this.lastTime>=17000000){
                this.gameCanvas.createAll();
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime=currentTime;
            }
        }
    }

}
