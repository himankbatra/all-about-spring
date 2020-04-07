package guestbook.config;

import guestbook.repositories.JpaRepository;
import guestbook.services.GuestbookService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {GuestbookService.class,
        JpaRepository.class})
public class ApplicationConfig {
}
