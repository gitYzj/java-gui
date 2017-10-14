package pers.yzj.picture.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 * 对称近邻均值滤波
 */
public class WaveFiltering {
    /**
     * 对称近邻均值滤波
     * @param img 图片的存储位置
     */
    public  BufferedImage snnFiltering(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] pix = new int[w*h];
        img.getRGB(0, 0, w, h, pix, 0, w);
        int newpix[] = snnFiltering(pix, w, h);
        img.setRGB(0, 0, w, h, newpix, 0, w);
        return img;
    }
    /**
     * 对称近邻均值滤波
     * @param pix 像素矩阵数组
     * @param w 矩阵的宽
     * @param h 矩阵的高
     * @return 处理后的数组
     */
    public static int[] snnFiltering(int pix[], int w, int h) {
        int newpix[] = new int[w*h];
        int n = 9;
        int temp, i1,i2, sum;
        int[] temp1 = new int[n];
        int[] temp2 = new int[n/2];
        ColorModel cm = ColorModel.getRGBdefault();
        int r=0;
        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                if(x!=0 && x!=w-1 && y!=0 && y!=h-1) {
                    sum = 0;
                    temp1[0] = cm.getRed(pix[x-1+(y-1)*w]);
                    temp1[1] = cm.getRed(pix[x+(y-1)*w]);
                    temp1[2] = cm.getRed(pix[x+1+(y-1)*w]);
                    temp1[3] = cm.getRed(pix[x-1+(y)*w]);
                    temp1[4] = cm.getRed(pix[x+(y)*w]);
                    temp1[5] = cm.getRed(pix[x+1+(y)*w]);
                    temp1[6] = cm.getRed(pix[x-1+(y+1)*w]);
                    temp1[7] = cm.getRed(pix[x+(y+1)*w]);
                    temp1[8] = cm.getRed(pix[x+1+(y+1)*w]);
                    for(int k=0; k<n/2; k++) {
                        i1 = Math.abs(temp1[n/2] - temp1[k]);
                        i2 = Math.abs(temp1[n/2] - temp1[n-k-1]);
                        temp2[k] = i1<i2 ? temp1[k] : temp1[n-k-1];  //选择最接近原像素值的一个邻近像素
                        sum = sum + temp2[k];
                    }
                    r = sum/(n/2);
                    //System.out.println("pix:" + temp1[4] + "  r:" + r);
                    newpix[y*w+x] = 255<<24 | r<<16 | r<<8 |r;
                } else {
                    newpix[y*w+x] = pix[y*w+x];
                }
            }
        }
        return newpix;
    }


    /**
     * 均值滤波
     */
    public BufferedImage avrFiltering(BufferedImage img) {

        int w = img.getWidth();
        int h = img.getHeight();
        int[] pix = new int[w*h];
        img.getRGB(0, 0, w, h, pix, 0, w);
        int newpix[] = avrFiltering(pix, w, h);
        img.setRGB(0, 0, w, h, newpix, 0, w);
        return img;
    }
    /**
     * 均值滤波
     * @param pix 像素矩阵数组
     * @param w 矩阵的宽
     * @param h 矩阵的高
     * @return 处理后的数组
     */
    public static int[] avrFiltering(int pix[], int w, int h) {
        int newpix[] = new int[w*h];
        ColorModel cm = ColorModel.getRGBdefault();
        int r=0;
        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                if(x!=0 && x!=w-1 && y!=0 && y!=h-1) {
                    //g = (f(x-1,y-1) + f(x,y-1)+ f(x+1,y-1)
                    //  + f(x-1,y) + f(x,y) + f(x+1,y)
                    //  + f(x-1,y+1) + f(x,y+1) + f(x+1,y+1))/9
                    r = (cm.getRed(pix[x-1+(y-1)*w]) + cm.getRed(pix[x+(y-1)*w])+ cm.getRed(pix[x+1+(y-1)*w])
                            + cm.getRed(pix[x-1+(y)*w]) + cm.getRed(pix[x+(y)*w]) + cm.getRed(pix[x+1+(y)*w])
                            + cm.getRed(pix[x-1+(y+1)*w]) + cm.getRed(pix[x+(y+1)*w]) + cm.getRed(pix[x+1+(y+1)*w]))/9;
                    newpix[y*w+x] = 255<<24 | r<<16 | r<<8 |r;

                } else {
                    newpix[y*w+x] = pix[y*w+x];
                }
            }
        }
        return newpix;
    }


}
