package cn.xzhang.boot;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@SpringBootApplication
@Slf4j
@EnableAdminServer
@MapperScan("cn.xzhang.boot.mapper")
public class SpringBootInitApplication {

    public static void main(String[] args) {
        // 不使用banner 解开下面操作
        // SpringApplication.run(SpringBootInitApplication.class, args);
        SpringApplication app = new SpringApplication(SpringBootInitApplication.class);
        Environment env = app.run(args).getEnvironment();
        app.setBannerMode(Banner.Mode.CONSOLE);
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/doc.html";
        } else {
            contextPath = contextPath + "/doc.html";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n" +
                        "\t应用程序\" {} \"正在运行中......\n" +
                        "\t接口文档访问 URL:\n" +
                        "\t本地: \t\t{}://localhost:{}{}\n" +
                        "\t外部: \t\t{}://{}:{}{}\n" +
                        "\t配置文件: \t{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                String.join(",", env.getActiveProfiles()));
    }
}
