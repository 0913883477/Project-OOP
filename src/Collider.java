import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class Collider extends JPanel implements MouseListener {

//    PR
    ActionListener al;

    public Collider(){
        setOpaque(false);
        addMouseListener(this);
        setSize(100,120);
    }
//  Variable add Plant
    public Plant assignedPlant;
    MP3Player mp3Player;


    public void setPlant(Plant p){
        assignedPlant = p;
    }

//  Collider to remove Plant
    public void removePlant(){
        String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/yuck2.mp3";
        mp3Player = new MP3Player(new File(SONG));
        mp3Player.play();
        assignedPlant.stop();
        assignedPlant = null;
    }

//  W
    public boolean isInsideCollider(int tx){
        return (tx > getLocation().x) && (tx < getLocation().x + 100);
    }

    public void setAction(ActionListener al){
        this.al = al;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(al != null){
//            ActionEvent.RESERVED_ID_MAX ID to store Action Performed
            al.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
