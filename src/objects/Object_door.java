package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_door extends SuperObject{

    GamePanel gp;
    public Object_door(GamePanel gp){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
