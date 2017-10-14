package pers.yzj.picture.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Files {

    public File selectFile(){
        JFileChooser jfc=new JFileChooser();//文件选择器
//        FileNameExtensionFilter filter=new FileNameExtensionFilter(
//                "jpg", "gif");
//         jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(0);//设定只能选择到文件
        int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
        if(state==1){
            return null;//撤销则返回
        }
        else{
            File f=jfc.getSelectedFile();//f为选择到的文件
            return f;
        }
    }


    public boolean saveFile(java.io.File file, RenderedImage renderedImage) {
        //弹出文件选择框
        JFileChooser chooser = new JFileChooser();
        boolean t=false;
        //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
        chooser.setSelectedFile(file);
        int option = chooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION){    //假如用户选择了保存
            try {
                t=ImageIO.write(renderedImage,file.getName().substring(file.getName().indexOf(".")+1),chooser.getSelectedFile());
                System.err.println(file.getName().substring(file.getName().indexOf(".")+1));
                System.out.println(chooser.getSelectedFile());
            } catch (IOException e) {
                System.err.println("IO异常");
                e.printStackTrace();
            }

        }
        return t;
    }

}
