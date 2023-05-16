package main;

import java.awt.*;
import java.util.Random;

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

        if(hit(14,10, "right") == true){
            //does event stuff
            System.out.println("SUS");
            pit(gp.dialogState);
        }
        if(hit(6, 18, "left") == true){
            drinkWater(gp.dialogState);
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

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
    public void drinkWater(int gameState){

        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attackCancel = true;
            if(gp.player.life < gp.player.maxlife) {
                Random random = new Random();

                int i = random.nextInt(3) + 1;
                gp.ui.currentDialog = "You Drink The Water. \n You Gained " + i + " Hearts";
                gp.player.life += i;
            } else if (gp.player.life >= gp.player.maxlife){
                gp.ui.currentDialog = "You Cant Drink The Water Right \n Now. Come Back When You \n Loose Hearts.";
                gp.aSetter.setMonster();
            }
        }
    }



}
