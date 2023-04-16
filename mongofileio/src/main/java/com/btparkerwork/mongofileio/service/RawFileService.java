package com.btparkerwork.mongofileio.service;

import com.btparkerwork.mongofileio.model.RawFile;

import java.util.Date;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface RawFileService {
    String save(MultipartFile file);

    RawFile findById(String id);

    Map<String, String> findAllByDateRange(Date startDate, Date endDate);
}
