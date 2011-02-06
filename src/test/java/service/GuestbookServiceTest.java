package service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;

import org.hamcrest.Factory;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import org.jmock.Mockery;
import org.jmock.Expectations;

import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;

import java.util.Date;

import guestbook.service.GuestbookService;

import guestbook.domain.Greeting;
import guestbook.dao.GreetingDao;

@RunWith(JMock.class)
public class GuestbookServiceTest {
    Mockery context = new JUnit4Mockery();

    GuestbookService guestbookService;

    GreetingDao greetingDao;

    @Before
    public void setupMocks() {
        greetingDao = context.mock(GreetingDao.class);

        guestbookService = new GuestbookService(
            greetingDao
        );
    }

    @Test
    public void addGreeting() throws Exception {
        final Greeting greeting = new Greeting(null, "content", new Date());

        context.checking(new Expectations() {{
            oneOf(greetingDao).store(greeting);
        }});

        guestbookService.addGreeting(greeting);
    }

}
