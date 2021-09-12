package com.example.image.imageiskcon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {

    byte[] writeTextOnImage(MultipartFile file, String text) throws IOException;

}
