package ru.mpei.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mpei.Containerable;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class MyTripletQueueImplTest {
    private Deque<String> myImpl = new MyTripletQueueImpl<>();
    private Containerable c = (Containerable) myImpl;
    @Test
    void addFirst() {

        myImpl.addLast("One");
        Assertions.assertEquals("One", myImpl.getLast());
        myImpl.addLast("two");
        Assertions.assertEquals("two", myImpl.getLast());

        myImpl.addLast("three");
        myImpl.addLast("four");
        myImpl.addLast("five");
        myImpl.addLast("six");
        Assertions.assertEquals("six", myImpl.getLast());

        Object[] fContainer = c.getContainerByIndex(1);

        Assertions.assertEquals("six", fContainer[0]);


    }
}