package main;

import entity.Entity;
import objects.*;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
//    BufferedImage keyImage;
    public boolean messageOn = false;

    public int slotColom = 0;
    public int slotRow = 0;
//    public String message = "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public String currentDialog = "";
    public int commandNum = 0;
    BufferedImage heart_0,heart_1, heart_2;
    BufferedImage playB, quitB, optionsB, tb,ytB, twB, creditsB;

    BufferedImage cloud1;

    BufferedImage arrow1, arrow2;

    BufferedImage boarder1, boarder2, boarder3, boarder4,boarder5;

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
        twB = UIG.image6;
        creditsB = UIG.image7;


        Entity cloud = new cloud(gp);
        cloud1 = cloud.image;

        Entity boarder = new boarders(gp);
        boarder1 = boarder.image;
        boarder2 = boarder.image2;
        boarder3 = boarder.image3;
        boarder4 = boarder.image4;
        boarder5 = boarder.image5;

        Entity arrow = new arrows(gp);
        arrow1 = arrow.image2;
        arrow2 = arrow.image;
    }

    public void showMessage(String text){

//        message = text;
//        messageOn = true;

        message.add(text);
        messageCounter.add(0);
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
            drawMessage();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerHearts();
            drawPauseScrn();
        }
        if(gp.gameState == gp.dialogState){
            drawPlayerHearts();
            drawDialogScreen();
        }
        if(gp.gameState == gp.characterState){
            drawStatScreen();
            drawInvenory();

        }
    }

    public void drawInvenory(){

        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //slots

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;

        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        //drwas player items
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //crsor
        int cursorX = slotXstart + (slotSize * slotColom);
        int cursorY = slotYstart + (slotSize *slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //drwas the cursor
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight, 10,10);

        //descriptio winodw
        int descriptionFrameX = frameX;
        int descriptionFrameY = frameY + frameHeight + 130;
        int descriptionFrameWidth = frameWidth;
        int descriptionFrameHeight = gp.tileSize*3;
        drawSubWindow(descriptionFrameX,descriptionFrameY,descriptionFrameWidth,descriptionFrameHeight);
        //draws description
        int textX = descriptionFrameX + 20;
        int textY = descriptionFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndex();

        if(itemIndex < gp.player.inventory.size()){

            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX,textY);
                textY += 32;
            }
        }



    }
    public int getItemIndex(){
        int itemIndex = slotColom + (slotRow*5);
        return itemIndex;
    }
    public void drawMessage(){

        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        for (int i = 0; i < message.size(); i++) {

            if(message.get(i) != null){

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) +1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }
    public void drawStatScreen(){

        //creats frame
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWdith = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWdith,frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        g2.drawString("Level", textX,textY);
        textY += lineHeight;
        g2.drawString("Life", textX,textY);
        textY += lineHeight;
        g2.drawString("Strength", textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX,textY);
        textY += lineHeight;
        g2.drawString("Defence", textX,textY);
        textY += lineHeight;
        g2.drawString("Attack", textX,textY);
        textY += lineHeight;
        g2.drawString("XP", textX,textY);
        textY += lineHeight;
        g2.drawString("XP Next Level", textX,textY);
        textY += lineHeight;
        g2.drawString("Gold", textX,textY);
        textY += lineHeight+ 20;
        g2.drawString("Shield", textX,textY);
        textY += lineHeight+ 40;
        g2.drawString("Weapon", textX,textY);
        textY += lineHeight;


        //values
        int endX = (frameX + frameWdith)- 35;
        //resets textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxlife);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defence);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attackval);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.xp);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelxp);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.gold);
        textX = getXforAllignToRight(value, endX);
        g2.drawString(value, textX, textY);
        textY += lineHeight + 50;

//        value = String.valueOf(gp.player.currentWeapon);
//        textX = getXforAllignToRight(value, endX);
//        g2.drawString(value, textX, textY);
//        textY += lineHeight+ 80;
//
//        value = String.valueOf(gp.player.currentItemInOffhand);
//        textX = getXforAllignToRight(value, endX);
//        g2.drawString(value, textX, textY);
//        textY += lineHeight ;

        g2.drawImage(gp.player.currentItemInOffhand.down1, endX-gp.tileSize, textY-60,null);

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
            int pp = 0;
            text = "Play";
            x += gp.tileSize*1.2;
            y += gp.tileSize*1.9;
//            g2.drawString(text, x, y);
            g2.drawImage(playB, x,y, 160, 160, null);

            if(commandNum == 0){
//                g2.drawString(">", x-gp.tileSize, 400);
//                g2.drawImage(boarder1, x,y, 160, 160, null);
                g2.drawImage(arrow1, x+170,y+ 50, 50, 50, null);

            }

            text = "Options";
            x = getXforCenter(text) - 35;
            y += gp.tileSize*1.009;
//            g2.drawString(text, x, y);
            g2.drawImage(optionsB, x,436, 229, 87, null);
            if(commandNum == 1){
//                g2.drawString(">", x-gp.tileSize, 500);
//                g2.drawImage(boarder4, x,436, 229, 87, null);
                g2.drawImage(arrow1, x+250,y+ 70, 50, 50, null);
            }

            text = "Quit";
//            x = getXforCenter(text);
            x = getXforCenter(text) - 43;
            y += gp.tileSize*1.71;
//            g2.drawString(text, x, y);
            g2.drawImage(quitB, x,y, 160, 160, null);
            if(commandNum == 2){
//                g2.drawString(">", x-gp.tileSize, 600);
//                g2.drawImage(boarder1, x,y, 160, 160, null);
                g2.drawImage(arrow1, x+170,y+ 50, 50, 50, null);
            }

            text = "Youtube";
            x = getXforCenter(text) - 350;
            y += gp.tileSize*1.2;
//            g2.drawString(text, x, y);
            g2.drawImage(ytB, x,641, null);
            if(commandNum == 3){
//                g2.drawString(">", 25, 690);
                g2.drawImage(arrow2, x-16,y-20, 100, 100, null);

            }


            text = "Twitter";
            x = getXforCenter(text) - 270;
            y += gp.tileSize*1.2;
//            g2.drawString(text, x, 641);
            g2.drawImage(twB, x,641, null);
            if(commandNum == 4){
//                g2.drawString(">", 125, 690);

                g2.drawImage(arrow2, x-15,y-103, 100, 100, null);
            }

            text = "Credits";
            x = getXforCenter(text) + 350;
            y += gp.tileSize*1.2;
//            g2.drawString(text, x, 641);
            g2.drawImage(creditsB, x,641, 200,85, null);
            if(commandNum == 5){
//                g2.drawImage(boarder3, x,641, 200,85, null);
                g2.drawImage(arrow2, x+54,y-183, 100, 100, null);


            }

            g2.drawImage(cloud1, x,175,  null);
            g2.drawImage(cloud1, x-600,150,  null);

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
        else if(titleScreenState == 3){


            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));
            String  text = "Credits go here";
            int  x = getXforCenter(text);
            int y = gp.tileSize*4;
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
    public int getXforAllignToRight(String text, int endX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = endX - length;
        return x;
    }



}
