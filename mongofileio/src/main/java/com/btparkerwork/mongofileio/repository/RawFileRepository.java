package com.btparkerwork.mongofileio.repository;

import com.btparkerwork.mongofileio.model.RawFile;

public interface RawFileRepository {
    String save(RawFile file);

    RawFile findById(String id);
}
