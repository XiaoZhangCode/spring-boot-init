package cn.xzhang.boot.core.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 权限校验
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userService.getById((Serializable) loginId);
        if (user != null) {
            return Collections.singletonList(user.getUserRole());
        }
        return null;
    }
}
