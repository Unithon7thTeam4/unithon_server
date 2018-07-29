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

    public  File generateQRcode(String contents,String id)  {

        File file = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(
                    contents,
                    BarcodeFormat.QR_CODE,
                    500, 500); // width x height

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            // ImageIO를 사용한 바코드 파일쓰기
            file = new File("./"+id+"_qr.png");

            ImageIO.write(bufferedImage, "png", file);
        }catch (Exception e){
            e.getStackTrace();
        }

        return file;
    }
}
