package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class sign extends Entity {


    public sign(GamePanel gp){
        super(gp);
        name = "Sign";
        down1 = setup("/objects/sign", gp.tileSize, gp.tileSize);


    }

}
