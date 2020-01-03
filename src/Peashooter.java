import jaco.mp3.player.MP3Player;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
public class Peashooter extends Plant {

    public Timer shootTimer;
    MP3Player mp3Player;


    public Peashooter(GamePanel parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            //System.out.println("SHOOT");
            if(gp.laneZombies.get(y).size() > 0) {
                gp.lanePeas.get(y).add(new Pea(gp, y, 103 + this.x * 100));
                String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/plant.mp3";
                mp3Player = new MP3Player(new File(SONG));
                mp3Player.play();
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}
