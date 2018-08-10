package com.yuneke.enums;


import com.yuneke.interfaces.common.Code;

public class AppDebugLogEnum {

    public static enum Type implements Code {
        PUSH_1(1,"接收到推送"),
        PUSH_2(2,"获取GetPush接口前"),
        PUSH_3(3,"获取GetPush接口后"),
        PUSH_4(4,"服务器错误"),
        PUSH_5(5,"POS保存订单数据"),
        PUSH_6(6,"POS弹出提示消息");
        private final Integer code;
        private final String msg;

        private Type(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
