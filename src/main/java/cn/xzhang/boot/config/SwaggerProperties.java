package cn.xzhang.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger 配置属性
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Component
@ConfigurationProperties(prefix = "info")
public class SwaggerProperties {


    private String title = "SpringBoot-Init";

    private String description = "SpringBoot-Init";

    private String version = "1.0.0";

    private String contactName = "codeZhang";

    private String contactEmail = "xiaozhang@qq.com";
}
