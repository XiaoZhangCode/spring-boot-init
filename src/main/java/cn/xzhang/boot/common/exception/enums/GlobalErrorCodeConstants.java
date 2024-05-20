package cn.xzhang.boot.common.exception.enums;

import cn.xzhang.boot.common.exception.ErrorCode;

/**
 * 全局错误码枚举
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface GlobalErrorCodeConstants {


    ErrorCode SUCCESS = new ErrorCode(0, "成功");
    ErrorCode FAIL = new ErrorCode(1, "失败");


    // ========== 客户端错误段 ==========
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode BAD_REQUEST_PARAMS_ERROR = new ErrorCode(400, "请求参数不正确,{}");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试");
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");
    ErrorCode BAD_REQUEST_PARAMS = new ErrorCode(400, "请求参数不能为空");
    ErrorCode BAD_REQUEST_PARAM_ERROR = new ErrorCode(400, "请求参数错误");

    // ========== 服务端错误段 ==========
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");




    // ========== 自定义错误段 ==========
    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");
    ErrorCode PASSWORD_NOT_MATCH = new ErrorCode(4001, "两次输入的密码不一致");
    ErrorCode PASSWORD_LENGTH_NOT_ENOUGH = new ErrorCode(4002, "密码长度不能小于8");

    ErrorCode USER_NAME_REPEAT = new ErrorCode(4091, "账号重复");

    ErrorCode USER_REGISTER_FAIL = new ErrorCode(4221, "注册失败");

    ErrorCode USER_NOT_EXIST = new ErrorCode(4041, "用户不存在");

    ErrorCode USER_NOT_NORMAL = new ErrorCode(4031, "此账号已被冻结");

    ErrorCode ADD_FAIL = new ErrorCode(4222, "添加失败");
    ErrorCode UPDATE_FAIL = new ErrorCode(4223, "更新失败");
    ErrorCode DELETE_FAIL = new ErrorCode(4224, "删除失败");

    //


    /**
     * 是否为服务端错误，参考 HTTP 5XX 错误码段
     *
     * @param code 错误码
     * @return 是否
     */
    static boolean isServerErrorCode(Integer code) {
        return code != null
                && code >= INTERNAL_SERVER_ERROR.getCode() && code <= INTERNAL_SERVER_ERROR.getCode() + 99;
    }


}
