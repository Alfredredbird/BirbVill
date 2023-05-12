package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attack_up1, attack_up2, attack_down1, attack_down2,
            attack_left1, attack_left2, attack_right1, attack_right2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean invc = false;
    public int invcCounter = 0;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialouges[] = new String[20];
    int diagloeIndex = 0;
    public BufferedImage image, image2, image3, image4, image5,image6, image7;
    public String name;
    public boolean collision = false;

    //chracter health stats
    public int maxlife;
    public int life;
    public int type; //0 = player, etc



    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){

    }
    public void speak(){
        if (dialouges[diagloeIndex] == null){
            diagloeIndex = 0;
        }
        gp.ui.currentDialog = dialouges[diagloeIndex];
        diagloeIndex++;

        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update(){
     setAction();

     collisionOn = false;
     gp.cChecker.checkTile(this);
     gp.cChecker.checkObject(this, false);
     gp.cChecker.checkEntity(this,gp.npc);
     gp.cChecker.checkEntity(this, gp.monster);
    boolean contactPlayer = gp.cChecker.checkPlayer(this);

    if(this.type == 2 && contactPlayer == true){
        if(gp.player.invc == false){
            //gives dmg
            gp.player.life -= 1;
            gp.player.invc = true;

        }
    }


        //if its false npc can move
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

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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

            g2.drawImage(image, screenX ,screenY, gp.tileSize, gp.tileSize,  null);
        }

    }
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(  imagePath+ ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e){
            e.printStackTrace();
        }
        return image;

    }

}
