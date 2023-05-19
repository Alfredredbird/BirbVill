package objects;

import entity.Entity;
import main.GamePanel;

public class health_bar extends Entity {

    public health_bar(GamePanel gp){
        super(gp);
        name = "Ui";
        image = setup("/health_bar/full", gp.tileSize, gp.tileSize);
        image2 = setup("/health_bar/almost_full", gp.tileSize, gp.tileSize);
        image3 = setup("/health_bar/mid", gp.tileSize, gp.tileSize);
        image4 = setup("/health_bar/almost_dead", gp.tileSize, gp.tileSize);



    }
}
