package main;

import entity.Entity;
import entity.Npc_birb;
import entity.tut_birb;
import monster.Blue_slime;
import monster.big_blue_slime;
import objects.Object_door;
import objects.Object_key;
import objects.sign;

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
        gp.obj[3].worldX = 9 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new sign(gp);
        gp.obj[4].worldX = 11 * gp.tileSize;
        gp.obj[4].worldY = 15 * gp.tileSize;



    }
    public void setNPC(){

        gp.npc[0] = new Npc_birb(gp);
        gp.npc[0].worldX = gp.tileSize*9;
        gp.npc[0].worldY = gp.tileSize*9;

        gp.npc[1] = new tut_birb(gp);
        gp.npc[1].worldX = gp.tileSize*13;
        gp.npc[1].worldY = gp.tileSize*19;
    }

    public void setMonster(){
        gp.monster[0] = new big_blue_slime(gp);
        gp.monster[0].worldX = gp.tileSize*21;
        gp.monster[0].worldY = gp.tileSize*20;

        gp.monster[1] = new Blue_slime(gp);
        gp.monster[1].worldX = gp.tileSize*10;
        gp.monster[1].worldY = gp.tileSize*10;
    }
}
