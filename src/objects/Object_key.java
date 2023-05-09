package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_key extends SuperObject{

    GamePanel gp;
    public Object_key(GamePanel gp){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
