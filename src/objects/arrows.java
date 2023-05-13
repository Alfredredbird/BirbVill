package objects;

import entity.Entity;
import main.GamePanel;

public class arrows extends Entity {

    public arrows(GamePanel gp){
        super(gp);
        name = "Arrows";
        image = setup("/ui/down_arrow", gp.tileSize, gp.tileSize);
        image2 = setup("/ui/arrow", gp.tileSize, gp.tileSize);


    }
}
