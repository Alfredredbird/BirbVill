package objects;

import entity.Entity;
import main.GamePanel;

public class ui extends Entity {

    public ui(GamePanel gp){
        super(gp);
        name = "Ui";
        image = setup("/ui/play_button");
        image2 = setup("/ui/quit_button");
        image3 = setup("/ui/quit_button2");


    }
}