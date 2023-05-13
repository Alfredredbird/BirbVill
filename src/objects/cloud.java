package objects;

import entity.Entity;
import main.GamePanel;

public class cloud extends Entity {

    public cloud(GamePanel gp){
        super(gp);
        name = "Cloud";
        image = setup("/ui/cloud_1", gp.tileSize*2, gp.tileSize);


    }
}
