package main;

import java.awt.*;

//This code below is broken and the detection system wont work. if you can fix it go ahead :D
public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX,eventRectDefaultY;
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;

        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;



    }



    public void checkEvent(){

        if(hit(10,10, "down") == true){
            //does event stuff
            System.out.println("SUS");
            pit(gp.dialogState);
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;
        System.out.println("HI");
        if(gp.player.solidArea.intersects(eventRect)){

            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }

        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    //fist event
    public void pit(int gameState){
        gp.gameState = gameState;

        gp.ui.currentDialog ="You Fell And Got Hurt";
        gp.player.life -= 1;


    }


}
