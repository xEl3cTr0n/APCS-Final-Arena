package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_heal extends SuperObject {
	GamePanel gp;
	public OBJ_heal(GamePanel gp) {
		name = "Heal";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heal.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
