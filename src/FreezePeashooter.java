import jaco.mp3.player.MP3Player;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;


public class FreezePeashooter extends Plant {

    public Timer shootTimer;
    MP3Player mp3Player;


    public FreezePeashooter(GamePanel parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(4000,(ActionEvent e) -> {
            //System.out.println("SHOOT");
            if(gp.laneZombies.get(y).size() > 0) {
//                Set position of FreezePeashooter to create a new FreezePea
                gp.lanePeas.get(y).add(new FreezePea(gp, y, 103 + this.x * 100));
                String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/shoop.mp3";
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