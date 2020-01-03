import jaco.mp3.player.MP3Player;

import java.awt.*;
import java.io.File;


public class FreezePea extends Pea {

    public FreezePea(GamePanel parent, int lane, int startX){
        super(parent,lane,startX);
    }
    MP3Player mp3Player;
//  Shoot FreezePea and slow zombie
    @Override
    public void advance(){
// 0 = 130y, 1 = 250y
        Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
        for (int i = 0; i < gp.laneZombies.get(myLane).size(); i++) {
//            Save and Receive Zombie from Arraylist
            Zombie z = gp.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
//            pRect.intersects() boolean
            if(pRect.intersects(zRect)){
//                Damage FreezePea is 300
                z.health -= 300;
                z.slow();
                boolean exit = false;
                if(z.health < 0){
                    String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/zombaquarium_die.mp3";
                    mp3Player = new MP3Player(new File(SONG));
                    mp3Player.play();
                    System.out.println("ZOMBIE DIE");
                    GamePanel.setProgress(10);
                    gp.laneZombies.get(myLane).remove(i);
                    exit = true;
                }
//                System.out.println("Remove FreezePea");
                gp.lanePeas.get(myLane).remove(this);
                if(exit) break;
            }
        }
//        Pea move 15x
        posX += 15;
    }

}
