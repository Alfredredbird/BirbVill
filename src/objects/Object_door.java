package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_door extends Entity {

    GamePanel gp;
    public Object_door(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;

        solidArea.width = 48;
        solidArea.height = 32;

        solidAreaDefaultX =   solidArea.x;
        solidAreaDefaultY =   solidArea.y;
    }
}
