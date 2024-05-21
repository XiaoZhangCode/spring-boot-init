package ${basePackage}.service.impl;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.common.exception.ServiceException;
import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.mapper.${entityName}Mapper;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}AddReqDTO;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}PageReqDTO;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}UpdateReqDTO;
import ${basePackage}.model.entity.${entityName};
import ${basePackage}.model.vo.${entityName?lower_case}.${entityName}SimpleVo;
import ${basePackage}.model.vo.${entityName?lower_case}.${entityName}Vo;
import ${basePackage}.service.${entityName}Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ${basePackage}.common.exception.enums.GlobalErrorCodeConstants.*;
import static ${basePackage}.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【${entityName?lower_case}(${description}表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class ${entityName}ServiceImpl extends ServiceImpl<${entityName}Mapper, ${entityName}> implements ${entityName}Service {

    @Resource
    private ${entityName}Mapper ${entityName?lower_case}Mapper;

    /**
     * 添加新${description}
     *
     * @param ${entityName?lower_case}ReqDTO ${description}信息请求DTO
     * @return 添加成功返回${description}id
     */
    @Override
    public long add${entityName}(${entityName}AddReqDTO ${entityName?lower_case}ReqDTO) {
        ${entityName} ${entityName?lower_case} = new ${entityName}();
        BeanUtil.copyProperties(${entityName?lower_case}ReqDTO, ${entityName?lower_case});
        if (!this.save(${entityName?lower_case})) {
            throw exception(ADD_FAIL);
        }
        return ${entityName?lower_case}.getId();
    }

    /**
     * 更新${description}信息
     *
     * @param ${entityName?lower_case}ReqDTO ${description}信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean update${entityName}(${entityName}UpdateReqDTO ${entityName?lower_case}ReqDTO) {
        if (${entityName?lower_case}ReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        ${entityName} ${entityName?lower_case} = new ${entityName}();
        BeanUtil.copyProperties(${entityName?lower_case}ReqDTO, ${entityName?lower_case});
        boolean b = this.updateById(${entityName?lower_case});
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除${description}
     *
     * @param id ${description}id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean delete${entityName}(Long id) {
        if (id == null) {
            throw exception(BAD_REQUEST);
        }
        boolean b = this.removeById(id);
        if (!b) {
            throw exception(DELETE_FAIL);
        }
        return true;
    }

    /**
     * 将${description}对象转换为${description}VO对象
     *
     * @param ${entityName?lower_case} ${description}对象
     * @return 返回${description}VO对象
     */
    @Override
    public ${entityName}SimpleVo getSimple${entityName}VO(${entityName} ${entityName?lower_case}) {
        if (${entityName?lower_case} == null) {
            return null;
        }
        ${entityName}SimpleVo ${entityName?lower_case}SimpleVo = new ${entityName}SimpleVo();
        BeanUtil.copyProperties(${entityName?lower_case}, ${entityName?lower_case}SimpleVo);
        return ${entityName?lower_case}SimpleVo;
    }

    /**
     * 获取${description}分页信息
     *
     * @param ${entityName?lower_case}PageReqDTO ${description}分页请求DTO
     * @return 返回${description}分页结果
     */
    @Override
    public PageResult<${entityName}Vo> get${entityName}Page(${entityName}PageReqDTO ${entityName?lower_case}PageReqDTO) {
        PageResult<${entityName}> pageResult = ${entityName?lower_case}Mapper.selectPage(${entityName?lower_case}PageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<${entityName}Vo> ${entityName?lower_case}Vos = pageResult.getList().stream().map(${entityName?lower_case} -> {
            ${entityName}Vo ${entityName?lower_case}Vo = new ${entityName}Vo();
            BeanUtil.copyProperties(${entityName?lower_case}, ${entityName?lower_case}Vo);
            return ${entityName?lower_case}Vo;
        }).collect(Collectors.toList());
        return new PageResult<>(${entityName?lower_case}Vos, pageResult.getTotal());
    }

    @Override
    public ${entityName}Vo get${entityName}VO(${entityName} ${entityName?lower_case}) {
        if (${entityName?lower_case} == null) {
            return null;
        }
        ${entityName}Vo ${entityName?lower_case}Vo = new ${entityName}Vo();
        BeanUtil.copyProperties(${entityName?lower_case}, ${entityName?lower_case}Vo);
        return ${entityName?lower_case}Vo;
    }

}



