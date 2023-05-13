package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Blue_slime extends Entity {

    GamePanel gp;

    public Blue_slime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "Blue Slime";
        speed = 1;
        maxlife = 4;
        life = maxlife;

        solidArea.x = 4;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("/monster/blue_slime1", 16,16);
        up2 = setup("/monster/blue_slime2", 16,16);

        down1 = setup("/monster/blue_slime1", 16,16);
        down2 = setup("/monster/blue_slime2", 16,16);

        left1 = setup("/monster/blue_slime1", 16,16);
        left2 = setup("/monster/blue_slime2", 16,16);

        right1 = setup("/monster/blue_slime1", 16,16);
        right2 = setup("/monster/blue_slime2",16,16);

    }
    public void setAction(){


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
}
