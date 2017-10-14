package pers.yzj.picture.layout;

import pers.yzj.picture.mod.PicIntegration;
import pers.yzj.picture.mod.impl.PicIntegrationImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Layouts {
    private JPanel l1;
    private JPanel l2;
    private JPanel la;
    private JPanel panelA;
    private JPanel panelB;
    private JButton btnSelect;
    private JButton btnFlip;
    private JButton btnSpinL;
    private JButton btnSpinR;
    private JButton SpinGray;
    private JButton btnClean;
    private JButton btnGaussian;
    private JButton btnDelete;
    private JButton btnSave;
    private JButton btnWaveFitering;
    private JButton btnSharper;
    private JButton btnAvrFiltering;
    private JButton butGlowFilter;

    private File file =null;
    private BufferedImage bufferedImage=null;
    private PicIntegration picIntegration=new PicIntegrationImpl();
    private Graphics gA= null;
    private  Graphics gB= null;
    public Layouts() {

        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file=picIntegration.select();
                if(file==null)return;
                 bufferedImage=picIntegration.fileLoad(file);
                if(bufferedImage==null)return;
                 gA= panelA.getGraphics();
                 gB= panelB.getGraphics();
                 picIntegration.cover(gA);
                 picIntegration.cover(gB);
                 picIntegration.draw(gA,bufferedImage);
                 picIntegration.draw(gB,bufferedImage);
            }
        });

        btnFlip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.flip(gB,bufferedImage);
            }
        });
        btnSpinL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.spinL(gB,bufferedImage);

            }
        });
        btnSpinR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.spinR(gB,bufferedImage);
            }
        });
        SpinGray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.gray(gB,bufferedImage);

            }
        });
        btnClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.clearImage(gB,bufferedImage);
            }
        });
        btnGaussian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.gaussianBlur(gB,bufferedImage);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bufferedImage==null)return;
                picIntegration.cover(gB);
                bufferedImage=picIntegration.fileLoad(file);
                picIntegration.draw(gA,bufferedImage);
                picIntegration.draw(gB,bufferedImage);


            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file==null){return;}
                picIntegration.saveFile(file,bufferedImage);
            }
        });
        btnWaveFitering.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file==null){return;}
                picIntegration.waveFiltering(gB,bufferedImage);
            }
        });

        btnSharper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file==null){return;}
                picIntegration.usmSharper(gB,bufferedImage);
            }
        });
        btnAvrFiltering.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file==null){return;}
                picIntegration.avrFiltering(gB,bufferedImage);
            }
        });
        butGlowFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file==null){return;}
                picIntegration.glowFilter(gB,bufferedImage);
            }
        });
    }

    public  void layouts(String[] args) {
        JFrame frame = new JFrame("图片处理");
        frame.setContentPane(new Layouts().la);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
