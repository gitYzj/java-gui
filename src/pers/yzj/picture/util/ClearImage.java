package pers.yzj.picture.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ClearImage {
    /** 
     *
     * @param bufferedImage
     *            圖片來源
     * @throws IOException 
     */  
    public static BufferedImage cleanImage(BufferedImage bufferedImage ){


        int h = bufferedImage.getHeight();  
        int w = bufferedImage.getWidth();  
  
        // 灰度化  
        int[][] gray = new int[w][h];  
        for (int x = 0; x < w; x++){  
            for (int y = 0; y < h; y++){  
                int argb = bufferedImage.getRGB(x, y);  
                // 图像加亮（调整亮度识别率非常高）  
                int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);  
                int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);  
                int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);  
                if (r >= 255){  
                    r = 255;  
                }  
                if (g >= 255){  
                    g = 255;  
                }  
                if (b >= 255){  
                    b = 255;  
                }  
                gray[x][y] = (int) Math.pow((Math.pow(r, 2.2) * 0.2973 + Math.pow(g, 2.2)* 0.6274 + Math.pow(b, 2.2) * 0.0753), 1 / 2.2);  
            }  
        }  
  
        // 二值化  
        int threshold = ostu(gray, w, h);  
        BufferedImage binaryBufferedImage = new BufferedImage(w, h,BufferedImage.TYPE_BYTE_BINARY);  
        for (int x = 0; x < w; x++){  
            for (int y = 0; y < h; y++){  
                if (gray[x][y] > threshold){  
                    gray[x][y] |= 0x00FFFF;  
                } else{  
                    gray[x][y] &= 0xFF0000;  
                }  
                binaryBufferedImage.setRGB(x, y, gray[x][y]);  
            }  
        }  
  
        // 矩阵打印  
        for (int y = 0; y < h; y++){  
            for (int x = 0; x < w; x++){  
                isBlack(binaryBufferedImage.getRGB(x, y));
            }
        }  
       return binaryBufferedImage;
    }  
  
    public static boolean isBlack(int colorInt){  
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 300){  
            return true;  
        }  
        return false;  
    }
  
    public static int ostu(int[][] gray, int w, int h){  
        int[] histData = new int[w * h];  
        // Calculate histogram  
        for (int x = 0; x < w; x++){  
            for (int y = 0; y < h; y++){  
                int red = 0xFF & gray[x][y];  
                histData[red]++;  
            }  
        }  
  
        // Total number of pixels  
        int total = w * h;  
  
        float sum = 0;  
        for (int t = 0; t < 256; t++)  
            sum += t * histData[t];  
  
        float sumB = 0;  
        int wB = 0;  
        int wF = 0;  
  
        float varMax = 0;  
        int threshold = 0;  
  
        for (int t = 0; t < 256; t++){  
            wB += histData[t]; // Weight Background  
            if (wB == 0)  
                continue;  
  
            wF = total - wB; // Weight Foreground  
            if (wF == 0)  
                break;  
  
            sumB += (float) (t * histData[t]);  
  
            float mB = sumB / wB; // Mean Background  
            float mF = (sum - sumB) / wF; // Mean Foreground  
  
            // Calculate Between Class Variance  
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);  
  
            // Check if new maximum found  
            if (varBetween > varMax){  
                varMax = varBetween;  
                threshold = t;  
            }  
        }  
  
        return threshold;  
    }  
    //图片灰度，黑白  
    
//    public static void main(String[] args) throws IOException{
//        File testDataDir = new File("D:\\tmp\\3VG5B.jpg");//去噪
//        String destDir ="D:\\tmp\\tmp";
//        cleanImage(testDataDir, destDir);
//        //gray("D:\\tmp\\3VG5B.jpg","D:\\tmp\\3VG5B1.jpg");//灰度化
//    }
}  