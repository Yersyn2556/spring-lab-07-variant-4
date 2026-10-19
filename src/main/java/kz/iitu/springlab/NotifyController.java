package kz.iitu.springlab;

import kz.iitu.springlab.lifecycle.LifecycleDemo;
import kz.iitu.springlab.notify.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NotifyController {

    private final NotificationService notificationService;
    private final LifecycleDemo lifecycleDemo;
    private final ContainerReport containerReport;

    public NotifyController(
            NotificationService notificationService,
            LifecycleDemo lifecycleDemo,
            ContainerReport containerReport) {

        this.notificationService = notificationService;
        this.lifecycleDemo = lifecycleDemo;
        this.containerReport = containerReport;
    }

    @GetMapping("/api/notify")
    public Map<String, Object> notify(
            @RequestParam(defaultValue = "Hello") String message) {

        return Map.of(
                "message", message,
                "primary", notificationService.send(message),
                "channels", notificationService.getNotifierMap().keySet()
        );
    }

    @GetMapping("/api/lab2/custom")
    public Map<String, Object> custom(
            @RequestParam(defaultValue = "") String text) {

        return Map.of(
                "input", text,
                "reversed", notificationService.sendReversed(text)
        );
    }

    @GetMapping("/api/lab2/lifecycle")
    public Map<String, Object> lifecycle() {

        return Map.of(
                "status", lifecycleDemo.getStatus(),
                "service", "NotificationService",
                "bean", "LifecycleDemo",
                "events", lifecycleDemo.getEvents()
        );
    }

    @GetMapping("/api/lab2/scopes")
    public Map<String, Object> scopes() {
        return notificationService.getScopes();
    }

    @GetMapping("/api/lab2/container-report")
    public Map<String, Object> containerReport() {
        return containerReport.report();
    }
}