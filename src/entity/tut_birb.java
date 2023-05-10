package entity;

import main.GamePanel;

import java.util.Random;


public class tut_birb extends Entity{


    public tut_birb(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){


        up1 = setup("/npc/birb_npc_4");
        up2 = setup("/npc/birb_npc_5");
        down1 = setup("/npc/birb_npc_4");
        down2 = setup("/npc/birb_npc_5");
        right1 = setup("/npc/birb_npc_2");
        right2 = setup("/npc/birb_npc_3");
        left1 = setup("/npc/birb_npc_0");
        left2 = setup("/npc/birb_npc_1");


    }
    public void setDialogue(){
        dialouges[0] = "Hey Take This Sword \n and kill some enemies. \n Come back when you killed one.";
        dialouges[1] = "*peep*";


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
