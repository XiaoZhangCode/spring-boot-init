package cn.xzhang.boot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@AllArgsConstructor
@Getter
public enum UserStatusEnum {
    /**
     * 用户状态
     */
    NORMAL("正常", 0),

    DISABLE("停用", 1);

    private final String text;

    private final Integer value;

    /**
     * 根据数值获取对应的用户状态枚举。
     *
     * @param value 用户状态的数值表示。
     * @return 对应的用户状态枚举，如果找不到则返回null。
     */
    public static UserStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        // 遍历所有枚举值，查找数值匹配的枚举
        for (UserStatusEnum statusEnum : values()) {
            if (statusEnum.getValue().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }

    /**
     * 根据文本获取对应的用户状态枚举。
     *
     * @param text 用户状态的文本表示。
     * @return 对应的用户状态枚举，如果找不到则返回null。
     */
    public static UserStatusEnum getEnumByText(String text) {
        if (text == null) {
            return null;
        }
        // 遍历所有枚举值，查找文本匹配的枚举
        for (UserStatusEnum statusEnum : values()) {
            if (statusEnum.getText().equals(text)) {
                return statusEnum;
            }
        }
        return null;
    }

    /**
     * 判断给定的数值是否表示正常状态。
     *
     * @param value 用户状态的数值表示。
     * @return 如果给定数值表示正常状态，则返回true，否则返回false。
     */
    public static boolean isNormal(Integer value) {
        // 直接比较给定数值与NORMAL枚举的数值
        return NORMAL.getValue().equals(value);
    }

}
