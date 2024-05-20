package cn.xzhang.boot.model.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件上传请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "文件上传请求")
public class UploadFileRequest {

    @Schema(description = "业务",requiredMode = Schema.RequiredMode.REQUIRED)
    private String biz;

}
