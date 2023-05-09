package main;

import entity.Npc_birb;
import objects.Object_door;
import objects.Object_key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){

        gp.obj[0] = new Object_key(gp);
        gp.obj[0].worldX = 1 * gp.tileSize;
        gp.obj[0].worldY = 2 * gp.tileSize;


        gp.obj[1] = new Object_key(gp);
        gp.obj[1].worldX = 7 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;

        gp.obj[2] = new Object_door(gp);
        gp.obj[2].worldX = 5 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;

        gp.obj[3] = new Object_door(gp);
        gp.obj[3].worldX = 8 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;


    }
    public void setNPC(){

        gp.npc[0] = new Npc_birb(gp);
        gp.npc[0].worldX = gp.tileSize*9;
        gp.npc[0].worldY = gp.tileSize*9;
    }
}
