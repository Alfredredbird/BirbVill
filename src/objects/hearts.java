package objects;

import entity.Entity;
import main.GamePanel;

public class hearts extends Entity {

    public hearts(GamePanel gp){
        super(gp);
        name = "Hearts";
        image = setup("/ui/heart_0", gp.tileSize, gp.tileSize);
        image2 = setup("/ui/heart_1", gp.tileSize, gp.tileSize);
        image3 = setup("/ui/heart_2", gp.tileSize, gp.tileSize);


    }
}
