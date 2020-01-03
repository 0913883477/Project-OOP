import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JLayeredPane implements MouseMotionListener {

    Image bgImage;
    Image peashooterImage;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;

    Image normalZombieImage;
    Image coneHeadZombieImage;
    Collider[] colliders;
    
    ArrayList<ArrayList<Zombie>> laneZombies;
    ArrayList<ArrayList<Pea>> lanePeas;
    ArrayList<Sun> activeSuns;

    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunProducer;
    Timer zombieProducer;
    JLabel sunScoreboard;
//  Dung Enum
    GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;

    int mouseX , mouseY;

//      Initial Sun
    private int sunScore;

//    Music
//    MP3Player mp3Player = new MP3Player(new File(SONG));
    MP3Player mp3Player ;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
//        sunScoreboard JLabel set Text, type of Stirng
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public GamePanel(JLabel sunScoreboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;
//      Initial Sun
        setSunScore(10000);

        bgImage  = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peashooterImage = new ImageIcon(this.getClass().getResource("images/plants/peashooter.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("images/plants/freezepeashooter.gif")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();

        normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie1.gif")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie2.gif")).getImage();

//      Arraylist in arraylist
        laneZombies = new ArrayList<>();
        laneZombies.add(new ArrayList<>()); //line 1
        laneZombies.add(new ArrayList<>()); //line 2
        laneZombies.add(new ArrayList<>()); //line 3
        laneZombies.add(new ArrayList<>()); //line 4
        laneZombies.add(new ArrayList<>()); //line 5

        lanePeas = new ArrayList<>();
        lanePeas.add(new ArrayList<>()); //line 1
        lanePeas.add(new ArrayList<>()); //line 2
        lanePeas.add(new ArrayList<>()); //line 3
        lanePeas.add(new ArrayList<>()); //line 4
        lanePeas.add(new ArrayList<>()); //line 5

//      X
        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
//            System.out.println("i: "+i+" - x:"+ ((i%9)*100 +44) +" - y:"+(109 + (i/9)*120));
            a.setLocation(44 + (i%9)*100,109 + (i/9)*120);
//            System.out.println((i%9)+" - "+(i/9));
//            x= i%9, y=i/9
            a.setAction(new PlantActionListener((i%9),(i/9)));
//            Save postiion, It will be happen to collider between zombie and plant
            colliders[i] = a;
            add(a,new Integer(0));
        }

        activeSuns = new ArrayList<>();

//      Repaint again
        redrawTimer = new Timer(25,(ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

//        Run advance method is local
//        Run all advance of Plant and Zombie
        advancerTimer = new Timer(60,(ActionEvent e) -> advance());
        advancerTimer.start();

//        Sun Fall in the sky
        sunProducer = new Timer(7000,(ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this,rnd.nextInt(800)+100,-100,rnd.nextInt(300)+200);
            activeSuns.add(sta);
//            Add into JFrame 1 index
            add(sta,new Integer(1));
        });
        sunProducer.start();

//        Produce Zombie
        String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/readysetplant.mp3";
        mp3Player = new MP3Player(new File(SONG));
        mp3Player.play();
        zombieProducer = new Timer(5000,(ActionEvent e) -> {
            Random rnd = new Random();
//            Save File Level.vbhv
            LevelData lvl = new LevelData();
//            Interger.parseInt from String
//            System.out.println(Integer.parseInt(lvl.Lvl));
//            Integer.parseInt(lvl.Lvl) = 1
//            System.out.println(lvl.Level[Integer.parseInt(lvl.Lvl)-1]);
//            Save [Ljava.lang.String;@756983dc

//          Get Zombie Type
//          Level up , game will have coneheadzombie
            String [] Level = lvl.Level[Integer.parseInt(lvl.Lvl)-1];
            int [][] LevelValue = lvl.LevelValue[Integer.parseInt(lvl.Lvl)-1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
//            LevelValue =1
            for(int i = 0;i<LevelValue.length;i++) {

                if(t>=LevelValue[i][0]&&t<=LevelValue[i][1]) { // Bo cung duoc
//                    l= is ylane
//                    System.out.println(LevelValue[i][0]); =0
//                    System.out.println("T "+t); = Random
//                    System.out.println(LevelValue[i][1]); =99
                    z = Zombie.getZombie(Level[i],GamePanel.this,l);
                }
            }
            laneZombies.get(l).add(z);
        });
        zombieProducer.start();


    }

//    Advance access in Advance timer
    private void advance(){
        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombies.get(i)){
//                Access advance of Class Zombie
//                Check moving
//                Out map
//                Collider with Plant
                z.advance();
            }
            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
//              Shoot pea to zombie
                p.advance();
            }
        }

//      Fall sun
//        Destroy sun
        for (int i = 0; i < activeSuns.size() ; i++) {
            activeSuns.get(i).advance();
        }
    }

//  LOOP
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,null);

        g.setColor(Color.WHITE); //mau chu
        g.setFont(new Font("Arial", Font.BOLD, 20)); //font, type, size
        g.drawString("Hieu Chau", 875, 22); //ve chu

//      Check Collider with plant
        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
//       c.assignedPlant is variable of Collider, Properties of Plant
            if(c.assignedPlant != null){
                Plant p = c.assignedPlant;
                if(p instanceof Peashooter){
//                    x is plant, y is plant
                    g.drawImage(peashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof FreezePeashooter){
                    g.drawImage(freezePeashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof Sunflower){
                    g.drawImage(sunflowerImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
            }
        }

//        Draw Zombie
        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombies.get(i)){
                if(z instanceof NormalZombie){
//                    Note y: 109+(i*120)
//                    z.posX is movement of Zombie
                    g.drawImage(normalZombieImage,z.posX,109+(i*120),null);
                }else if(z instanceof ConeHeadZombie){
                    g.drawImage(coneHeadZombieImage,z.posX,109+(i*120),null);
                }
            }

//            Draw Plant
            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
                if(p instanceof FreezePea){
                    g.drawImage(freezePeaImage, p.posX, 130 + (i * 120), null);
                }else {
                    g.drawImage(peaImage, p.posX, 130 + (i * 120), null);
                }
            }

        }

    }

    class PlantActionListener implements ActionListener {

        int x,y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;
//            Click buy Plant and calculate score, then insert Plant into Map
//            GameWindow.PlantType.Sunflower check enum
            if(activePlantingBrush == GameWindow.PlantType.Sunflower){
                if(getSunScore()>=50) {
//                    x,y is Map 0-0 0-1
                    colliders[x + y * 9].setPlant(new Sunflower(GamePanel.this, x, y));
                    setSunScore(getSunScore()-50);
                }
            }
            if(activePlantingBrush == GameWindow.PlantType.Peashooter){
                if(getSunScore() >= 100) {
                    colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
                    setSunScore(getSunScore()-100);
                }
            }

            if(activePlantingBrush == GameWindow.PlantType.FreezePeashooter){
                if(getSunScore() >= 175) {
                    colliders[x + y * 9].setPlant(new FreezePeashooter(GamePanel.this, x, y));
                    setSunScore(getSunScore()-175);
                }
            }
            activePlantingBrush = GameWindow.PlantType.None;
            String SONG ="/Users/tgdd/Desktop/Project OOP/PlantsVsZombies Note/src/Music/points.mp3";
            mp3Player = new MP3Player(new File(SONG));
            mp3Player.play();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    static int progress = 0;
    public static void setProgress(int num) {
//        Score of Player when zombie is die
        progress = progress + num;
        System.out.println(progress);
        if(progress>=150) {
           if("1".equals(LevelData.Lvl)) {
            JOptionPane.showMessageDialog(null,"Level Completed !!!" + '\n' + "Starting next Level");
            GameWindow.gw.dispose();
            LevelData.write("2");
            GameWindow.gw = new GameWindow();

            }  else {
               JOptionPane.showMessageDialog(null,"Level Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
               LevelData.write("1");
               System.exit(0);
           }
           progress = 0;
        }
    }
}
