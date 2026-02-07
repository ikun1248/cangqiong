package com.sky.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;
import com.sky.properties.AliOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliOssUtil {

    private final AliOssProperties aliyunOSSProperties;
    private final OSS ossClient;

    @Autowired
    public AliOssUtil(AliOssProperties aliyunOSSProperties) throws ClientException {
        this.aliyunOSSProperties = aliyunOSSProperties;

        // 使用环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        config.setSignatureVersion(SignVersion.V4);

        this.ossClient = OSSClientBuilder.create()
                .endpoint(aliyunOSSProperties.getEndpoint())
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(config)
                .region(aliyunOSSProperties.getRegion())
                .build();
    }

    public String upload(byte[] content, String originalFilename) throws Exception {
        String bucketName = aliyunOSSProperties.getBucketName();
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));

        return "https://" + bucketName + "." +
                aliyunOSSProperties.getEndpoint().replace("https://", "") +
                "/" + objectName;
    }
}
