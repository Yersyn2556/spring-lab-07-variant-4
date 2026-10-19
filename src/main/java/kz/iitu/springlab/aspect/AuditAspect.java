package kz.iitu.springlab.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class AuditAspect {

    @Around("@annotation(audited)")
    public Object audit(
            ProceedingJoinPoint pjp,
            Audited audited) throws Throwable {

        String method =
                pjp.getSignature().toShortString();

        String args = audited.logArguments()
                ? Arrays.toString(pjp.getArgs())
                : "[hidden]";

        LocalDateTime start = LocalDateTime.now();

        System.out.println(
                "[AUDIT] START action=" + audited.action()
                        + " method=" + method
                        + " args=" + args
                        + " time=" + start
        );

        try {
            Object result = pjp.proceed();

            System.out.println(
                    "[AUDIT] SUCCESS action="
                            + audited.action()
                            + " method=" + method
            );

            return result;

        } catch (Throwable ex) {

            System.out.println(
                    "[AUDIT] FAILURE action="
                            + audited.action()
                            + " method=" + method
                            + " exception="
                            + ex.getClass().getSimpleName()
            );

            throw ex;
        }
    }
}