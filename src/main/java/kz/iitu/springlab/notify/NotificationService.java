package kz.iitu.springlab.notify;

import kz.iitu.springlab.scope.PrototypeDemo;
import kz.iitu.springlab.scope.SingletonDemo;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NotificationService {

    private final Notifier primaryNotifier;
    private final Notifier reversedNotifier;
    private final List<Notifier> notifiers;
    private final Map<String, Notifier> notifierMap;

    private final SingletonDemo singletonDemo;
    private final ObjectProvider<PrototypeDemo> prototypeProvider;

    public NotificationService(
            Notifier primaryNotifier,
            @Qualifier("reversed") Notifier reversedNotifier,
            List<Notifier> notifiers,
            Map<String, Notifier> notifierMap,
            SingletonDemo singletonDemo,
            ObjectProvider<PrototypeDemo> prototypeProvider) {

        this.primaryNotifier = primaryNotifier;
        this.reversedNotifier = reversedNotifier;
        this.notifiers = notifiers;
        this.notifierMap = notifierMap;
        this.singletonDemo = singletonDemo;
        this.prototypeProvider = prototypeProvider;
    }

    public String send(String message) {
        return primaryNotifier.send(message);
    }

    public String sendReversed(String message) {
        return reversedNotifier.send(message);
    }

    public List<Notifier> getNotifiers() {
        return notifiers;
    }

    public Map<String, Notifier> getNotifierMap() {
        return notifierMap;
    }

    public Map<String, Object> getScopes() {

        PrototypeDemo prototype1 = prototypeProvider.getObject();
        PrototypeDemo prototype2 = prototypeProvider.getObject();

        return Map.of(
                "singletonSame",
                singletonDemo == singletonDemo,

                "singletonId",
                System.identityHashCode(singletonDemo),

                "prototypeSame",
                prototype1 == prototype2,

                "prototypeId1",
                System.identityHashCode(prototype1),

                "prototypeId2",
                System.identityHashCode(prototype2)
        );
    }
}