package com.lemonbada.ekgxml.helper;

import com.lemonbada.ekgxml.dto.NetApp;
import retrofit2.http.*;

public interface NetAppIF {

    @POST("/protocols/s3/services/{svmUUID}/buckets")
    NetApp.BucketResponse createBucket(
            @Path("svmUUID") String svmUUID,
            @Body NetApp.BucketRequest body);

    @DELETE("/protocols/s3/services/{svmUUID}/buckets/{uuid}")
    NetApp.BucketResponse deleteBucket(
            @Path("svmUUID") String svmUUID,
            @Path("uuid") String uuid);

    @GET("/protocols/s3/services/{svmUUID}/buckets/{uuid}")
    NetApp.BucketResponse findBucket(
            @Path("svmUUID") String svmUUID,
            @Path("uuid") String uuid);

    @POST("/storage/volumes/{volume.uuid}/files/{path}")
    NetApp.BucketResponse uploadFile(
            @Path("volumeUUID") String volumeUUID,
            @Body NetApp.BucketRequest body);

}
