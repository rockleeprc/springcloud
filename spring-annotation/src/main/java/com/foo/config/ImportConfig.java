package com.foo.config;

import com.foo.bean.Blue;
import com.foo.bean.Person;
import com.foo.bean.Yellow;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

@Configuration
@ComponentScan(value = "com.foo", excludeFilters = {@ComponentScan.Filter(value = {Configuration.class})})
@Import({Blue.class, ImportConfig.ImportSelect.class, ImportConfig.ImportBean.class})
public class ImportConfig {


    @Bean
    public PersonFactoryBean personFactoryBean() {
       return new PersonFactoryBean();
    }

    public static class PersonFactoryBean implements FactoryBean<Person> {

        @Override
        public Person getObject() throws Exception {
            return new Person("FactoryBean",11);
        }

        @Override
        public Class<?> getObjectType() {
            return Person.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }

    public static class ImportSelect implements ImportSelector {

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{"com.foo.bean.Red"};
        }
    }

    public static class ImportBean implements ImportBeanDefinitionRegistrar {

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            boolean hasBlue = registry.containsBeanDefinition("com.foo.bean.Blue");
            boolean hasRed = registry.containsBeanDefinition("com.foo.bean.Red");
            if (hasBlue && hasRed) {
                RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Yellow.class);
                registry.registerBeanDefinition("yellow", rootBeanDefinition);
            }
        }
    }


}
