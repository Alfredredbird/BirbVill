package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //debug
    boolean DrawTimeMenu = false;
    public int charaterCardnum = 0;
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

            if(gp.ui.titleScreenState == 0){
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
                        gp.ui.titleScreenState = 1;

                    }
                    if(gp.ui.commandNum == 1){
                        //for later

                    }
                    if(gp.ui.commandNum == 2){
                        //multiplayer
                    }if(gp.ui.commandNum == 3){
                        System.exit(69);

                    }
                }
            }
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

                        System.out.println("Birb has been chosen");
                    }
                    if(gp.ui.commandNum == 1){
                        //for later
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        charaterCardnum = 1;
                        System.out.println("Big Birb has been chosen");

                    }
                    if(gp.ui.commandNum == 2) {
                        //for later
                        gp.gameState = gp.playState;

                        System.out.println("Lil Birb has been chosen");
                    }
                    if(gp.ui.commandNum == 3) {
                        //for later
                       gp.ui.titleScreenState = 0;
                    }
                }
            }

        }
        //keystrokes for play state
        if(gp.gameState == gp.playState){
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
        //pause state strokes
        else if(gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_ESCAPE){
                gp.gameState =gp.playState;

            }
        }
        //dialogues state strokes
        else if(gp.gameState == gp.dialogState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
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
