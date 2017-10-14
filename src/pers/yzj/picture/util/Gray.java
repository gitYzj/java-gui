package pers.yzj.picture.util;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * 转黑白
 */
public class Gray {
    public BufferedImage gray(BufferedImage src) {
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        src = op.filter(src, null);
        return src;
    }
}
