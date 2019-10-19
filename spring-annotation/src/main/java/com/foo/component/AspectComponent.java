package com.foo.component;


import com.sun.xml.internal.xsom.XSUnionSimpleType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面
 */
@Aspect
public class AspectComponent {

    @Pointcut("execution(public * com.foo.service.*.*(..))")
    public void pointCut() {

    }

    /**
     * 当前类
     */
    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }

    /**
     * 外部类
     */
    @Before("com.foo.component.AspectComponent.pointCut()")
    public void after() {
        System.out.println("after");
    }

    /**
     *
     * @param joinPoint 必须放在方法的first place
     * @param result 参数与注解中的名称做映射
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReture(JoinPoint joinPoint, int result) {
        System.out.println("afterReture targetMeth:" + joinPoint.getSignature().getName() + "  returnValue:" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "excepion")
    public void afterThrowing(JoinPoint joinPoint, Exception excepion) {
        System.out.println("afterThrowing targetMeth:" + joinPoint.getSignature().getName() + "  excepton:" + excepion);
    }
}
