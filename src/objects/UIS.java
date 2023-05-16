package objects;

import entity.Entity;
import main.GamePanel;

public class UIS extends Entity {
    public UIS(GamePanel gp){
        super(gp);
        name = "UIS";
        image = setup("/ui/ui_1", gp.tileSize, gp.tileSize);

        image2 = setup("/ui/ui_2", gp.tileSize, gp.tileSize);


    }

}
