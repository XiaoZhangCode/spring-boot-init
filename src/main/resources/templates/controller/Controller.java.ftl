package ${basePackage}.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import ${basePackage}.common.pojo.CommonResult;
import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.constant.UserConstant;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}AddReqDTO;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}PageReqDTO;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}UpdateReqDTO;
import ${basePackage}.model.entity.${entityName};
import ${basePackage}.model.vo.${entityName?lower_case}.${entityName}SimpleVo;
import ${basePackage}.model.vo.${entityName?lower_case}.${entityName}Vo;
import ${basePackage}.service.${entityName}Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static ${basePackage}.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * ${description}管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - ${description}管理")
@RestController
@RequestMapping("/${entityName?lower_case}")
public class ${entityName}Controller {

    @Resource
    private ${entityName}Service ${entityName?lower_case}Service;

    /**
     * 创建${entityName}
     *
     * @param ${entityName?lower_case}ReqDTO ${entityName}添加请求数据传输对象，包含新增${entityName}的信息
     * @return 返回操作结果，其中包含新添加${entityName}的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建${description}")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> add${entityName}(@RequestBody ${entityName}AddReqDTO ${entityName?lower_case}ReqDTO) {
        if (${entityName?lower_case}ReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = ${entityName?lower_case}Service.add${entityName}(${entityName?lower_case}ReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新${description}信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> update${entityName}(@RequestBody @Valid ${entityName}UpdateReqDTO ${entityName?lower_case}ReqDTO) {
        // 检查传入的请求数据是否为空
        if (${entityName?lower_case}ReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = ${entityName?lower_case}Service.update${entityName}(${entityName?lower_case}ReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除${description}")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "${description}ID",required = true)
    public CommonResult<Boolean> delete${entityName}(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = ${entityName?lower_case}Service.delete${entityName}(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取${description}")
    @Parameter(name = "id", description = "${description}ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<${entityName}Vo> get${entityName}(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(${entityName?lower_case}Service.get${entityName}VO(${entityName?lower_case}Service.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取${description}简要信息")
    @Parameter(name = "id", description = "${description}ID",required = true)
    public CommonResult<${entityName}SimpleVo> get${entityName}VO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        ${entityName} ${entityName?lower_case} = ${entityName?lower_case}Service.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(${entityName?lower_case}Service.getSimple${entityName}VO(${entityName?lower_case}));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取${description}列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<${entityName}Vo>> get${entityName}Page(${entityName}PageReqDTO ${entityName?lower_case}PageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(${entityName?lower_case}Service.get${entityName}Page(${entityName?lower_case}PageReqDTO));
    }

}