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

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,rest_down;
    public BufferedImage attack_up1, attack_up2, attack_down1, attack_down2,
            attack_left1, attack_left2, attack_right1, attack_right2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    int dyingCounter =  0;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean invc = false;
    public int invcCounter = 0;

    boolean attack = false;
    public boolean alive = true;
     public boolean dying = false;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;

    public int hpbarCounter = 0;
    String dialouges[] = new String[20];
    int diagloeIndex = 0;
    public BufferedImage image, image2, image3, image4, image5,image6, image7,image8;
    public String name;
    public boolean collision = false;

    //chracter health stats
    public int maxlife;
    public int life;
    public int type; //0 = player, etc
    public boolean hpBarOn = false;

    public int level;
    public int strength;
    public int attackval;
    public int defence;
    public int xp;
    public int nextLevelxp;
    public int gold;
    public Entity currentWeapon;
    public Entity currentItemInOffhand;

    //item atributes
    public int attackValue;
    public int defenceVal;
    public String description = "";
    public int dexterity;





    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){

    }
    public void damageReact(){

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
            int damage = attackValue - gp.player.defence ;
            if(damage < 0){
                damage = 0;
            }
            gp.player.life -= damage;
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
        if(invc == true){
            invcCounter++;
            if (invcCounter > 30){
                invc = false;
                invcCounter = 0;
            }
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

            //dras the hp bar
            if(type == 2 && hpBarOn == true) {

                double scale = (double)gp.tileSize/maxlife;
                double hpBarVal = scale*life;

//
//                g2.setColor(new Color(35, 35, 35));
//                g2.fillRect(screenX-1, screenY - 16, gp.tileSize+3, 12);
//                g2.setColor(new Color(255, 0, 30));
//                g2.fillRect(screenX, screenY - 15, (int)hpBarVal , 10);
                if(maxlife == 4) {
                    if (life == 4) {
                        g2.drawImage(gp.ui.h1, screenX - 1, screenY - 16, 64, 7, null);
                    }
                    if (life == 3) {
                        g2.drawImage(gp.ui.h2, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 2) {
                        g2.drawImage(gp.ui.h3, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 1) {
                        g2.drawImage(gp.ui.h4, screenX - 1, screenY - 16, 64, 14, null);
                    }
                }
                if(maxlife == 10) {
                    
                    if (life == 10) {
                        g2.drawImage(gp.ui.h1, screenX - 1, screenY - 16, 64, 7, null);

                    }
                    if (life == 9) {
                        g2.drawImage(gp.ui.h1, screenX - 1, screenY - 16, 64, 14, null);

                    }
                    if (life == 8) {
                        g2.drawImage(gp.ui.h2, screenX - 1, screenY - 16, 64, 14, null);

                    }
                    if (life == 7) {
                        g2.drawImage(gp.ui.h2, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 6) {
                        g2.drawImage(gp.ui.h3, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 5) {
                        g2.drawImage(gp.ui.h3, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 4) {
                        g2.drawImage(gp.ui.h3, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 3) {
                        g2.drawImage(gp.ui.h4, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 2) {
                        g2.drawImage(gp.ui.h4, screenX - 1, screenY - 16, 64, 14, null);
                    }
                    if (life == 1 ) {
                        g2.drawImage(gp.ui.h4, screenX - 1, screenY - 16, 64, 14, null);
                    }

                }

                hpbarCounter++;

                if(hpbarCounter > 400){
                    hpbarCounter = 0;


                    hpBarOn = false;

                }
            }

            if(invc == true){
                hpBarOn = true;
                hpbarCounter = 0;
                changeAlpha(g2, 0.5F );

            }
            if (dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX ,screenY, gp.tileSize, gp.tileSize,  null);

            changeAlpha(g2, 1F );
        }

    }




    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 5;
        if(dyingCounter <= i){
            changeAlpha(g2, 0F);
        }
        if(dyingCounter > i && dyingCounter >= i*2){
            changeAlpha(g2, 1F);
        }
        if(dyingCounter > i*2 && dyingCounter >= i*3){
            changeAlpha(g2, 0F);
        }
        if(dyingCounter > i*3 && dyingCounter >= i*4){
            changeAlpha(g2, 1F);
        }
        if(dyingCounter > i*4 && dyingCounter >= i*5){
            changeAlpha(g2, 0F);
        }
        if(dyingCounter > i*5 && dyingCounter >= i*6){
            changeAlpha(g2, 1F);
        }
        if(dyingCounter > i*6 ){
            dying = false;
            alive = false;

        }


    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(  imagePath+ ".png"));
            image = uTool.scaleImage(image, width, height );

        } catch (IOException e){
            e.printStackTrace();
        }
        return image;




    }

}
