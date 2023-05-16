package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.PublicKey;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //debug
    boolean DrawTimeMenu = false;





    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //we don't need this yet btw
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //title state
        if (gp.gameState == gp.titleState){

            titleState(code);
        }
        //keystrokes for play state
       else if(gp.gameState == gp.playState){

           playState(code);
        }
        //pause state strokes
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //dialogues state strokes
        else if(gp.gameState == gp.dialogState){
                dialougeState(code);
            //character state
        } else if (gp.gameState == gp.characterState) {
            chracterState(code);

        }

    }
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebdpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void titleState(int code){
        if(gp.ui.titleScreenState == 0){
            if (code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum <0 ){
                    gp.ui.commandNum = 5;
                }
            }
            if (code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum >5 ){
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;

                }
                if (gp.ui.commandNum == 1) {
                    //for later
                    gp.ui.titleScreenState = 2;
                }
                if (gp.ui.commandNum == 2) {
                    //exit
                    System.exit(69);


                }
                if (gp.ui.commandNum == 3) {
                    //opens link
                    try {
                        URL utube = new URL("https://www.youtube.com/channel/UCnQ8RexasTgyh23H4xReakw");
                        openWebdpage(utube);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                if (gp.ui.commandNum == 4) {
                    //opens link
                    try {
                        URL twitter = new URL("https://twitter.com/@Alfredredbird1");
                        openWebdpage(twitter);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                if (gp.ui.commandNum == 5) {
                    gp.ui.titleScreenState = 3;




                }
            }
        }
        //chose birb screen
        else if(gp.ui.titleScreenState == 1){
            if (code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum <0 ){
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum >3 ){
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){

                    //leave till choose birb screen
                    gp.gameState = gp.playState;
                    gp.playMusic(0);

                    System.out.println("Normal has been chosen");

                }
                if(gp.ui.commandNum == 1){
                    //for later
                    gp.gameState = gp.playState;
                    gp.playMusic(0);


                    System.out.println("Easy Has Been Chosen");
                    gp.player.life = 5;

                }
                if(gp.ui.commandNum == 2) {
                    //for later
                    gp.playMusic(0);

                    System.out.println("Hardcore has been chosen");
                    gp.player.life = 1;
                    gp.gameState = gp.playState;

                }
                if(gp.ui.commandNum == 3) {
                    //exit

                    gp.ui.titleScreenState = 0;
                    gp.ui.commandNum = 2;
                }
            }

            //stops from using multiplayer
        }else if(gp.ui.titleScreenState == 2){
            if (code == KeyEvent.VK_UP){

                if(gp.ui.commandNum <0 ){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_DOWN){

                if(gp.ui.commandNum >0 ){
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){


                    gp.ui.titleScreenState = 0;

                    System.out.println("Birb has been chosen");
                }


            }
        }  else if(gp.ui.titleScreenState == 3) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {



                    System.out.println("Credits ");
                    gp.ui.titleScreenState = 0;


                }

            }
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W){
            upPressed = true;

        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState =gp.pauseState;

        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;

        }
        if (code == KeyEvent.VK_E){
            gp.gameState = gp.characterState;

        }


        //debug menu
        if (code == KeyEvent.VK_F3){
            if (DrawTimeMenu == false){
                DrawTimeMenu = true;
            }
            else if(DrawTimeMenu == true){
                DrawTimeMenu = false;
            }
        }
    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState =gp.playState;

        }

    }
    public void dialougeState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    public void chracterState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;

        }
        if(code == KeyEvent.VK_E){
            gp.gameState = gp.playState;

        }
        if(code == KeyEvent.VK_UP){

            if (gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.playSoundEffect(8);
            }

        }
        if(code == KeyEvent.VK_DOWN){
            if (gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.playSoundEffect(8);
            }

        }
        if(code == KeyEvent.VK_LEFT){
            if (gp.ui.slotColom != 0){
                gp.ui.slotColom--;
                gp.playSoundEffect(8);
            }

        }
        if(code == KeyEvent.VK_RIGHT){
            if (gp.ui.slotColom != 4) {
                gp.ui.slotColom++;
                gp.playSoundEffect(8);
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
