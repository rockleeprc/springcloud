package com.foo.config;

import com.foo.bean.Person;
import com.foo.component.MacCondition;
import com.foo.component.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/*
includeFilters：默认包含@Controller、@Service、@Repository、@Componet，要自定义时需要先禁用掉默认规则 useDefaultFilters = false，配置才会生效
@ComponentScan.Filter：默认的过滤类型为FilterType.ANNOTATION可以自定
 */
@Configuration//声明这是一个配置类，等同于.xml文件
//@ComponentScan(basePackages = "com.foo", includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes={MyTypeFilter.class})}, useDefaultFilters = false)
//@ComponentScans({
//        @ComponentScan(basePackages = "com.foo", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}, useDefaultFilters = false)
//})
//@ComponentScan(value="com.foo",includeFilters ={@ComponentScan.Filter(classes = {Controller.class})},useDefaultFilters =false)
@ComponentScan(value="com.foo",excludeFilters = {@ComponentScan.Filter(classes={Controller.class})})
public class BeanConfig {

    /*
     对象作用域：
        单例：IoC容器初始化时实例化对象
        多例：IoC容器初始化时不会创建对象，获取对象时才初始化对象
     单例bean懒加载：@Lazy，IoC容器初始化时不加载bean、调用时初始化单例bean
     */
    @Bean("per")//<bean id="person" class="com.foo.Person" scope="">
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person person() {
        return new Person("苍老师", 20);
    }

    @Conditional(WindowsCondition.class)
    @Bean
    public Person bill(){
       return new Person("bill",30);
    }

    @Conditional(MacCondition.class)
    @Bean
    public Person jobs(){
        return new Person("jobs",40);
    }
}
