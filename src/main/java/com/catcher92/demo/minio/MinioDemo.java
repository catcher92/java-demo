package com.catcher92.demo.minio;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class MinioDemo {

    public static void main(String[] args) {
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://hadoop00:9000")
                            .credentials("QoNF8GOyDuRBje29Gd0g", "gXwpXSR74LLTxcTchhI0JhiuFSvQiJJYk4liXcVA")
                            .build();
            minioClient.listBuckets().forEach(b -> System.out.printf("bucket:[%s]%n", b.name()));
            minioClient.downloadObject(DownloadObjectArgs.builder()
                    .bucket("bucket1")
                    .object("abc.txt")
                    .filename("/Users/catcher92/Downloads/abc.txt")
                    .overwrite(true).build()
            );
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket("bucket1")
                    .object("credentials.json")
                    .filename("/Users/catcher92/Downloads/credentials.json").build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
