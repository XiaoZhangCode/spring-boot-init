package ${basePackage}.model.dto.${entityName?lower_case};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${description}添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "${description}添加请求")
public class ${entityName}AddReqDTO extends ${entityName}BaseDTO{


}
