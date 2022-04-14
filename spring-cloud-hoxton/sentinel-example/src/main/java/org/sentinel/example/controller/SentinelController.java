package org.sentinel.example.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sentinel")
public class SentinelController {
    @Value("${server.port}")
    private Integer port;

    private static final String RESOURCE_NAME = "circuitBreaker";

    @SentinelResource(value = RESOURCE_NAME, blockHandler = "circuitBreakerBlockHandler")
    @RequestMapping("/circuitBreaker2")
    public String circuitBreaker2(Integer i) {
        log.info("circuitBreaker");
        if (i < 0) {
            throw new RuntimeException("抛异常");
        }
        return i + "|" + port;
    }

    /**
     * 流控方法
     *
     * @param i
     * @param e
     * @return
     */
    public String circuitBreakerBlockHandler(Integer i, BlockException e) {
        return i + "-circuitBreakerBlockHandler";
    }


    @RequestMapping("/circuitBreaker1")
    public String circuitBreaker1(Integer i) {
        Entry entry = null;
        try {
            // 资源名称通常和接口名称保持一致
            entry = SphU.entry(RESOURCE_NAME);
            log.info("circuitBreaker");
            if (i < 0) {
                throw new RuntimeException("抛异常");
            }
        } catch (BlockException e) {
            e.printStackTrace();
            return "异常";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return i + "|" + port;
    }

    /**
     * 初始化防护规则
     */
    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule flowRule = new FlowRule();
        flowRule.setResource(RESOURCE_NAME);
        // 设置防护规则
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(1); // 设置阈值
        rules.add(flowRule);

        // 加载配置规则
        FlowRuleManager.loadRules(rules);
        log.info("initFlowRules 执行");
    }
}
