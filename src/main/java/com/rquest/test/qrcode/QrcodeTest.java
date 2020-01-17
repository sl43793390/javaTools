package com.rquest.test.qrcode;
import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

    public class QrcodeTest {


    public static void main(String[] args)
    {
   // write your code here
        String content="";
        Scanner sc=new Scanner(System.in);
        System.out.println("input your information,and ends with ok");
       while(! content.endsWith("ok")){
            content=content+"\n";
           content=content.concat(sc.next());
       }

        content=content.substring(1,content.length()-2);
       // content=content.replaceAll("ok","");

        System.out.println("input your filename ends with EnterKey");
        String filename=sc.next();
        String filepath="D:\\"+filename+".png";
        new QrcodeTest().drawQRCODE(content, filepath);
        System.out.println("draw QR_code successfullly!");
        System.out.println(content);

    }
    public void drawQRCODE(String content,String filepath){
        try {
            Qrcode qrcode=new Qrcode();

            qrcode.setQrcodeErrorCorrect('M');
            qrcode.setQrcodeEncodeMode('B');
            qrcode.setQrcodeVersion(15);
            int width= 235;
            int height=235;
            BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2=image.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0,0,235,235);
            g2.setColor(Color.BLACK);

            byte[] contentbytes=content.getBytes("utf-8");
            boolean[][] codeout= qrcode.calQrcode(contentbytes);
            for (int i = 0; i <codeout.length; i++) {
                for (int j = 0; j < codeout.length; j++) {

                    if (codeout[j][i]) g2.fillRect(j*3+2,i*3+2,3,3);
                }
            }
            g2.dispose();
            image.flush();
            File imgFile = new File(filepath);
            ImageIO.write(image, "png", imgFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void demo(){
    	 String [] str = {""};
    	main(str);
    }
}
