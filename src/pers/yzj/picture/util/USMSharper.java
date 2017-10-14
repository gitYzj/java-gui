package pers.yzj.picture.util;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class USMSharper {
    public final BufferedImage getSharperPicture(BufferedImage bufferedImage){
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();

        BufferedImage newPic = new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_3BYTE_BGR);
        float[] data =
                { -1.0f, -1.0f, -1.0f, -1.0f, 10.0f, -1.0f, -1.0f, -1.0f, -1.0f };

        Kernel kernel = new Kernel(3, 3, data);
        ConvolveOp co = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        co.filter(bufferedImage, newPic);
        return newPic;
    }
}
