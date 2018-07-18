package com.com.example;

import com.example.SpringBootAppApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.directory.Attribute;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MOCK ： 加载一个WebApplicationContext并提供一个模拟servlet环境。嵌入式servlet容器在使用此注释时不会启动。如果servlet API不在你的类路径上，这个模式将透明地回退到创建一个常规的非web应用程序上下文。可以与@AutoConfigureMockMvc结合使用，用于基于MockMvc的应用程序测试。
 * RANDOM_PORT ： 加载一个EmbeddedWebApplicationContext并提供一个真正的servlet环境。嵌入式servlet容器启动并在随机端口上侦听。
 * DEFINED_PORT ： 加载一个EmbeddedWebApplicationContext并提供一个真正的servlet环境。嵌入式servlet容器启动并监听定义的端口（即从application.properties或默认端口8080）。
 * NONE ： 使用SpringApplication加载ApplicationContext，但不提供任何servlet环境（模拟或其他）。
 * 1、如果你的测试是@Transactional，默认情况下它会在每个测试方法结束时回滚事务。 但是，由于使用RANDOM_PORT或DEFINED_PORT这种安排隐式地提供了一个真正的servlet环境，所以HTTP客户端和服务器将在不同的线程中运行，从而分离事务。 在这种情况下，在服务器上启动的任何事务都不会回滚。
 * 2、@SuiteClasses({ATest.class, BTest.class, CTest.class})  加载其它测试类一起执行
 * 3、@ActiveProfiles(profiles = "test") 在测试类上面指定profiles，可以改变当前spring 的profile，来达到多环境的测试
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="prd")
@SpringBootTest(classes = SpringBootAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest1 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testInfo() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user/info",
                String.class)).contains("hello");

    }


    @Test
    public void contextLoads() throws Exception {
    }

}