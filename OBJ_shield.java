package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_shield extends SuperObject {
	public OBJ_shield() {
		name = "Shield";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/shield.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
