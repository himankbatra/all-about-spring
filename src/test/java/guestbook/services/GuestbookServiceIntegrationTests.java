package guestbook.services;


import guestbook.config.ApplicationConfig;
import guestbook.model.GuestbookEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class GuestbookServiceIntegrationTests {
    @Autowired
    private GuestbookService guestbookService;

    @Test
    public void bean_is_not_null() {
        assertThat(guestbookService).isNotNull();
    }

    @Test
    public void should_create_db() {
        GuestbookEntry guestbookEntry = new GuestbookEntry("Himank", "Awesome");
        guestbookService.create(guestbookEntry, "10.0.0.1");
        List<GuestbookEntry> entries = guestbookService.findAll();
        assertThat(entries).hasSize(4);
    }
}