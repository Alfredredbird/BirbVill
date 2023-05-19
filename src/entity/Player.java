package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.Object_door;
import objects.Object_key;
import objects.sheild;
import objects.weapon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player extends Entity{


    KeyHandler keyH;



    public final int screenX;
    public boolean rest = false;
    public final int screenY;
    public boolean attackCancel = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int invntroySize = 20;

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

        attackArea.width = 36;
        attackArea.height = 36;



        setDefaultVal();
        getPlayerImage();
        getAttackImg();
        setItems();

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
        level = 1;
        strength = 1;
        xp = 0;
        nextLevelxp = 5;
        gold = 0;
        currentWeapon = new weapon(gp);
        currentItemInOffhand = new sheild(gp);
        attackval = getAttack();
        defence = getDefence();
        dexterity = 2;

    }

    public void setItems(){

        inventory.add(currentWeapon);
        inventory.add(currentItemInOffhand);
        inventory.add(new Object_key(gp));
        inventory.add(new Object_key(gp));

    }
    public int getDefence(){

        return defence = dexterity * currentItemInOffhand.defenceVal ;
    }
    public int getAttack(){

        return attackval = strength * currentWeapon.attackValue;
    }

    public void getPlayerImage(){



            up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
            up2 = setup("/player/up1", gp.tileSize, gp.tileSize);
            down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
            down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
            right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            right2 = setup("/player/right1", gp.tileSize, gp.tileSize);
            left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            left2 = setup("/player/left1", gp.tileSize, gp.tileSize);


             rest_down = setup("/player/rest_down", gp.tileSize, gp.tileSize);




    }
    public void getAttackImg(){
        attack_up1 = setup("/player/attack_0", gp.tileSize, gp.tileSize*2);
        attack_up2 = setup("/player/attack_1", gp.tileSize, gp.tileSize*2);
        attack_down1 = setup("/player/attack_2", gp.tileSize, gp.tileSize*2);
        attack_down2 = setup("/player/attack_3", gp.tileSize, gp.tileSize*2);
        attack_right1 = setup("/player/attack_4", gp.tileSize*2, gp.tileSize*2);
        attack_right2 = setup("/player/attack_5", gp.tileSize*2, gp.tileSize);
        attack_left1 = setup("/player/attack_6", gp.tileSize*2, gp.tileSize*2);
        attack_left2 = setup("/player/attack_7", gp.tileSize*2, gp.tileSize*2);

    }

    public void update(){
        if(attack == true){

            attack();
        }

      else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {


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

            //checks for monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

            contactWMonster(monsterIndex);

            //checks for events
            gp.eHandler.checkEvent();


            //if its false player can move
            if(collisionOn == false && keyH.enterPressed == false){
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
            if(keyH.enterPressed == true && attackCancel == false){
                attack = true;
                spriteCounter = 0;

            }

            attackCancel = false;
            gp.keyH.enterPressed = false;
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
        if(invc == true){
            invcCounter++;
            if (invcCounter > 60){
                invc = false;
                invcCounter = 0;
            }
        }

    }
    public void interactNpc(int i){

        if(gp.keyH.enterPressed == true) {

            if (i != 999){
                    attackCancel = true;
                    gp.gameState = gp.dialogState;
                    gp.npc[i].speak();
            }
        }
    }
    public void attack(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            //gets current data
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreawidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjusts the data
            switch (direction){
                case "up": worldY-= attackArea.height; break;
                case "down": worldY+= attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            //attack area copies solidarea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMob(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreawidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attack = false;
        }

    }

    public void damageMob(int i){
        if(i != 999){
        if(gp.monster[i].invc == false){
            gp.playSoundEffect(6);

            int damage = attackval - gp.monster[i].defence;
            if(damage < 0){
                damage = 0;
            }
            gp.monster[i].life -= damage;
            gp.ui.showMessage(damage + " Damage");
            gp.monster[i].invc= true;
            gp.monster[i].damageReact();



            if(gp.monster[i].life <= 0){

                gp.monster[i].dying = true;
                gp.ui.showMessage("Killed " + gp.monster[i].name);
                gp.ui.showMessage("XP + " + gp.monster[i].xp);
                xp += gp.monster[i].xp;
                checkLevel();
            }
        }
        }
    }
   public void checkLevel(){

        if(xp >= nextLevelxp){

            level++;
            nextLevelxp = nextLevelxp*2;
            maxlife += 2;
            strength++;
            dexterity++;
            attackValue = getAttack();
            defence = getDefence();
            gp.gameState = gp.dialogState;
            gp.ui.currentDialog = "You Leveled Up";


        }
   }



    public void pickUpObject(int i){
        if (i != 999) {

            String text;
            if (inventory.size() != invntroySize) {
                inventory.add(gp.obj[i]);
                text = "Picked Up A " + gp.obj[i].name +"!";
            } else {
                text = "Your Inventory Is Full!";
            }
            gp.ui.showMessage(text);
            gp.obj[i] = null;
//            String objectName = gp.obj[i].name;
//            switch (objectName ){
//                case "Key":
//                    gp.playSoundEffect(4);
//                    hasKey++;
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("Picked Up A Key");
//                    break;
//                case "Door":
//
//                    if(hasKey > 0){
//
//                        hasKey--;
//                        System.out.println("Key--");
//                        gp.obj[i] = null;
//                        gp.ui.showMessage("You Opened A Door!");
//                    } else {
//                        gp.ui.showMessage("You Need A Key To Open This Door!");
//                    }
//                    break;
//            }
//

        }
    }
    public void contactWMonster(int i){

        if(i != 999){


            if(invc == false){
                int damage = gp.monster[i].attackValue - defence ;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invc = true;
            }

        }

    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(attack == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if(attack == true){
                    if (spriteNum == 1) {
                        image = attack_up1;
                    }
                    if (spriteNum == 2) {
                        image = attack_up2;
                    }
                }

                break;
            case "down":
                if(attack == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (rest == true){
                        image = rest_down;
                    }
                }
                if(attack == true) {
                    if (spriteNum == 1) {
                        image = attack_down1;
                    }
                    if (spriteNum == 2) {
                        image = attack_down2;
                    }
                }
                break;
            case "right":
                if(attack == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if(attack == true) {
                    if (spriteNum == 1) {
                        image = attack_right1;
                    }
                    if (spriteNum == 2) {
                        image = attack_right2;
                    }
                }

                break;
            case "left":
                if(attack == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if(attack == true) {
                    if (spriteNum == 1) {
                        image = attack_left1;
                    }
                    if (spriteNum == 2) {
                        image = attack_left2;
                    }
                }
                break;
        }

        if(invc == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image,screenX ,screenY, 96, 96, null);

        //resets alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

//                g2.setColor(Color.WHITE);
//
//        g2.fillRect(x,y,gp.tileSize, gp.tileSize);



    }



}
