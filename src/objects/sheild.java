package objects;

import entity.Entity;
import main.GamePanel;

public class sheild extends Entity {
    public sheild(GamePanel gp) {
        super(gp);


        name = "Sheild";
        down1 = setup("/objects/sheild", gp.tileSize, gp.tileSize);
        defenceVal = 3;
    }
}
