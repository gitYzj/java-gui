package pers.yzj.picture.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 旋转
 */
public class Spin {
    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage
     *            目标图像
     * @param degree
     *            旋转角度为90的倍数
     * @return
     */
    public BufferedImage spinImage(BufferedImage bufferedimage,
                                     int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h,w, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);

        if(degree<0){
            graphics2d.translate((h-w)/2,(h-w)/2);
        }else{
            graphics2d.translate(-(h-w)/2,-(h-w)/2);
        }


        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
