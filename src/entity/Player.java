package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public int hasHeal = 0;
	int hasShield = 0;
	int timeElapsed = 0;
	
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		up1 = setUp("slayerUp1");
		up2 = setUp("slayerUp2");
		down1 = setUp("slayerDown1");
		down2 = setUp("slayerDown2");
		left1 = setUp("slayerLeft1");
		left2 = setUp("slayerLeft2");
		right1 = setUp("slayerRight1");
		right2 = setUp("slayerRight2");
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;	
		direction = "down";
	}

    public BufferedImage setUp(String imagePath){
		UtilityTool uTool = new UtilityTool();
		BufferedImage scaledImage = null;

		try{
		   scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/"+imagePath+".png"));
		   scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
		}catch(IOException e){
			e.printStackTrace();
		}
		return scaledImage;
	}

	public void update() {
		
		if (keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			if (collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY +=speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			spriteCounter++;
			if (spriteCounter > 15) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Heal":
				hasHeal++;
				gp.obj[i] = null;
				System.out.println("Heals: " + hasHeal);
				break;
			case "Shield":
				hasShield++;
				gp.obj[i] = null;
				System.out.println("Shields: " + hasShield);
				break;
			case "speedBoost":
				speed +=3;
				gp.obj[i] = null;
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			else if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			else if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			else if (spriteNum == 2) {
				image = right2;
			}
			break;
		}


        int x = screenX;
		int y = screenY;
		if(screenX > worldX){
			x= worldX;
		}
		if(screenY > worldY){
			y= worldY;
		}

		int rightOffset = gp.screenWidth - screenX;
		if(rightOffset > gp.worldWidth - worldX){
			x = gp.screenWidth - (gp.worldWidth - worldX);
		}	
		int bottomOffset = gp.screenHeight - screenY;
		if(bottomOffset > gp.worldHeight - worldY){
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}	

		g2.drawImage(image, x, y, null);
	}
		
}
