package com.foo.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.Arrays;
import java.util.List;

/**
 * 如果要对指定的服务使用特定的负载均衡配置，不能在@ComponentScan配置的包下
 * @RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
 */
public class MyRandomRule extends AbstractLoadBalancerRule {

    private int times = 0;
    private int machineNum = 0;

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            if (times < 5) {
                server = upList.get(machineNum);
                times++;
            } else {
                times = 0;
                machineNum++;
                if (machineNum >= upList.size()) {
                    machineNum = 0;
                }
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        List<String> upList = Arrays.asList("A", "B", "C", "D", "E");

        int times = 0;
        int currentHost = 0;
        for (int i = 0; i < 70; i++) {
            if (times < 5) {
                times++;
            } else {
                System.out.println("-----------------------");
                times = 0;
                currentHost++;
                if (currentHost >= 5) {
                    currentHost = 0;
                }
            }
            String server = upList.get(currentHost);
            System.out.println(server);
        }
    }
}
