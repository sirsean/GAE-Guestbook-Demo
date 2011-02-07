package guestbook.dao.jdo;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;

import com.vikinghammer.dao.BaseVHDao;
import com.vikinghammer.dao.VHQuery;

import guestbook.dao.GreetingDao;
import guestbook.domain.Greeting;

import org.springframework.stereotype.Repository;

@Repository
public class JdoGreetingDao extends BaseVHDao<Greeting> implements GreetingDao {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Greeting getLatestByAuthor(User author) {
        VHQuery query = new VHQuery(Greeting.class);
        query.setFilter("author == :author");
        query.setOrdering("date desc");
        return first(query, author);
    }

    @Override
    public List<Greeting> findAll() {
        String query = String.format(
            "select from %s",
            Greeting.class.getName()
        );
        return list(query);
    }

    @Override
    public List<Greeting> mostRecent(int count) {
        VHQuery query = new VHQuery(Greeting.class);
        query.setOrdering("date desc");
        query.setRange(0, count);
        return list(query);
    }

}
