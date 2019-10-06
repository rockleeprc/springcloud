## IoC

* @Bean
* @Condition
* @Scope
* @ComponentScan
* @Import
  * xxx.class
  * ImportSelector
  * ImportBeanDefinitionRegistrar
* FactoryBean

* Bean声明周期
  * init-method：
    - singleton bean 先调用构造，在调用初始化方法
    - prototype bean IoC容器初始化时不调用init-method，每次获取bean时调用
  * destroy-method：
    - singleton bean 必须显示调用容器的close()，才会执行destroy()
    - prototype bean 容器不负责销毁
  * org.springframework.beans.factory.InitializingBean 初始化
  * org.springframework.beans.factory.DisposableBean 销毁
  * JSR250
    * @PostConstruct
    * @PreDestory
  * org.springframework.beans.factory.config.BeanPostProcessor 对象初始化前后调用

