package guestbook.dao.jdo;

import java.util.List;
import java.util.logging.Logger;

import com.vikinghammer.dao.BaseVHDao;

import guestbook.dao.GreetingDao;
import guestbook.domain.Greeting;

import org.springframework.stereotype.Repository;

@Repository
public class JdoGreetingDao extends BaseVHDao<Greeting> implements GreetingDao {
    private final Logger log = Logger.getLogger(getClass().getName());

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
        String query = String.format(
            "select from %s order by date desc range 0,%s",
            Greeting.class.getName(),
            count
        );
        return list(query);
    }

}
