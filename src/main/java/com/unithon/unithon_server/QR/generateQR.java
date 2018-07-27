package com.unithon.unithon_server.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class generateQR {

    public  void generateQRcode() throws Exception {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "JavaSampleApproach\nJava Technology, Spring Framework",
                BarcodeFormat.QR_CODE,
                350, 350); // width x height



        Path path = FileSystems.getDefault().getPath("/Users/namkiwon/Desktop/a.png");
        System.out.println(path.toString());

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        // ImageIO를 사용한 바코드 파일쓰기
        try {
            ImageIO.write(bufferedImage, "png", new File("/Users/namkiwon/Desktop/a.png"));
        }catch (NumberFormatException e){
            System.out.println( "  ");
        }

    }
}
