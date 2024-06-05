package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.io.FileUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.constant.FileConstant;
import cn.xzhang.boot.manager.CosManager;
import cn.xzhang.boot.model.dto.file.UploadFileRequest;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.enums.FileUploadBizEnum;
import cn.xzhang.boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 文件上传接口
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@RestController
@RequestMapping("/file")
@Slf4j
@Tag(name = "通用 - 文件上传接口")
public class FileController {

    @Resource
    private CosManager cosManager;

    @Resource
    private UserService userService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SaCheckLogin
    @Operation(summary = "文件上传")
    public CommonResult<String> uploadFile(
            @RequestPart(value = "file")
            @RequestParam("file")
            @Parameter(description = "上传的文件", required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(implementation = MultipartFile.class)))
            MultipartFile multipartFile,
            @RequestParam("biz")
            @Parameter(required = true, description = "业务枚举")
            String biz) {
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw exception(BAD_REQUEST);
        }
        validFile(multipartFile, fileUploadBizEnum);
        User loginUser = userService.getLoginUser();
        // 文件目录：根据业务、用户来划分
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        String filepath = String.format("/%s/%s/%s", fileUploadBizEnum.getValue(), loginUser.getId(), filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
            // 返回可访问地址
            return CommonResult.success(FileConstant.COS_HOST + filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
            throw exception(INTERNAL_SERVER_ERROR);
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }



    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw exception(BAD_REQUEST_PARAMS_ERROR, "文件大小不能超过 1M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw exception(BAD_REQUEST_PARAMS_ERROR, "文件类型错误");
            }
        }
    }

}
