package kz.iitu.springlab.notify;

import jakarta.annotation.PostConstruct;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("reversed")
@Order(3)
public class ReversedNotifier implements Notifier {

    @PostConstruct
    public void init() {
        System.out.println("ReversedNotifier created");
    }

    @Override
    public String send(String message) {
        return new StringBuilder(message)
                .reverse()
                .toString();
    }

    @Override
    public String channel() {
        return "reversed";
    }
}