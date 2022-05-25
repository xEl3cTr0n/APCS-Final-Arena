package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
  
		int screenX = worldX - gp.slayer.worldX + gp.slayer.screenX;
		int screenY = worldY - gp.slayer.worldY + gp.slayer.screenY;
		
		// STOP MOVING CAMERA
		if(gp.slayer.worldX < gp.slayer.screenX) {
		 screenX = worldX;
		}
		if(gp.slayer.worldY < gp.slayer.screenY) {
		 screenY = worldY;
		}   
		int rightOffset = gp.screenWidth - gp.slayer.screenX;      
		if(rightOffset > gp.worldWidth - gp.slayer.worldX) {
		 screenX = gp.screenWidth - (gp.worldWidth - worldX);
		} 
		int bottomOffset = gp.screenHeight - gp.slayer.screenY;
		if(bottomOffset > gp.worldHeight - gp.slayer.worldY) {
		 screenY = gp.screenHeight - (gp.worldHeight - worldY);
		}
		///////////////////
		
		if(worldX + gp.tileSize > gp.slayer.worldX - gp.slayer.screenX &&
		   worldX - gp.tileSize < gp.slayer.worldX + gp.slayer.screenX &&
		   worldY + gp.tileSize > gp.slayer.worldY - gp.slayer.screenY &&
		   worldY - gp.tileSize < gp.slayer.worldY + gp.slayer.screenY) {
		 
		 g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		// If player is around the edge, draw everything
		else if(gp.slayer.worldX < gp.slayer.screenX ||
		  gp.slayer.worldY < gp.slayer.screenY ||
		  rightOffset > gp.worldWidth - gp.slayer.worldX ||
		  bottomOffset > gp.worldHeight - gp.slayer.worldY) {
		 g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); 
		}  
	   }
	  
	  
}
