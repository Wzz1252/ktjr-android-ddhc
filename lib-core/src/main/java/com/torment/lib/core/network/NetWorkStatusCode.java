package com.torment.lib.core.network;

public enum NetWorkStatusCode {
    NETWORK_SUCCESS("NETWORK_SUCCESS", 200, "成功"),
    NETWORK_SERVICE_EXCEPTION("NETWORK_SERVICE_EXCEPTION", 500, "服务器异常"),

    ACCOUNT_BEING_KICKED("NETWORK_SERVICE_EXCEPTION", 901, "该账号已在其它设备登录"),
    NETWORK_TOKEN_OVERDUE("NETWORK_SERVICE_EXCEPTION", 902, "用户信息过期，请重新登录"),

    CUSTOMER_IS_EXITS("CUSTOMER_IS_EXITS", 1001, "手机号存在"),
    CUSTOMER_IS_EXITS_UPDATE("CUSTOMER_IS_EXITS_UPDATE", 1002, "手机号存在"),
    CUSTOMER_IS_NOT_EXITS("CUSTOMER_IS_NOT_EXITS", 1003, "手机号不存在"),
    CUSTOMER_IS_EXITS_MINE("CUSTOMER_IS_EXITS_MINE", 1004, "该客户已保存在我的客户中");

    public String type;
    public int code;
    public String message;

    NetWorkStatusCode(String type, int code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }
}
