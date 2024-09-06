package cn.xzhang.boot.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自定义元对象处理器
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();

            // 获取当前时间
            DateTime current = DateUtil.date();

            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }

            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
            long userId;
            try {
                userId = StpUtil.getLoginIdAsLong();
            }catch (Exception e){
                userId = 1L;
            }
            if (Objects.isNull(baseDO.getCreator())) {
                baseDO.setCreator(Long.toString(userId));
            }
            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
            if (Objects.isNull(baseDO.getUpdater())) {
                baseDO.setUpdater(Long.toString(userId));
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", DateUtil.date(), metaObject);
        }

        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        Object modifier = getFieldValByName("updater", metaObject);
        long userId;
        try {
            userId = StpUtil.getLoginIdAsLong();
        }catch (Exception e){
            userId = 1L;
        }
        if (Objects.isNull(modifier)) {
            setFieldValByName("updater", Long.toString(userId), metaObject);
        }
    }
}