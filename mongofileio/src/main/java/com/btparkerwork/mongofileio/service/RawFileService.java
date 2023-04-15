package com.btparkerwork.mongofileio.service;

import com.btparkerwork.mongofileio.model.RawFile;
import org.springframework.web.multipart.MultipartFile;

public interface RawFileService {
    String save(MultipartFile file);

    RawFile findById(String id);
}
