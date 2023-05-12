package objects;

import entity.Entity;
import main.GamePanel;

public class boarders extends Entity {

    public boarders(GamePanel gp){
        super(gp);
        name = "boarder";
        image = setup("/ui/boarder_1");
        image2 = setup("/ui/boarder_2");
        image3 = setup("/ui/boarder_3");
        image4 = setup("/ui/boarder_4");


    }
}
