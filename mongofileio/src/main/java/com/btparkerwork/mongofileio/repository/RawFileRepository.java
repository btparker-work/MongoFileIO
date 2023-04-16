package com.btparkerwork.mongofileio.repository;

import java.util.Date;
import java.util.Map;

import com.btparkerwork.mongofileio.model.RawFile;

public interface RawFileRepository {
    String save(RawFile file);

    RawFile findById(String id);

    Map<String, String> findAllByDateRange(Date startDate, Date endDate);
}
