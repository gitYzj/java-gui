package pers.yzj.picture.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 翻转
 */
public class Flip {
    public static BufferedImage flipImage(BufferedImage bufferedimage) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, bufferedimage
                .getColorModel().getTransparency())).createGraphics())
                .drawImage(bufferedimage, 0, 0, w, h, w, 0, 0, h, null);
        graphics2d.dispose();
        return img;
    }

}
