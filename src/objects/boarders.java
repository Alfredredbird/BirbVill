package objects;

import entity.Entity;
import main.GamePanel;

public class boarders extends Entity {

    public boarders(GamePanel gp){
        super(gp);
        name = "boarder";
        image = setup("/ui/boarder_1", gp.tileSize, gp.tileSize);
        image2 = setup("/ui/boarder_2", gp.tileSize, gp.tileSize);
        image3 = setup("/ui/boarder_3", gp.tileSize, gp.tileSize);
        image4 = setup("/ui/boarder_4", gp.tileSize, gp.tileSize);
        image5 = setup("/ui/boarder_twitter", gp.tileSize, gp.tileSize);


    }
}
