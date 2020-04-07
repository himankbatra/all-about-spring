package guestbook.services;


import guestbook.model.GuestbookEntry;
import guestbook.repositories.JpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GuestbookServiceTests {
    @Mock
    RateLimiter mockedRateLimiter;
    @Mock
    JpaRepository mockedJpaRepository;
    @Mock
    SpamChecker mockedSpamChecker;
    @InjectMocks
    GuestbookService guestbookService;

    @Test
    public void should_return_guestBookService_when_there_is_no_spam_and_no_rate_limiter() {
     /*  SpamChecker mockedSpamChecker = mock(SpamChecker.class);
       RateLimiter mockedRateLimiter = mock(RateLimiter.class);
       JpaRepository mockedJpaRepository = mock(JpaRepository.class);*/
        when(mockedRateLimiter.isRateLimited(anyString())).thenReturn(false);
        when(mockedSpamChecker.isSpam(anyString())).thenReturn(false);
//        GuestbookService guestbookService = new GuestbookService(mockedSpamChecker, mockedRateLimiter,
//                mockedJpaRepository);
        GuestbookEntry guestbookEntry = new GuestbookEntry("User 1", "Awesome");
        guestbookService.create(guestbookEntry, "10.0.0.0");
        verify(mockedJpaRepository).save(guestbookEntry);
    }

    @Test
    public void should_throw_exception_in_guestBookService_when_there_is_a_spam_and_no_rate_limiter() {
 /*      SpamChecker mockedSpamChecker = mock(SpamChecker.class);
       RateLimiter mockedRateLimiter = mock(RateLimiter.class);
       JpaRepository mockedJpaRepository = mock(JpaRepository.class);
*/
        when(mockedSpamChecker.isSpam(anyString())).thenReturn(true);
       /*GuestbookService guestbookService = new GuestbookService(mockedSpamChecker, mockedRateLimiter,
               mockedJpaRepository);*/
        GuestbookEntry guestbookEntry = new GuestbookEntry("User 1", "Awesome");
        try {
            guestbookService.create(guestbookEntry, "10.0.0.0");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
            assertThat(e.getMessage()).isEqualTo("Spam words in content");
        }
    }
}