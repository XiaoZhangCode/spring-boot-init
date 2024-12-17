package ${basePackage}.controller;
<#-- 转换为小驼峰 -->
<#assign words = entityName?split("_")>
<#assign camelCaseEntityName = words[0]?lower_case + (words[1..]?map(w -> w?capitalize))?join("")>
<#-- 转换为大驼峰 -->
<#assign words = entityName?split("_")>
<#assign pascalCaseEntityName = words?map(w -> w?capitalize)?join("")>

import cn.dev33.satoken.annotation.SaCheckRole;
import ${basePackage}.common.pojo.CommonResult;
import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.constant.UserConstant;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}PageReqDTO;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}SaveReqDTO;
import ${basePackage}.model.entity.${pascalCaseEntityName};
import ${basePackage}.model.vo.${camelCaseEntityName}.${pascalCaseEntityName}SimpleVo;
import ${basePackage}.model.vo.${camelCaseEntityName}.${pascalCaseEntityName}Vo;
import ${basePackage}.service.${pascalCaseEntityName}Service;
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
@RequestMapping("/${camelCaseEntityName}")
public class ${pascalCaseEntityName}Controller {

    @Resource
    private ${pascalCaseEntityName}Service ${camelCaseEntityName}Service;

    /**
     * 创建${camelCaseEntityName}
     *
     * @param ${camelCaseEntityName}ReqDTO ${camelCaseEntityName}添加请求数据传输对象，包含新增${pascalCaseEntityName}的信息
     * @return 返回操作结果，其中包含新添加${camelCaseEntityName}的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建${description}")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> add${pascalCaseEntityName}(@RequestBody ${pascalCaseEntityName}SaveReqDTO ${camelCaseEntityName}ReqDTO) {
        if (${camelCaseEntityName}ReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = ${camelCaseEntityName}Service.add${pascalCaseEntityName}(${camelCaseEntityName}ReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新${description}信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> update${pascalCaseEntityName}(@RequestBody @Valid ${pascalCaseEntityName}SaveReqDTO ${camelCaseEntityName}ReqDTO) {
        // 检查传入的请求数据是否为空
        if (${camelCaseEntityName}ReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = ${camelCaseEntityName}Service.update${pascalCaseEntityName}(${camelCaseEntityName}ReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除${description}")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "${description}ID",required = true)
    public CommonResult<Boolean> delete${pascalCaseEntityName}(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = ${camelCaseEntityName}Service.delete${pascalCaseEntityName}(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取${description}")
    @Parameter(name = "id", description = "${description}ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<${pascalCaseEntityName}Vo> get${pascalCaseEntityName}(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(${camelCaseEntityName}Service.get${pascalCaseEntityName}VO(${camelCaseEntityName}Service.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取${description}简要信息")
    @Parameter(name = "id", description = "${description}ID",required = true)
    public CommonResult<${pascalCaseEntityName}SimpleVo> get${pascalCaseEntityName}VO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        ${pascalCaseEntityName} ${camelCaseEntityName} = ${camelCaseEntityName}Service.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(${camelCaseEntityName}Service.getSimple${pascalCaseEntityName}VO(${camelCaseEntityName}));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取${description}列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<${pascalCaseEntityName}Vo>> get${pascalCaseEntityName}Page(${pascalCaseEntityName}PageReqDTO ${camelCaseEntityName}PageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(${camelCaseEntityName}Service.get${pascalCaseEntityName}Page(${camelCaseEntityName}PageReqDTO));
    }

}