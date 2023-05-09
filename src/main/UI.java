package main;

import objects.Object_key;

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

    public int titleScreenState = 0; //0 = screen 1 etc
    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
//        Object_key key = new Object_key(gp);
//        keyImage = key.image;
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
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScrn();
        }
        if(gp.gameState == gp.dialogState){
            drawDialogScreen();
        }
    }
    public void drawTitleScreen(){

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

            x = gp.screenWdith/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.left1, x,y, gp.tileSize*2, gp.tileSize*2, null);

            //menus
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "New Game";
            x = getXforCenter(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Load Game";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Multiplayer";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Quit";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){


            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            String text = "Select your Class";
            int x = getXforCenter(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x ,y);

            text = "Birb";
             x = getXforCenter(text);
             y += gp.tileSize*3;
            g2.drawString(text,x ,y);
            if(commandNum == 0){

                g2.drawString(">", x- gp.tileSize, y);
            }

            text = "Big Birb";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text,x ,y);
            if(commandNum == 1){
                g2.drawString(">", x- gp.tileSize, y);
            }

            text = "lil birb";
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
        g2.drawString(currentDialog,x ,y);

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
