package main;

import entity.Entity;
import objects.hearts;
import objects.ui;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
//    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialog = "";
    public int commandNum = 0;
    BufferedImage heart_0,heart_1, heart_2;
    BufferedImage playB, quitB, optionsB, tb,ytB;
    public int titleScreenState = 0; //0 = screen 1 etc
    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
//        Object_key key = new Object_key(gp);
//        keyImage = key.image;

        //creats the hearts
        Entity heart = new hearts(gp);
        heart_0 = heart.image;
        heart_1 = heart.image2;
        heart_2 = heart.image3;

        //ui items
        Entity UIG = new ui(gp);
        playB = UIG.image;
        quitB = UIG.image2;
        optionsB = UIG.image3;
        tb = UIG.image4;
        ytB = UIG.image5;

    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

////
////        g2.setFont(arial_40);
////        g2.setColor(Color.WHITE);
////        g2.drawImage(keyImage,gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
////        g2.drawString("Key = "+ gp.player.hasKey, 50 ,50);
//
//        if(messageOn == true){
//
//            g2.setFont(g2.getFont().deriveFont(15F));
//            g2.drawString(message, gp.tileSize/2, gp.tileSize*3);
//
//            messageCounter++;
//            if(messageCounter > 120){
//                messageCounter = 0;
//                messageOn = false;
//            }
//        }

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            //starts play state
            drawPlayerHearts();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerHearts();
            drawPauseScrn();
        }
        if(gp.gameState == gp.dialogState){
            drawPlayerHearts();
            drawDialogScreen();
        }
    }
    public void drawPlayerHearts(){


        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        //draws max hearts
        while (i < gp.player.maxlife/2 ){
            g2.drawImage(heart_2, x,y, null);
            i++;
            x+= gp.tileSize;
        }

        //resets values
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;

         //draws the life.
        while (i < gp.player.life){
            g2.drawImage(heart_1,x ,y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_0, x,y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }
    public void drawTitleScreen(){

        //npc text box code
        // g2.drawImage(tb, 525, 185,300, 200,null);

        if(titleScreenState == 0){


            //title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "BirbVill";
            int x = getXforCenter(text);
            int y = gp.tileSize*3;

            g2.setColor(Color.GRAY);
            g2.drawString(text, x+5, y+5);
            g2.setColor(Color.white);
            g2.drawString(text, x ,y);

//            x = gp.screenWdith/2 - (gp.tileSize*2)/2;
//            y += gp.tileSize*1.3;
//            g2.drawImage(gp.player.left1, x,y, gp.tileSize*2, gp.tileSize*2, null);



            //menus
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "Play";
            x += gp.tileSize*1.2;
            y += gp.tileSize*1.9;
//            g2.drawString(text, x, y);
            g2.drawImage(playB, x,y, 160, 160, null);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, 400);
            }

            text = "Options";
            x = getXforCenter(text) - 35;
            y += gp.tileSize*1.009;
//            g2.drawString(text, x, y);
            g2.drawImage(optionsB, x,436, 229, 87, null);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, 500);
            }

            text = "Quit";
//            x = getXforCenter(text);
            x = getXforCenter(text) - 43;
            y += gp.tileSize*1.71;
//            g2.drawString(text, x, y);
            g2.drawImage(quitB, x,y, 160, 160, null);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, 600);
            }

            text = "Youtube";
            x = getXforCenter(text) - 350;
            y += gp.tileSize*1.2;
//            g2.drawString(text, x, y);
            g2.drawImage(ytB, x,641, null);
            if(commandNum == 3){
                g2.drawString(">", 25, 690);
            }



        }
        else if(titleScreenState == 1){


            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            String text = "Select your Class";
            int x = getXforCenter(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x ,y);

            text = "Play";
             x = getXforCenter(text);
             y += gp.tileSize*3;
            g2.drawString(text,x ,y);
            if(commandNum == 0){

                g2.drawString(">", x- gp.tileSize, y);
            }

            text = "Save";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text,x ,y);
            if(commandNum == 1){
                g2.drawString(">", x- gp.tileSize, y);
            }

            text = "Multiplayer";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text,x ,y);
            if(commandNum == 2){
                g2.drawString(">", x- gp.tileSize, y);
            }
            text = "Back";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text,x ,y);
            if(commandNum == 3){
                g2.drawString(">", x- gp.tileSize, y);
            }
        }
        else if(titleScreenState == 2){

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));
            String  text = "Press Down Arrow To Go Back";
            int  x = getXforCenter(text);
            int y = gp.tileSize*4;
            g2.drawString(text,x ,y);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
             text = "Multiplayer Is Not Available Yet :(";
             x = getXforCenter(text);
             y = gp.tileSize*3;
            g2.drawString(text,x ,y);




            text = "Back";
            x = getXforCenter(text);
            y += gp.tileSize*3;
            g2.drawString(text,x ,y);
            if(commandNum == 0){

                g2.drawString(">", x- gp.tileSize, y);
            }


        }

    }
    public void drawDialogScreen(){


        //window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWdith - (gp.tileSize*5);
        int height = gp.tileSize*4;

        drawSubWindow(x,y,width, height);

        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialog.split("\n")){
            g2.drawString(line,x ,y);
            y+= 40;
        }



    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height, 35,35);
        c = new Color(255,255,255);


        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y +5, width-10, height-10, 25,25);

    }
    public void drawPauseScrn(){
        String text = "PAUSED";


        int x = getXforCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x,y);
    }
    public int getXforCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWdith/2 - length/2;
        return x;
    }


}
