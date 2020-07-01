package com.linzx.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.InputStream;

/**
 * 阿里云oss存储
 */
public class AliOSSUtils {

    /**
     * 保存流到文件中
     * @param uploadPath 上传的路径
     * @param inputStream 文件输入流
     * @param config oss配置信息
     */
    public static final void uploadFileToOSS(String uploadPath, InputStream inputStream, OSSConfig config) {
        OSSClient ossClient = new OSSClient(config.getEndpoint(), config.getKeyid(), config.getKeysecret());
        try {
            // 判断容器是否存在,不存在就创建
            if (!ossClient.doesBucketExist(config.getBucketname())) {
                ossClient.createBucket(config.getBucketname());
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(config.getBucketname());
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(config.getBucketname(), uploadPath, inputStream));
            // 设置权限(公开读)
            ossClient.setBucketAcl(config.getBucketname(), CannedAccessControlList.PublicRead);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public static final void downFileFromOSS() {

    }

    public static class OSSConfig {

        private String endpoint;

        private String keyid;

        private String keysecret;

        private String bucketname;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getKeyid() {
            return keyid;
        }

        public void setKeyid(String keyid) {
            this.keyid = keyid;
        }

        public String getKeysecret() {
            return keysecret;
        }

        public void setKeysecret(String keysecret) {
            this.keysecret = keysecret;
        }

        public String getBucketname() {
            return bucketname;
        }

        public void setBucketname(String bucketname) {
            this.bucketname = bucketname;
        }
    }

}
