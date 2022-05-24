package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_shield extends SuperObject {
	GamePanel gp;
	public OBJ_shield(GamePanel gp) {
		this.gp = gp;
		name = "Shield";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/shield.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
