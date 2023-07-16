package stark.reshaper.images.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthController
{
    @GetMapping("/hello")
    public String sayHello()
    {
        log.info("Enter sayHello()...");
        return "Hello";
    }
}
