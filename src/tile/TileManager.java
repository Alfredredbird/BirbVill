package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map.txt");
    }
    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while (col < gp.maxWorldCol ){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol ){
                    col = 0;
                    row++;
                }

            }
            br.close();
        }catch (Exception e){

        }

    }
    public void getTileImage(){


            setUp(2, "swamp_grass", false);
            setUp(1, "grass", false);
            setUp(0, "tree", true);
            setUp(3, "grass2", false);
            setUp(4, "water", true);
            setUp(5, "dirt", false);
            setUp(6, "stone", true);
            setUp(7, "grass_path_right", false);
            setUp(8, "dirt_path_left", false);
            setUp(9, "dirt_path_corner_3", false);
            setUp(10, "grass_path_left", false);
            setUp(11, "grass_up1", false);
            setUp(12, "grass_up2", false);
            setUp(13, "grass_path_down", false);
            setUp(14, "grass_up1", true);
            setUp(15, "grass_up1", true);
            setUp(16, "grass_down1", false);
            setUp(17, "grass_down2", false);


    }

    public void setUp(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
//        g2.drawImage(tile[2].image, 0,0,gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 48,0,gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 96,0,gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX ,screenY,   null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
