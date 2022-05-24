package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_speedBoost extends SuperObject {
	GamePanel gp;
	public OBJ_speedBoost(GamePanel gp) {
		this.gp = gp;
		name = "speedBoost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/speedBoost.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
