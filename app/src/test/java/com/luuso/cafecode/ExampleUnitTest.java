package com.luuso.cafecode;

import com.luuso.cafecode.model.Event;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void isInstance() throws Exception {
        Event event1 = new Event();
        Event2 event2 = new Event2();

        boolean instance1 = Event.class.isInstance(event1);

        boolean instance2 = Event.class.isInstance(event2);
    }

    public static class Event2 extends Event {

    }
}