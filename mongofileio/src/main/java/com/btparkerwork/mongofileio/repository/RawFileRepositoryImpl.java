package com.btparkerwork.mongofileio.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.btparkerwork.mongofileio.model.RawFile;
import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

@Repository
public class RawFileRepositoryImpl implements RawFileRepository {
    private final GridFSBucket gridFSBucket;

    public RawFileRepositoryImpl(MongoClient mongoClient) {
        this.gridFSBucket = GridFSBuckets.create(mongoClient.getDatabase("products"), "rawFiles");
    }

    @Override
    public String save(RawFile raw) {
        // save file to GridFS bucket named "rawFiles" in MongoDB
        GridFSUploadOptions options = new GridFSUploadOptions().metadata(new Document("name", raw.getName()))
            .metadata(new Document("contentType", raw.getType()));
        ObjectId fileId = this.gridFSBucket.uploadFromStream(raw.getName(), new ByteArrayInputStream(raw.getData()), options);
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
                outputStream.toByteArray()
        );
    }
}

