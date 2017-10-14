package pers.yzj.picture.mod;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public interface PicIntegration {
    //文件选择
    public File select();
    //加载
    public BufferedImage fileLoad(File file);
    //覆盖画布
    public void cover(Graphics graphics);
    //绘图
    public void draw(Graphics graphics,BufferedImage bufferedImage);
    //翻转
    public BufferedImage flip(Graphics graphics,BufferedImage bufferedImage);
    //左转90度
    public BufferedImage spinL(Graphics graphics,BufferedImage bufferedImage);
    //右转90度
    public BufferedImage spinR(Graphics graphics,BufferedImage bufferedImage);
    //黑白
    public BufferedImage gray(Graphics graphics,BufferedImage bufferedImage);
    //去燥
    public BufferedImage clearImage(Graphics graphics,BufferedImage bufferedImage);
    //高斯模糊
    public BufferedImage gaussianBlur(Graphics graphics,BufferedImage bufferedImage);
    //高斯模糊
    public BufferedImage glowFilter(Graphics graphics,BufferedImage bufferedImage);
    //对称近邻均值滤波
    public BufferedImage waveFiltering(Graphics graphics,BufferedImage bufferedImage);
    //中值滤波
    public BufferedImage avrFiltering(Graphics graphics,BufferedImage bufferedImage);
    //锐化
    public BufferedImage usmSharper(Graphics graphics,BufferedImage bufferedImage);

    //保存
    public boolean saveFile(File file,BufferedImage bufferedImage);


}
