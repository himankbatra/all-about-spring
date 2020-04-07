package guestbook.repositories;

import guestbook.model.GuestbookEntry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class JpaRepository {

    private List<GuestbookEntry> db = new CopyOnWriteArrayList<>();

    public JpaRepository() {
        this.db.addAll(Arrays.asList(new GuestbookEntry("User 1","Awesome!"),
                new GuestbookEntry("User 2","Awesome!"),
                new GuestbookEntry("User 3"," Himank Awesome!")));
    }

    public void save(GuestbookEntry guestbookEntry) {
        db.add(guestbookEntry);
    }

    public List<GuestbookEntry> findAll() {
        return new ArrayList<>(db);
    }

}

