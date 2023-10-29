package com.mpei;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TripletDeque<T> implements Deque<T>, Containerable {

    private MyContainer<T> firstContainer;
    private MyContainer<T> lastContainer;

    private final int volume = 1000;
    private final int capacity = 5;

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        MyContainer<T> ref = firstContainer;
        int counters = 0;
        while (cIndex != counters) {
            if(ref.next ==null){
                return null;
            }
            ref = ref.next;
            counters++;
        }
        return ref.data;
    }

    private class MyContainer<T> {
        MyContainer<T> next; // -link to next container
        MyContainer<T> prev; // -link to previous container
        private int capacity, iniIndex, lastIndex;
        private Object[] data;

        public MyContainer(int capacity) {
            this.capacity = capacity;
            this.data = new Object[capacity];

        }

        public MyContainer() {
            this(5);
        }

        public boolean addFirst(T object) {
            if (firstContainer.data[0] != null) {
                return false;
            }
            else {
                for (int i = 0; i < capacity-1; i++) {
                    if (firstContainer.data[i+1] != null) {
                        firstContainer.data[i] = object;
                        firstContainer.iniIndex--;
                        return true;
                    }

                }
                        firstContainer.data[capacity-1] = object;
                        firstContainer.iniIndex = capacity;
                        firstContainer.lastIndex = capacity;
                        return true;                
            }
        }

        public boolean addLast(T object) {
            if (lastContainer.data[capacity-1] != null) {
                return false;
            }
            else{
                for (int i = capacity - 1; i >= 0; i--) {
                    if (this.data[i] != null) {
                        data[i+1] = object;
                        this.lastIndex++;
                        return true;
                    }
            }
        }
                    this.data[0] = object;
                    this.iniIndex = 1;
                    this.lastIndex = 1;
                    return true; 
        }

        public int getDataCount() {
            int n = lastIndex - iniIndex + 1;
            return n;
        }
    }

    @Override
    public void addFirst(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        // if (size() > volume) {
        //     throw new IllegalStateException();
        // }
        if (firstContainer == null) {
            firstContainer = new MyContainer<>();
            lastContainer = firstContainer;
        }
        boolean res = firstContainer.addFirst(t);
        if (!res) {
            MyContainer<T> newFirstContainer = new MyContainer<>();
            newFirstContainer.next = firstContainer; // -giving link to next container for new one(to the
                                                     // right-previous)
            firstContainer.prev = newFirstContainer; // -giving link to prev container for old one(to the left-added)
            firstContainer = newFirstContainer; // -changing value for first container
            firstContainer.addFirst(t); // -adding value to new first container
        }

    }

    @Override
    public void addLast(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        // if (size() > volume) {
        //     throw new IllegalStateException();
        // }
        if (lastContainer == null) {
            firstContainer = new MyContainer<>();
            lastContainer = firstContainer;
        }
        boolean res = lastContainer.addLast(t);
        if (!res) {
            MyContainer<T> newLastContainer = new MyContainer<>();
            newLastContainer.prev = lastContainer;
            lastContainer.next = newLastContainer;
            lastContainer = newLastContainer;
            this.addLast(t);
        }
    }

    @Override
    public boolean offerFirst(T t) {
        try {
            addFirst(t);
        } catch (IllegalStateException i) {
            return false;
        }
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        try {
            addLast(t);
        } catch (IllegalStateException i) {
            return false;
        }
        return true;
    }

    @Override
    public T removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            return pollFirst();
        }
    }

    @Override
    public T removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            return pollLast();
        }
    }

    @Override
    public T pollFirst() {
        T poll;
        poll = (T) firstContainer.data[firstContainer.iniIndex - 1];
        firstContainer.data[firstContainer.iniIndex - 1] = null;
        if (firstContainer.getDataCount() == 1 && firstContainer.next != null) {
            firstContainer = firstContainer.next;
        } else {
            firstContainer.iniIndex++;
        }
        return poll;
    }

    @Override
    public T pollLast() {
        T poll;
        poll = (T) lastContainer.data[lastContainer.lastIndex - 1];
        this.lastContainer.data[lastContainer.lastIndex - 1] = null;
        if (lastContainer.getDataCount() == 1 && firstContainer.prev != null) {
            lastContainer = lastContainer.prev;
        } else {
            lastContainer.lastIndex--;
        }
        return poll;
    }

    @Override
    public T getFirst() {
        // if(size() == 0){
        // return null;
        // }
        Object datum = this.firstContainer.data[this.firstContainer.iniIndex - 1];
        return (T)datum;
        // return (T)firstContainer.data[firstContainer.iniIndex - 1];
    }

    @Override
    public T getLast() {
        // if(size() == 0){
        // return null;
        // }
        Object datum = this.lastContainer.data[this.lastContainer.lastIndex - 1];
        return (T) datum;

    }

    @Override
    public T peekFirst() {

        return getFirst();

    }

    @Override
    public T peekLast() {

        return getLast();

    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        MyContainer<T> checker = firstContainer;
        if (getFirst().equals(o)) {
            removeFirst();
            return true;
        } else {
            while (checker != null) {
                for (int i = 0; i < checker.getDataCount(); i++) {
                    if (checker.data[i] != null) {
                        if (checker.data[i].equals(o)) {
                            checker.data[i] = removeFirst();
                            return true;
                        }
                    }
                }
                checker = checker.next;
            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        MyContainer<T> checker = lastContainer;
        if (getFirst().equals(o)) {
            removeLast();
            return true;
        } else {
            while (checker != null) {
                for (int i = checker.getDataCount() - 1; i >= 0; i--) {
                    if (checker.data[i] != null) {
                        if (checker.data[i].equals(o)) {
                            checker.data[i] = removeLast();
                            return true;
                        }
                    }
                }
                checker = checker.prev;
            }
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return false;
    }

    @Override
    public boolean offer(T t) {
        try {
            addLast(t);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        try {
            return removeFirst();
        } catch (NoSuchElementException t) {
            return null;
        }
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        try {
            return getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        lastContainer = null;
        firstContainer = null;
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : this) {
            if (t.equals((T) o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        int size = 0;
        MyContainer<T> current = firstContainer;
        size += current.getDataCount();
        while (current.next != null) {
            current = current.next;
            size += current.getDataCount();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itor();
    }
    private class Itor implements Iterator<T> {
        int cursor;
        MyContainer<T> curretCont = firstContainer;
        MyContainer<T> retCont;
        int dequeCursor;
        int lastRet = -1; // index of last element returned; -1 if no such
        @Override
        public boolean hasNext() {
            return dequeCursor < size() && size()!=0;
        }
        @Override
        public T next() {
            // TODO Auto-generated method stub
            if(hasNext()){
                if(cursor == 0 && curretCont == firstContainer){
                    while(curretCont.data[cursor]==null){
                        cursor++;
                    }
                }
                lastRet = cursor;
                retCont = curretCont;
                if(cursor == capacity-1){
                    curretCont = curretCont.next;
                    cursor =0;
                    dequeCursor++;
                } else{
                    cursor++;
                    dequeCursor++;
                }
                return (T)retCont.data[lastRet];
            }
            else{
                throw new NoSuchElementException();
            }
            // throw new UnsupportedOperationException("Unimplemented method 'next'");
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (T elem : this) {
            array[i] = elem;
            i++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        throw new UnsupportedOperationException("Не реализованный");
    }
}
