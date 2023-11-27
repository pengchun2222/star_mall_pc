package com.ququ.star.auth.constent;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:32
 */
public enum UserStatusConstant {
    NORMAL(0, "正常"),
    FROZEN(1, "冻结");
    private Integer value;
    private String desc;

    private UserStatusConstant(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
