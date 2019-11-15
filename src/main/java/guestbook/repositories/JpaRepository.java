package guestbook.repositories;

import guestbook.model.GuestbookEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class JpaRepository {

    private List<GuestbookEntry> db = new CopyOnWriteArrayList<>();

    public void save(GuestbookEntry guestbookEntry) {
        db.add(guestbookEntry);
    }

    public List<GuestbookEntry> findAll() {
        return new ArrayList<>(db);
    }

}

