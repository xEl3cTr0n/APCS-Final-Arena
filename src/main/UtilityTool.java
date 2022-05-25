package main;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;
public class UtilityTool{
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D  g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0,width,height,	null);
        g2.dispose();
        return scaledImage;
        
    }
}
