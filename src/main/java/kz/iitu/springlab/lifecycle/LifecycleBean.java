package kz.iitu.springlab.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {

    @PostConstruct
    public void init() {
        System.out.println("LifecycleBean: bean created");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("LifecycleBean: bean destroyed");
    }
}