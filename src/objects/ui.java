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
        image4 = setup("/ui/text_box");
        image5 = setup("/ui/youtube_button");
        image6 = setup("/ui/twitter_button");
        image7 = setup("/ui/credits_button");


    }
}
