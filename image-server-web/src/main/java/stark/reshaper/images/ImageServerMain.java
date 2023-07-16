package stark.reshaper.images;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"stark.reshaper.images", "stark.dataworks.boot.autoconfig"})
@EnableDubbo
@EnableDiscoveryClient
public class ImageServerMain
{
    public static void main(String[] args)
    {
        SpringApplication.run(ImageServerMain.class);
    }
}
