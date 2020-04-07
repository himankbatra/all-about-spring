package guestbook.services;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter {
    public boolean isRateLimited(String ipAddress) {
        return false;
    }
}
