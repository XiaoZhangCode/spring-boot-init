package cn.xzhang.boot.generate;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;

import java.sql.*;
import java.util.*;

public class Generator {

    static String url = "jdbc:mysql://localhost:3306/my_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    static String username = "root";
    static String password = "123456";

    public static void main(String[] args) {
        // 生成文件的包名
        String basePackage = "cn.xzhang.boot";
        // 文件头描述
        String description = "用户";
        // 实体类的表名 大写
        String tableName = "user";
        // 生成的文件目录
        String path = "src/main/java/generated";

        // 将tableName 转换成大驼峰命名
        String entityName = StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
        // 将tableName 转换成小驼峰命名
        String entityNameLower = StrUtil.lowerFirst(entityName);


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("codeXiaozhang") // 设置作者
                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(path); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude(StrUtil.toUnderlineCase(tableName));
                    builder.entityBuilder()
                            // 取消生成Entity
                            .entityBuilder()
                            .disable()
                            .controllerBuilder()
                            .template("/templates/controller/Controller.java")
                            .serviceBuilder()
                            .serviceTemplate("/templates/service/EntityService.java")
                            .serviceImplTemplate("/templates/service/impl/EntityServiceImpl.java")
                            .formatServiceFileName(entityName + "Service")
                            .mapperBuilder()
                            .mapperTemplate("/templates/mapper/EntityMapper.java")
                            .mapperXmlTemplate("/templates/mapper/EntityMapper.xml")
                            .disable();

                })
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // entity/dto/vo
                    customFile.put("/model/entity/" + entityName + ".java", "/templates/model/entity/Entity.java.ftl");
                    customFile.put("/model/dto/" + entityNameLower + "/" + entityName + "SaveReqDTO.java", "/templates/model/dto/EntitySaveReqDTO.java.ftl");
                    customFile.put("/model/dto/" + entityNameLower + "/" + entityName + "PageReqDTO.java", "/templates/model/dto/EntityPageReqDTO.java.ftl");
                    customFile.put("/model/vo/" + entityNameLower + "/" + entityName + "SimpleVo.java", "/templates/model/vo/EntitySimpleVo.java.ftl");
                    customFile.put("/model/vo/" + entityNameLower + "/" + entityName + "Vo.java", "/templates/model/vo/EntityVo.java.ftl");

                    consumer.customFile(customFile);

                    // 自定义数据
                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("basePackage", basePackage);
                    customMap.put("description", description);
                    customMap.put("entityName", tableName);
                    List<ColumnMetadata> metaInfo = getTableColumnsMetadata(StrUtil.toUnderlineCase(tableName));
                    customMap.put("columns", metaInfo);

                    consumer.customMap(customMap);


                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用自定义的模板引擎
                .execute(); // 执行生成
    }

    public static List<ColumnMetadata> getTableColumnsMetadata(String tableName) {
        List<ColumnMetadata> columnsMetadata = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            // 扩展SQL查询以获取长度、精度、标度和默认值
            String sql = "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_KEY, COLUMN_COMMENT " +
                    "FROM INFORMATION_SCHEMA.COLUMNS " +
                    "WHERE TABLE_NAME = ? AND TABLE_SCHEMA = DATABASE()";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tableName.toUpperCase());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ColumnMetadata column = new ColumnMetadata();
                column.setColumnName(resultSet.getString("COLUMN_NAME"));
                column.setColumnType(resultSet.getString("DATA_TYPE"));
                column.setColumnComment(resultSet.getString("COLUMN_COMMENT"));

                // 注意：IS_NULLABLE 返回 'YES' 或 'NO'
                column.setNullable("YES".equals(resultSet.getString("IS_NULLABLE")));

                // 注意：COLUMN_KEY 返回 'PRI' 如果是主键
                column.setPrimaryKey("PRI".equals(resultSet.getString("COLUMN_KEY")));

                column.inferJavaType();
                columnsMetadata.add(column);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnsMetadata;
    }


    @Data
    public static class ColumnMetadata {
        private String columnName;
        private String columnType; // 数据库类型
        private String javaType; // Java类型
        private String columnComment; // 字段说明
        private boolean nullable; // 是否可为空
        private boolean primaryKey; // 是否为主键

        public boolean getNullable() {
            return nullable;
        }

        public boolean getPrimaryKey() {
            return primaryKey;
        }


        public void inferJavaType() {
            if (columnType != null) {
                columnType = columnType.toUpperCase();
                switch (columnType) {
                    case "VARCHAR":
                    case "CHAR":
                    case "TEXT":
                        javaType = "String";
                        break;
                    case "SMALLINT":
                        javaType = "Short";
                        break;
                    case "INTEGER":
                    case "INT":
                    case "TINYINT":
                        javaType = "Integer";
                        break;
                    case "BIGINT":
                        javaType = "Long";
                        break;
                    case "REAL":
                    case "FLOAT":
                        javaType = "Float";
                        break;
                    case "DOUBLE":
                        javaType = "Double";
                        break;
                    case "DECIMAL":
                    case "NUMERIC":
                        javaType = "java.math.BigDecimal";
                        break;
                    case "BOOLEAN":
                        javaType = "Boolean";
                        break;
                    case "DATE":
                    case "DATETIME":
                        javaType = "java.util.Date";
                        break;
                    case "TIMESTAMP":
                        javaType = "java.sql.Timestamp";
                        break;
                    case "TIME":
                        javaType = "java.util.Time";
                        break;
                    case "BINARY":
                    case "VARBINARY":
                    case "LONGVARBINARY":
                    case "BLOB":
                    case "IMAGE":
                        javaType = "byte[]";
                        break;
                    default:
                        // 对于未列出的类型，可以选择默认的处理方式，比如继续使用String，或者抛出异常等
                        javaType = "String";
                        break;
                }
            }
        }

    }


}
