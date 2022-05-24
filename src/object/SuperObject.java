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
		
		if (worldX + gp.tileSize > gp.slayer.worldX - gp.slayer.screenX && worldX - gp.tileSize < gp.slayer.worldX + gp.slayer.screenX && worldY + gp.tileSize > gp.slayer.worldY - gp.slayer.screenY && worldY - gp.tileSize < gp.slayer.worldY + gp.slayer.screenY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
