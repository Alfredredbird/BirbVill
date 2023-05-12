package objects;

import entity.Entity;
import main.GamePanel;

public class ui extends Entity {

    public ui(GamePanel gp){
        super(gp);
        name = "Ui";
        image = setup("/ui/play_button", gp.tileSize, gp.tileSize);
        image2 = setup("/ui/quit_button", gp.tileSize, gp.tileSize);
        image3 = setup("/ui/quit_button2", gp.tileSize, gp.tileSize);
        image4 = setup("/ui/text_box", gp.tileSize, gp.tileSize);
        image5 = setup("/ui/youtube_button", gp.tileSize, gp.tileSize);
        image6 = setup("/ui/twitter_button", gp.tileSize, gp.tileSize);
        image7 = setup("/ui/credits_button", gp.tileSize, gp.tileSize);


    }
}
