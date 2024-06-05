package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.user.*;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.vo.user.LoginUserVO;
import cn.xzhang.boot.model.vo.user.UserSimpleVo;
import cn.xzhang.boot.model.vo.user.UserVo;
import cn.xzhang.boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;
import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAM_ERROR;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 用户管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 用户管理")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public CommonResult<Long> userRegister(@RequestBody UserRegisterReqDTO userRegisterRequest) {
        if (userRegisterRequest == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return CommonResult.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginReq 用户登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public CommonResult<LoginUserVO> userLogin(@RequestBody UserLoginReqDTO userLoginReq) {
        if (userLoginReq == null) {
            throw exception(BAD_REQUEST_PARAMS);
        }
        String userAccount = userLoginReq.getUserAccount();
        String userPassword = userLoginReq.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw exception(BAD_REQUEST_PARAM_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword);
        return CommonResult.success(loginUserVO);
    }


    /**
     * 获取当前登录的用户信息。
     * 该接口不需要任何参数，通过调用userService的getLoginUser方法，
     * 获取当前登录的用户对象，然后将其转换为LoginUserVO类型，
     * 包装在CommonResult中返回。
     *
     * @return CommonResult<LoginUserVO> 包含当前登录用户信息的CommonResult对象。
     */
    @GetMapping("/get/login")
    @Operation(summary = "获取当前登录用户")
    public CommonResult<LoginUserVO> getLoginUser() {
        // 从userService获取当前登录的用户
        User user = userService.getLoginUser();
        // 将User对象转换为LoginUserVO类型，并返回成功结果
        return CommonResult.success(userService.getLoginUserVO(user));
    }

    /**
     * 创建用户
     *
     * @param userReqDTO 用户添加请求数据传输对象，包含新增用户的信息
     * @return 返回操作结果，其中包含新添加用户的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建用户")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addUser(@RequestBody UserAddReqDTO userReqDTO) {
        // 检查传入的用户请求数据是否为空
        if (userReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加用户，并获取添加结果（用户ID）
        long result = userService.addUser(userReqDTO);
        // 返回添加用户成功响应结果，包含用户ID
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateUser(@RequestBody @Valid UserUpdateReqDTO userReqDTO) {
        // 检查传入的用户请求数据是否为空
        if (userReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新用户信息，并获取更新结果
        boolean result = userService.updateUser(userReqDTO);
        // 返回更新用户信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "用户ID",required = true)
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        // 检查传入的用户ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除用户，并获取删除结果
        boolean result = userService.deleteUser(id);
        // 返回删除用户成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取用户")
    @Parameter(name = "id", description = "用户ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<UserVo> getUser(@RequestParam("id") Long id) {
        // 检查传入的用户ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取用户信息，并返回结果
        return CommonResult.success(userService.getUserVO(userService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取用户")
    @Parameter(name = "id", description = "用户ID",required = true)
    public CommonResult<UserSimpleVo> getUserVO(@RequestParam("id") Long id) {
        // 检查传入的用户ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        User user = userService.getById(id);
        // 调用服务层方法，获取用户信息，并返回结果
        return CommonResult.success(userService.getSimpleUserVO(user));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取用户列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<UserVo>> getUserPage(UserPageReqDTO userPageReqDTO) {
        // 调用服务层方法，获取用户分页信息，并返回结果
        return CommonResult.success(userService.getUserPage(userPageReqDTO));
    }



    // 修改个人信息
    @PutMapping("/update/profile")
    @Operation(summary = "修改个人信息")
    @SaCheckLogin
    public CommonResult<Boolean> updateProfile(@RequestBody UserProfileUpdateReqDTO userProfileUpdateReqDTO) {
        // 检查传入的用户请求数据是否为空
        if (userProfileUpdateReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新用户信息，并获取更新结果
        boolean result = userService.updateUserProfile(userProfileUpdateReqDTO);
        // 返回更新用户信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/logout")
    @Operation(summary = "注销")
    @SaCheckLogin
    public CommonResult<Boolean> logout() {
        // 调用服务层方法，注销用户，并获取注销结果
        StpUtil.logout();
        // 返回注销用户成功响应结果
        return CommonResult.success(true);
    }






}
