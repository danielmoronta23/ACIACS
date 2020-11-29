package ACIACS.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// QR Code Generator in Java using opensource library ZXing
public class GenerateQRCode {
    // Image properties
    private static final int qr_image_width = 240;
    private static final int qr_image_height = 240;
    private static final String IMAGE_FORMAT = "png";
    private static final String IMG_PATH = "src/main/resources/qrcode.png";

    // Let's do it
    public byte[] GenerarQR(String data) throws IOException {
        // Encode URL in QR format
        BitMatrix matrix;
        Writer writer = new QRCodeWriter();
        try {

            matrix = writer.encode(data, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);

        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return new byte[0];
        }

        // Create buffered image to draw to
        BufferedImage image = new BufferedImage(qr_image_width,
                qr_image_height, BufferedImage.TYPE_INT_RGB);

        // Iterate through the matrix and draw the pixels to the image
        for (int y = 0; y < qr_image_height; y++) {
            for (int x = 0; x < qr_image_width; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, IMAGE_FORMAT, baos);
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

}