package guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.users.User;

import guestbook.dao.GreetingDao;
import guestbook.domain.Greeting;

@Service
public class GuestbookService {

    @Autowired
    private GreetingDao greetingDao;

    public GuestbookService() {
        super();
    }

    public GuestbookService(
        GreetingDao greetingDao
    ) {
        super();
        this.greetingDao = greetingDao;
    }

    public void addGreeting(Greeting greeting) {
        this.greetingDao.store(greeting);
    }

    public List<Greeting> findAllGreetings() {
        return this.greetingDao.findAll();
    }

    public List<Greeting> recentGreetings(int count) {
        return this.greetingDao.mostRecent(count);
    }

    public Greeting getLatestByAuthor(User author) {
        return greetingDao.getLatestByAuthor(author);
    }
}
