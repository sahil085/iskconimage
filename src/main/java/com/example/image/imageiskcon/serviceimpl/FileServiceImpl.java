package com.example.image.imageiskcon.serviceimpl;

import com.example.image.imageiskcon.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public byte[] writeTextOnImage(MultipartFile file, String text) throws IOException {
        log.info("Write Text on image started with text {}", text);
        BufferedImage image = ImageIO.read(file.getInputStream());
        Font font = new Font("Arial", Font.ITALIC, 18);

        Graphics g = image.getGraphics();
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(font);
        int positionX = (image.getWidth() - metrics.stringWidth(text));
        int positionY = (image.getHeight() - metrics.getHeight()) + metrics.getAscent();
        g.drawString(text, positionX, positionY);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            ImageIO.write(image, file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1), outStream);

        log.info("Write Text on image completed with text {}", text);
        return  outStream.toByteArray();

    }
}
