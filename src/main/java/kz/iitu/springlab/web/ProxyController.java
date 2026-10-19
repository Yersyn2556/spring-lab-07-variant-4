package kz.iitu.springlab.web;

import kz.iitu.springlab.service.CatalogService;
import org.springframework.aop.support.AopUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private final CatalogService catalogService;

    public ProxyController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/api/lab4/proxy")
    public String proxyInfo() {
        boolean proxy = AopUtils.isAopProxy(catalogService);
        boolean cglib = AopUtils.isCglibProxy(catalogService);

        return "isAopProxy=" + proxy
                + ", isCglibProxy=" + cglib
                + ", class=" + catalogService.getClass().getName();
    }
}