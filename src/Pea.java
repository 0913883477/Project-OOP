import jaco.mp3.player.MP3Player;

import java.awt.*;
import java.io.File;

public class Pea {

    public int posX;
    protected GamePanel gp;
    public int myLane;
    MP3Player mp3Player;
    public Pea(GamePanel parent,int lane,int startX){
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance(){
        Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
        for (int i = 0; i < gp.laneZombies.get(myLane).size(); i++) {
            Zombie z = gp.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 300;
                boolean exit = false;
                if(z.health < 0){
                    String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/zombaquarium_die.mp3";
                    mp3Player = new MP3Player(new File(SONG));
                    mp3Player.play();
                    System.out.println("ZOMBIE DIE");
                    
                    gp.laneZombies.get(myLane).remove(i);
                    GamePanel.setProgress(10);
                    exit = true;
                }
                gp.lanePeas.get(myLane).remove(this);
                if(exit) break;
            }
        }
        /*if(posX > 2000){
            gp.lanePeas.get(myLane).remove(this);
        }*/
        posX += 15;
    }

}
