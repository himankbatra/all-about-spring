package guestbook.services;

import guestbook.model.GuestbookEntry;
import guestbook.repositories.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GuestbookService {

    private SpamChecker spamChecker;
    private RateLimiter rateLimiter;
    private JpaRepository jpaRepository;

    @Autowired
    public GuestbookService(SpamChecker spamChecker,
                            RateLimiter rateLimiter,
                            JpaRepository jpaRepository) {
        this.spamChecker = spamChecker;
        this.rateLimiter = rateLimiter;
        this.jpaRepository = jpaRepository;
    }

    public void create(GuestbookEntry guestbookEntry, String ipAddress) {
        if (spamChecker.isSpam(guestbookEntry.getContent())) {
            throw new RuntimeException("Spam words in content");
        }

        if (rateLimiter.isRateLimited(ipAddress)) {
            throw new RuntimeException("Ip Address is rate limited");
        }

        jpaRepository.save(guestbookEntry);
    }

    public List<GuestbookEntry> findAll() {

        return jpaRepository.findAll();
    }
}
