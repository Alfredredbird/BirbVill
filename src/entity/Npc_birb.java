package entity;

import main.GamePanel;

import java.util.Random;


public class Npc_birb extends Entity{


    public Npc_birb(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){


        up1 = setup("/npc/birb_npc_4", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/birb_npc_5", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/birb_npc_4", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/birb_npc_5", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/birb_npc_2", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/birb_npc_3", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/birb_npc_0", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/birb_npc_1", gp.tileSize, gp.tileSize);


    }
    public void setDialogue(){
        dialouges[0] = "Hi Im A Birdie *Chirp* *Chirp*";
        dialouges[1] = "Get Keys To Open Doors";
        dialouges[2] = "The Devs Are Lazy *Chirp*";
        dialouges[3] = "*peep*";
        dialouges[4] = "Did You Know That You \nHave " + gp.player.life + " Hearts Left.";

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
