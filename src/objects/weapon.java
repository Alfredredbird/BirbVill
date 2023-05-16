package objects;

import entity.Entity;
import main.GamePanel;

public class weapon extends Entity {

    public weapon(GamePanel gp) {
        super(gp);

        name = "Sword";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "Very Low Quality Sword";
    }
}
