package guestbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class SpamChecker {

    private Set<String> spamWords;

    @Autowired
    public SpamChecker() {
        this.spamWords = new HashSet<>();
        this.spamWords.addAll(Arrays.asList("acne", "adult", ""));
    }

    public boolean isSpam(String content) {
        Set<String> words = new HashSet<>(Arrays.asList(content.split("\\s")));
        if (!Collections.disjoint(spamWords, words)) {
            return true;
        }
        return false;
    }
}
