package main;

import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.TreeMap;

public class GamePanel extends JPanel implements Runnable{

    //sets screen sets
    final int orts = 16;



    //org size was 3 so if something is wrong change back :D
    final int scale = 4;

    public  int tileSize = orts * scale; // 48
   public  int maxScreenCol = 16;
     public  int maxScreenRow = 12;
   public  int screenWdith = tileSize * maxScreenCol;
  public  int screenHeight = tileSize * maxScreenRow;


  //chrater choicec stuff



  //wrld settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final  int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight  = tileSize * maxWorldRow;


    //fps
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    //instances
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sound = new Sound();
   public CollisionChecker cChecker = new CollisionChecker(this);
   public AssetSetter aSetter = new AssetSetter(this);
   public UI ui = new UI(this);
    Thread gameThread;

   public Player player = new Player(this, keyH);
   public SuperObject obj[] = new SuperObject[10];
    public Entity npc[]= new Entity[10];


    //game state stuff
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int titleState = 0;




    //sets players defult pos



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWdith, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void gameSetUp(){
        aSetter.setObject();
        aSetter.setNPC();
      // playMusic(5);
        gameState = titleState;



    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {

     double drawInterval =    1000000000/FPS;
     double delta = 0;
     long lastTime = System.nanoTime();
     long currentTime;
     long timer = 0;
     int drawCount = 0;

     while (gameThread != null){


         currentTime = System.nanoTime();
         delta +=(currentTime - lastTime) / drawInterval;
         timer += (currentTime - lastTime);
         lastTime = currentTime;

         if(delta >= 1){
             //update
             update();

             //repaints
             repaint();
             delta--;
             drawCount++;
         }
         if(timer >= 1000000000){
             System.out.println("FPS: "+ drawCount);
             drawCount = 0;
             timer = 0;
         }




//        System.out.println("Game is running");
    }


    }
    public void update(){

        if(gameState == playState){
            //player
            player.update();
//            music.loop();
            //npc
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
                }
            }

        }
        if (gameState == pauseState){
            //null
            stopMusic();
        }

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //debug
        long drawStart = 0;
        if (keyH.DrawTimeMenu == true) {
            drawStart = System.nanoTime();
        }

        //title screen
        if (gameState == titleState){
            ui.draw(g2);
        }
        else{
            tileM.draw(g2);

            //objects
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }

            }
            //npc
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].draw(g2);
                }
            }
            //player
            player.draw(g2);

            //ui
            ui.draw(g2);
        }
        // tile


        //debug pt 2

        if (keyH.DrawTimeMenu == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 80);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }

    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){

        sound.setFile(i);
        sound.play();
    }
}



















