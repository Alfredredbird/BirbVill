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
        up1 = setup("/monster/slime_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slime_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/slime_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/slime_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/slime_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/slime_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/slime_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/slime_2", gp.tileSize, gp.tileSize);
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
