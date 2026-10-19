package kz.iitu.springlab.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LifecycleDemo {

    private final List<String> events = new ArrayList<>();

    public LifecycleDemo() {
        events.add("constructor");
    }

    @PostConstruct
    public void init() {
        events.add("postConstruct");
        System.out.println("LifecycleDemo: @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        events.add("preDestroy");
        System.out.println("LifecycleDemo: @PreDestroy");
    }

    public List<String> getEvents() {
        return events;
    }

    public String getStatus() {
        return "initialized";
    }
}