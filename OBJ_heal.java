package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_heal extends SuperObject {
	public OBJ_heal() {
		name = "Heal";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heal.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
