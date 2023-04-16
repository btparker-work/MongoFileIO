package com.btparkerwork.mongofileio.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.btparkerwork.mongofileio.model.RawFile;
import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;

@Repository
public class RawFileRepositoryImpl implements RawFileRepository {
    private final GridFSBucket gridFSBucket;

    public RawFileRepositoryImpl(MongoClient mongoClient, @Value("${spring.data.mongodb.database}") String databaseName,
            @Value("${spring.data.mongodb.gridfs.bucket}") String bucketName) {
        this.gridFSBucket = GridFSBuckets.create(mongoClient.getDatabase(databaseName), bucketName);
    }

    @Override
    public String save(RawFile raw) {
        // save file to GridFS bucket named "rawFiles" in MongoDB
        GridFSUploadOptions options = new GridFSUploadOptions().metadata(new Document("name", raw.getName()))
                .metadata(new Document("contentType", raw.getType()));
        ObjectId fileId = this.gridFSBucket.uploadFromStream(raw.getName(), new ByteArrayInputStream(raw.getData()),
                options);
        raw.setId(fileId.toHexString());
        return raw.getId();
    }

    @Override
    public RawFile findById(String id) {
        ObjectId fileId;
        try {
            fileId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
        GridFSDownloadStream downloadStream = this.gridFSBucket.openDownloadStream(fileId);
        if (downloadStream == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.gridFSBucket.downloadToStream(fileId, outputStream);
        return new RawFile(
                id,
                (String) downloadStream.getGridFSFile().getMetadata().get("name"),
                (String) downloadStream.getGridFSFile().getMetadata().get("contentType"),
                outputStream.toByteArray());
    }

    @Override
    public Map<String, String> findAllByDateRange(Date startDate, Date endDate) {
        Map<String, String> files = new HashMap<>();
        Bson filter = Filters.and(Filters.gte("uploadDate", startDate), Filters.lt("uploadDate", endDate));
        GridFSFindIterable iterable = this.gridFSBucket.find(filter);
        for (GridFSFile file : iterable) {
            String fileId = file.getObjectId().toHexString();
            String fileName = (String) file.getFilename();
            files.put(fileName, fileId);
        }
        return files;
    }

}
