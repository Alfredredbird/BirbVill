package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
   public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

   public Player player = new Player(this, keyH);
   public Entity obj[] = new Entity[10];
    public Entity npc[]= new Entity[10];
    public Entity monster[] = new Entity[30];
    ArrayList<Entity> entityArrayList = new ArrayList<>();


    //game state stuff
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int titleState = 0;




    //sets players defult pos



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWdith, screenHeight));
        Color c = new Color(194, 194, 214);
        this.setBackground(c);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void gameSetUp(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
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
           // music.play();
            //npc
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    monster[i].update();
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

            //player
             player.draw(g2);

             //adds item/ player/ entitys
            entityArrayList.add(player);

            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    entityArrayList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null){
                    entityArrayList.add(obj[i]);
                }
            }


            for (int i = 0; i < monster.length; i++){
                if (monster[i] != null){
                    entityArrayList.add(monster[i]);
                }
            }

            //sorts
            Collections.sort(entityArrayList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);

                    return result;
                }
            });

            //draws entities
            for (int i = 0; i < entityArrayList.size(); i++) {
                entityArrayList.get(i).draw(g2);
            }
            //resets list
            for (int i = 0; i < entityArrayList.size(); i++) {
                entityArrayList.remove(i);
            }

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

            g2.drawString("X:" + player.worldX, 900, 140);
            g2.drawString("Y:" + player.worldY, 900, 70);

            g2.drawString("X Tile:" + player.worldX/48, 800, 180);
            g2.drawString("Y Tile:" + player.worldY/48, 800, 220);

            g2.setFont(new Font("Arial", Font.PLAIN, 26));
            g2.setColor(Color.white);
            g2.drawString("Invce: " + player.invcCounter, 10, 400);

            g2.drawString("Health:" + player.life, 850, 30);
           if(gameState == 3){
               g2.drawString("Talking To Npc: True" , 10, 140);
           }
            if(gameState == 1){
                g2.drawString("Talking To Npc: False", 10, 140);
            }

            //draws the amount of keys the player has
            if(player.hasKey > 0) {
                g2.drawString("Keys:" + player.hasKey, 900, 280);
            }

            g2.drawString("Direction:" + player.direction, 750, 260);
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



















