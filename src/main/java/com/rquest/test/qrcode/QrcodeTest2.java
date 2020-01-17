package com.rquest.test.qrcode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeTest2 {
    
	
	public static void main(String[] args) {
		createQRCode("sunlong","D:\\sunlong1.png");
		
	}
	
    public static byte[] createQRCode(String content,String filepath) {
        byte[] result = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            qrcodeHandler.setQrcodeVersion(7);
            
            byte[] contentBytes = content.getBytes("utf-8");

            BufferedImage bufferImgage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            
            Graphics2D graphics2D = bufferImgage.createGraphics();
            graphics2D.setBackground(Color.WHITE);
            graphics2D.clearRect(0, 0, 200, 200);
            graphics2D.setColor(Color.BLACK);
            int pixoff = 10;
            if (contentBytes.length > 0 && contentBytes.length < 120) {
                boolean[][] matrix = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[j][i]) {
                            graphics2D.fillRect(j * 4 + pixoff, i * 4 + pixoff, 4, 4);
                        }
                    }
                }
            } else {
                //
            }
            graphics2D.dispose();

            bufferImgage.flush();
            File imgFile = new File(filepath);
            
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bufferImgage, "png", imgFile);
            result = output.toByteArray();
            output.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}