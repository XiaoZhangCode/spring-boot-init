package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.userrecord.UserRecordAddReqDTO;
import cn.xzhang.boot.model.dto.userrecord.UserRecordPageReqDTO;
import cn.xzhang.boot.model.dto.userrecord.UserRecordUpdateReqDTO;
import cn.xzhang.boot.model.entity.UserRecord;
import cn.xzhang.boot.model.vo.userrecord.UserRecordSimpleVo;
import cn.xzhang.boot.model.vo.userrecord.UserRecordVo;
import cn.xzhang.boot.service.UserRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * 提现记录管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 提现记录管理")
@RestController
@RequestMapping("/userRecord")
public class UserRecordController {

    @Resource
    private UserRecordService userrecordService;

    /**
     * 创建UserRecord
     *
     * @param userrecordReqDTO UserRecord添加请求数据传输对象，包含新增UserRecord的信息
     * @return 返回操作结果，其中包含新添加UserRecord的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建提现记录")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addUserRecord(@RequestBody UserRecordAddReqDTO userrecordReqDTO) {
        if (userrecordReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = userrecordService.addUserRecord(userrecordReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新提现记录信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateUserRecord(@RequestBody @Valid UserRecordUpdateReqDTO userrecordReqDTO) {
        // 检查传入的请求数据是否为空
        if (userrecordReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = userrecordService.updateUserRecord(userrecordReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除提现记录")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "提现记录ID",required = true)
    public CommonResult<Boolean> deleteUserRecord(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = userrecordService.deleteUserRecord(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取提现记录")
    @Parameter(name = "id", description = "提现记录ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<UserRecordVo> getUserRecord(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(userrecordService.getUserRecordVO(userrecordService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取提现记录简要信息")
    @Parameter(name = "id", description = "提现记录ID",required = true)
    public CommonResult<UserRecordSimpleVo> getUserRecordVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        UserRecord userrecord = userrecordService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(userrecordService.getSimpleUserRecordVO(userrecord));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取提现记录列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<UserRecordVo>> getUserRecordPage(UserRecordPageReqDTO userrecordPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(userrecordService.getUserRecordPage(userrecordPageReqDTO));
    }

}