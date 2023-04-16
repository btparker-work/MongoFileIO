package com.btparkerwork.mongofileio.service;

import com.btparkerwork.mongofileio.model.RawFile;
import com.btparkerwork.mongofileio.repository.RawFileRepository;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RawFileServiceImpl implements RawFileService {

    @Autowired
    private RawFileRepository repository;

    @Override
    public String save(MultipartFile file) {
        RawFile rawFile = new RawFile();
        rawFile.setName(file.getOriginalFilename());
        rawFile.setType(file.getContentType());
        try {
            rawFile.setData(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repository.save(rawFile);
    }

    @Override
    public RawFile findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Map<String, String> findAllByDateRange(Date startDate, Date endDate) {
        return repository.findAllByDateRange(startDate, endDate);
    }
}
