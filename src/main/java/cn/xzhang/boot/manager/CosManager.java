package cn.xzhang.boot.manager;


import cn.xzhang.boot.config.CosClientConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * Cos 对象存储操作
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;


    /**
     * 将本地文件上传到指定的存储桶中。
     *
     * @param key           存储对象在存储桶中的唯一标识符。
     * @param localFilePath 本地文件的路径。
     * @return PutObjectResult 上传结果，包含上传的元数据等信息。
     */
    public PutObjectResult putObject(String key, String localFilePath) {
        // 创建上传请求，指定存储桶名称、对象键和本地文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key,
                new File(localFilePath));
        // 执行上传操作并返回结果
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 将指定的文件上传到存储桶中。
     *
     * @param key  存储对象在存储桶中的唯一标识符。
     * @param file 要上传的文件。
     * @return PutObjectResult 上传结果，包含上传的元数据等信息。
     */
    public PutObjectResult putObject(String key, File file) {
        // 创建上传请求，指定存储桶名称、对象键和文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key,
                file);
        // 执行上传操作并返回结果
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 从指定的存储桶中删除对象。
     *
     * @param bucketName 存储桶的名称，指定要从中删除对象的存储桶。
     * @param key        对象的键，指定要删除的对象在存储桶中的唯一标识符。
     */
    public void deleteObject(String bucketName, String key) {
        cosClient.deleteObject(bucketName, key); // 调用COS客户端的deleteObject方法，删除指定存储桶中的对象
    }




}
