import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Sun extends JPanel implements MouseListener {

     GamePanel gp;
     Image sunImage;

    public int myX;
    public int myY;
    public int endY;
    public int destruct = 200;
    MP3Player mp3Player;

    public Sun(GamePanel parent,int startX,int startY,int endY){
        this.gp = parent;
        this.endY = endY;
        setSize(80,80);
//        Opacity
        setOpaque(false);
        myX = startX;
        myY = startY;
        setLocation(myX,myY);
        sunImage = new ImageIcon(this.getClass().getResource("images/sun.png")).getImage();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage,0,0,null);
    }

    public void advance(){
//        Sun is falled
        if(myY < endY) {
            myY+= 4;
        }else{
//            Count down destroy
            destruct--;
            if(destruct<0){
                gp.remove(this);
//          gp.activeSun arraylist sun in gamepanel
                gp.activeSuns.remove(this);
            }
        }
        setLocation(myX,myY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        Get Sun point
        gp.setSunScore(gp.getSunScore()+50);
        gp.remove(this);
        gp.activeSuns.remove(this);
        String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/prize.mp3";
        mp3Player = new MP3Player(new File(SONG));
        mp3Player.play();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
