package entity;

import main.GamePanel;

import java.util.Random;


public class Little_pepper extends Entity{


    public Little_pepper(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){


        up1 = setup("/npc/parrot_left", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/parrot_left", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/parrot_right", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/parrot_right", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/parrot_right", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/parrot_right", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/parrot_left", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/parrot_left", gp.tileSize, gp.tileSize);


    }
    public void setDialogue(){

        dialouges[0] = "*peep*";


    }
    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            System.out.println(actionLockCounter);
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i >= 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }
    public void speak(){
        super.speak();
    }
}
