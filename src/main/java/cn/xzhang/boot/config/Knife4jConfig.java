package cn.xzhang.boot.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger 配置
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Configuration
@ConditionalOnClass({OpenAPI.class})
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Knife4jConfig {

    @Bean
    public OpenAPI springShopOpenApi(SwaggerProperties swaggerProperties) {
        return new OpenAPI()
                .info(new Info().title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion())
                        .contact(new Contact().name(swaggerProperties.getContactName())
                                .email(swaggerProperties.getContactEmail())));
    }


}
