package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class hearts extends SuperObject{
    GamePanel gp;
    public hearts(GamePanel gp){
        name = "Hearts";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ui/heart_0.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/ui/heart_1.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/ui/heart_2.png"));
            image = uTool.scaleImage(image, 120,120);
            image2 = uTool.scaleImage(image2, 120, 120);
            image3 = uTool.scaleImage(image3,120, 120);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
