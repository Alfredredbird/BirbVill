package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class sign extends SuperObject{

    GamePanel gp;
    public sign(GamePanel gp){
        name = "Sign";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sign_2.png"));
            uTool.scaleImage(image, 182,182);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
