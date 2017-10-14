package pers.yzj.picture.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 缩小图片显示
 */
public class Minify {
    public BufferedImage minify(int width, int height, BufferedImage imge) throws IOException {
        double imageWidth = imge.getWidth(null);
        double imageHeight = imge.getHeight(null);
        int w=(int)imageWidth,h=(int)imageHeight;
        if(imageWidth>width && imageHeight>height){
            if(imageWidth-width>=imageHeight-height){
                w=width;
                h=(int)(imageHeight*(width/imageWidth));
            }else {
                w=(int)(imageWidth*(height/imageHeight));
                h=height;
            }

        }else if(imageWidth>width){
            w=width;
            h=(int)(imageHeight*(width/imageWidth));
        }else if(imageHeight>height){
            w=(int)(imageWidth*(height/imageHeight));
            h=height;
        }
        System.out.println(w+" "+h);

        //构建图片对象
        BufferedImage _image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        //绘制缩小后的图
        _image.getGraphics().drawImage(imge, 0, 0, w, h, null);
        return _image;
    }
}
