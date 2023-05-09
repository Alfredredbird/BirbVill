package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;

public class Player extends Entity{


    KeyHandler keyH;



    public final int screenX;
    public final int screenY;

    public int hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);


        this.keyH = keyH;

        screenX = gp.screenWdith/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 32;
        solidArea.y = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 26;
        solidArea.width = 32;


        setDefaultVal();
        getPlayerImage();

    }
    public void setDefaultVal(){

//        worldX = gp.tileSize * 23;
//        worldY = gp.tileSize * 21;
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * 8;
        speed = 2;
        direction = "down";

        //player stats
        maxlife = 10;
        life = maxlife;

    }
    public void getPlayerImage(){



            up1 = setup("/player/walk_1");
            up2 = setup("/player/walk_2");
            down1 = setup("/player/down_1");
            down2 = setup("/player/down_2");
            right1 = setup("/player/right_1");
            right2 = setup("/player/right_2");
            left1 = setup("/player/left_1");
            left2 = setup("/player/left_2");



    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {


            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //checks for collision w tiles
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //checks for items
           int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //check npc collisionOn
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNpc(npcIndex);
            //if its false player can move
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;


                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;

                } else if (spriteNum == 2) {
                    spriteNum = 1;

                }
                spriteCounter = 0;
            }
        }
    }
    public void interactNpc(int i){
        if (i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogState;
                gp.npc[i].speak();
            }

        }
        gp.keyH.enterPressed = false;
    }

    public void pickUpObject(int i){
        if (i != 999){

            String objectName = gp.obj[i].name;
            switch (objectName ){
                case "Key":
                    gp.playSoundEffect(4);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked Up A Key");
                    break;
                case "Door":

                    if(hasKey > 0){

                        hasKey--;
                        System.out.println("Key--");
                        gp.obj[i] = null;
                        gp.ui.showMessage("You Opened A Door!");
                    } else {
                        gp.ui.showMessage("You Need A Key To Open This Door!");
                    }
                    break;
            }

        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }

                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }

                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
        }
        g2.drawImage(image,screenX ,screenY, 96, 96, null);
//                g2.setColor(Color.WHITE);
//
//        g2.fillRect(x,y,gp.tileSize, gp.tileSize);
    }


}
