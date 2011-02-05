package guestbook.dao;

import java.util.List;
import com.vikinghammer.dao.VHDao;
import guestbook.domain.Greeting;

public interface GreetingDao extends VHDao<Greeting> {

    public List<Greeting> findAll();

    public List<Greeting> mostRecent(int count);

}
