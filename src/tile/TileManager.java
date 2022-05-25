package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[17];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/map.txt");
	}
	
	//0 is grass
	//1 is wall
	//2 is grass with a pattern
	//3 is water
	//4 is water with a pattern
	//5 is top
	//6 is top right
	//7 is top left
	//8 is right
	//9 is left
	//10 is bottom
	//11 is bottom left
	//12 is bottom right
	public void getTileImage() {
		setup(0,"grass", false);
		setup(5,"bDown", true);
		setup(2,"grass2", false);
		setup(3,"water", true);
		setup(4,"water2", true);
		setup(1,"top", true);
		setup(6,"tRight", true);
		setup(7,"tLeft", true);
		setup(8,"right", true);
		setup(9,"left", true);
		setup(11,"bLeft", true);
		setup(12,"bRight", true);	
		setup(13,"corner2", true);	
		setup(14,"corner1", true);	
		setup(15,"corner3", true);			
}

	public void setup(int index, String imagePath, boolean collision){
      UtilityTool uTool =  new  UtilityTool();

	  try{
         tile[index] = new Tile();
		 tile[index].image = ImageIO.read(getClass().getResourceAsStream("/arena/"+imagePath+".png"));
		 tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
		 tile[index].collision = collision;
	  }catch(IOException e){
		  e.printStackTrace();
	  }

	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol*gp.tileSize;
			int worldY = worldRow*gp.tileSize;
			int screenX = worldX - gp.slayer.worldX + gp.slayer.screenX;
			int screenY = worldY - gp.slayer.worldY + gp.slayer.screenY;

			if(gp.slayer.screenX > gp.slayer.worldX){
				screenX = worldX;
			}

			if(gp.slayer.screenY > gp.slayer.worldY){
				screenY = worldY;
			}	
			int rightOffset = gp.screenWidth - gp.slayer.screenX;
			if(rightOffset > gp.worldWidth - gp.slayer.worldX){
				screenX = gp.screenWidth - (gp.worldWidth - worldX);
			}	
			int bottomOffset = gp.screenHeight - gp.slayer.screenY;
			if(bottomOffset > gp.worldHeight - gp.slayer.worldY){
				screenY = gp.screenHeight - (gp.worldHeight - worldY);
			}	

			if (worldX + gp.tileSize > gp.slayer.worldX - gp.slayer.screenX && worldX - gp.tileSize < gp.slayer.worldX + gp.slayer.screenX && worldY + gp.tileSize > gp.slayer.worldY - gp.slayer.screenY && worldY - gp.tileSize < gp.slayer.worldY + gp.slayer.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			else if(gp.slayer.screenX > gp.slayer.worldX||
			gp.slayer.screenY > gp.slayer.worldY||
			rightOffset > gp.worldWidth - gp.slayer.worldX||
			bottomOffset > gp.worldHeight - gp.slayer.worldY
			){
              g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}


			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
	}
}
