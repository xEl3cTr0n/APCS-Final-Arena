package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;


public class OBJ_HHG extends SuperObject {
	GamePanel gp;
	public OBJ_HHG(GamePanel gp) {
		this.gp = gp;
		name = "Holy Hand Grenade";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/HHG.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
