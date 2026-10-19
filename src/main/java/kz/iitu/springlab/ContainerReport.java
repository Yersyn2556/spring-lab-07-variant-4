package kz.iitu.springlab;

import kz.iitu.springlab.notify.Notifier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class ContainerReport {

    private final ApplicationContext context;
    private final Map<String, Notifier> notifierMap;
    private final DateTimeFormatter formatter;

    public ContainerReport(
            ApplicationContext context,
            Map<String, Notifier> notifierMap,
            @Qualifier("reportDateFormatter") DateTimeFormatter formatter) {

        this.context = context;
        this.notifierMap = notifierMap;
        this.formatter = formatter;
    }

    public Map<String, Object> report() {

        return Map.of(
                "beanDefinitionCount",
                context.getBeanDefinitionCount(),

                "notifierImplementations",
                notifierMap.size(),

                "containsDateTimeFormatter",
                context.containsBean("reportDateFormatter"),

                "dateFormatterClass",
                formatter.getClass().getName(),

                "notifierBeanNames",
                notifierMap.keySet()
        );
    }
}