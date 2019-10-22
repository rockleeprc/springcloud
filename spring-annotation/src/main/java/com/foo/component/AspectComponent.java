package com.foo.component;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Around(value="pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before around ");
        System.out.println("around "+pjp.getTarget().getClass());
        Object[] args = pjp.getArgs();
        System.out.println("around "+args[0]);
        Object result = pjp.proceed(args);
        System.out.println("result="+result);
        System.out.println("after around ");
        return result;
        /*
        @Around 一定要有返回值，不然报错，后续advice没法执行
         */
    }
}
