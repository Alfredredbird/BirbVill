package objects;

import entity.Entity;
import main.GamePanel;

public class hearts extends Entity {

    public hearts(GamePanel gp){
        super(gp);
        name = "Hearts";
        image = setup("/ui/heart_0");
        image2 = setup("/ui/heart_1");
        image3 = setup("/ui/heart_2");


    }
}
