package kz.iitu.springlab;

import kz.iitu.springlab.scope.PrototypeBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReverseController {

    private final ApplicationContext context;

    public ReverseController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/api/reverse")
    public Map<String, Object> reverse(
            @RequestParam(defaultValue = "") String text) {

        String reversed = new StringBuilder(text)
                .reverse()
                .toString();

        return Map.of(
                "reversed", reversed,
                "length", text.length()
        );
    }

    @GetMapping("/api/scope")
    public Map<String, Object> scopeTest() {

        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

        return Map.of(
                "sameObject", bean1 == bean2,
                "bean1", bean1.toString(),
                "bean2", bean2.toString()
        );
    }
}