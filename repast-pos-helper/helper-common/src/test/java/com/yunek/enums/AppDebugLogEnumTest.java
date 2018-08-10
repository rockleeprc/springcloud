package com.yunek.enums;

import com.yuneke.enums.AppDebugLogEnum;
import org.junit.Test;

import java.util.Arrays;

public class AppDebugLogEnumTest {

    @Test
    public void t2(){
        AppDebugLogEnum.Type[] values = AppDebugLogEnum.Type.values();
        System.out.println(Arrays.toString(values));
        for (AppDebugLogEnum.Type type:values) {
            System.out.println(type.getCode()+"-"+type.getMsg());
        }
    }

    @Test
    public void t1() {
        System.out.println(AppDebugLogEnum.Type.PUSH_1.getCode());
        System.out.println(AppDebugLogEnum.Type.PUSH_1.getCode());

        System.out.println(AppDebugLogEnum.Type.PUSH_1);
        System.out.println(AppDebugLogEnum.Type.PUSH_2);

        System.out.println(AppDebugLogEnum.Type.PUSH_1.getMsg());
        System.out.println(AppDebugLogEnum.Type.PUSH_2.getMsg());
    }
}
