package objects;

import entity.Entity;
import main.GamePanel;

public class Object_key extends Entity {

    public Object_key(GamePanel gp){
        super(gp);

        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);

    }

}
