package kz.iitu.springlab;

import kz.iitu.springlab.notify.NotificationService;
import kz.iitu.springlab.scope.PrototypeBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringLab01Application {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(SpringLab01Application.class, args);

        NotificationService notificationService =
                context.getBean(NotificationService.class);

        System.out.println(
                notificationService.send("Spring application started")
        );

        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

        System.out.println("bean1 == bean2: " + (bean1 == bean2));
    }
}