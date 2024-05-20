package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.user.UserPageReqDTO;
import cn.xzhang.boot.model.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface UserMapper extends BaseMapperPlus<User> {

    default PageResult<User> selectPage(UserPageReqDTO userPageReqDTO) {
        return selectPage(userPageReqDTO, new LambdaQueryWrapper<User>()
                .eq(Objects.nonNull(userPageReqDTO.getId()), User::getId, userPageReqDTO.getId())
                .eq(Objects.nonNull(userPageReqDTO.getUserName()), User::getUserName, userPageReqDTO.getUserName())
                .eq(Objects.nonNull(userPageReqDTO.getUserRole()), User::getUserRole, userPageReqDTO.getUserRole())
                .eq(Objects.nonNull(userPageReqDTO.getMpOpenId()), User::getMpOpenId, userPageReqDTO.getMpOpenId())
                .eq(Objects.nonNull(userPageReqDTO.getUnionId()), User::getUnionId, userPageReqDTO.getUnionId()));
    }

}




