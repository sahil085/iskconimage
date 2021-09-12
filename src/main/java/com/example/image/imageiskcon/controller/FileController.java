package com.example.image.imageiskcon.controller;

import com.example.image.imageiskcon.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    FileService fileService;

    @PostMapping(value = "/textOnImage")
    public ResponseEntity<ByteArrayResource> getImage(@RequestPart("file") MultipartFile file,
                                                      @RequestParam("text") String text) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(fileService.writeTextOnImage(file, text));
        String filename = file.getOriginalFilename();
        return ResponseEntity.ok().contentLength(resource.getByteArray().length).header("Content-type", "application/octet-stream;charset=UTF-8")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"").body(resource);
    }

}
