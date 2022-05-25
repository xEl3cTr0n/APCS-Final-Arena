package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_heal;

public class UI {
	GamePanel gp;
	Font arial_40;
	BufferedImage healImage;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		OBJ_heal heal = new OBJ_heal(gp);
		healImage = heal.image;
	}
	public void draw(Graphics2D g2) {
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawImage(healImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x = " + gp.slayer.hasHeal, 74, 65);
	}
}
