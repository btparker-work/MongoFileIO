package com.btparkerwork.mongofileio.controller;

import com.btparkerwork.mongofileio.model.RawFile;
import com.btparkerwork.mongofileio.service.RawFileService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/rawfiles")
public class RawFileController {

    @Autowired
    private RawFileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
        
        return new ResponseEntity<>(fileService.save(file), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        RawFile loadFile = fileService.findById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getName() + "\"")
                .body(new ByteArrayResource(loadFile.getData()));
    }

}
