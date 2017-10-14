package pers.yzj.picture.mod.impl;

import pers.yzj.picture.util.*;
import pers.yzj.picture.mod.PicIntegration;
import pers.yzj.picture.util.t.GlowFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PicIntegrationImpl implements PicIntegration {


    @Override
    public File select() {
        return new Files().selectFile();
    }

    @Override
    public BufferedImage fileLoad(File file) {
        if(file==null)return  null;
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void cover(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,500,300);
    }

    @Override
    public void draw(Graphics graphics, BufferedImage bufferedImage) {
        try {
            graphics.drawImage(new Minify().minify(500,300,bufferedImage),0,0,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage flip(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage=new Flip().flipImage(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage spinL(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage=new Spin().spinImage(bufferedImage,90);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage spinR(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage=new Spin().spinImage(bufferedImage,-90);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage gray(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage=new Gray().gray(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage clearImage(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage=new ClearImage().cleanImage(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage gaussianBlur(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage = new GaussianBlur().gaussian(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage usmSharper(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage = new USMSharper().getSharperPicture(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage waveFiltering(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage =new WaveFiltering().snnFiltering(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage avrFiltering(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage =new WaveFiltering().avrFiltering(bufferedImage);
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public BufferedImage glowFilter(Graphics graphics, BufferedImage bufferedImage) {
        bufferedImage =new GlowFilter().filter(bufferedImage,null);
        System.out.println("a");
        this.draw(graphics, bufferedImage);
        return bufferedImage;
    }

    @Override
    public boolean saveFile(File file, BufferedImage bufferedImage) {
        if(file==null){return false;}
        File t=new File(file.getParent()+"\\new_"+file.getName());
       return new Files().saveFile(t,bufferedImage);
    }
}
