<<<<<<< Updated upstream:deque/src/main/java/com/mpei/TripletDeque.java
package com.mpei;
=======

>>>>>>> Stashed changes:Labs/Lab2/deque/src/main/java/com/deque/TripletDeque.java

import java.util.*;

public class TripletDeque<E> implements Deque<E>, Containerable {
    
//Link to first container
private Container<E> first;
//Link to last container
    private Container<E> last;
//Capacity of container
    private int ContCapacity;
//Number of objects in queue
    private int VolumeOfQueue;
//Number of elements
    private int NumberOfElements;


    private class Container<E> {

//Link to next container
        private Container<E> next;

//Link to previous container
        private Container<E> prev;

//Array of elements inside
        private Object[] ElementConsist;

//Making containers with links
        public Container(int ContCapacity, Container<E> prev, Container<E> next) {
            this.prev = prev;
            this.next = next;
            this.ElementConsist = new Object[ContCapacity];
        }

//Containers size with elements
        public int size() {
            int size = 0;
            for (Object o : ElementConsist) {
                if (o != null) {
                    size++;
                }
            }
            return size;
        }

//Adds element to the end of deque. Returns true if added, exception if false
        public void add(Object o) {
            if (o != null) {
                int i = 0;
                boolean Stat = true;
                while (Stat) {
                    if (ElementConsist[i] == null) {
                        ElementConsist[i] = o;
                        Stat = false;
                    }
                    i++;
                }
            }
        }

        public void addAll(E[] elements) {
            for (E e : elements) {
                add(e);
            }
        }

        public E get(int index) {
            return (E) ElementConsist[index];
        }

        public void printContainer() {
            System.out.println(Arrays.toString(ElementConsist) + " ");
        }

        public Container<E> getNext() {
            return next;
        }

        public void setNext(Container<E> next) {
            this.next = next;
        }

        public Container<E> getPrev() {
            return prev;
        }

        public void setPrev(Container<E> prev) {
            this.prev = prev;
        }

        public E[] getElementConsist() {
            return (E[]) ElementConsist;
        }
    }


    public TripletDeque() {
        Container<E> container = new Container<>(5, null, null);
        this.first = container;
        this.last = container;
        this.ContCapacity = 5;
        this.VolumeOfQueue = 1000;
        this.NumberOfElements = 0;
    }


    public TripletDeque(int ContCapacity, int VolumeOfQueue) {
        Container<E> container = new Container<>(ContCapacity, null, null);
        this.first = container;
        this.last = container;
        this.ContCapacity = ContCapacity;
        this.VolumeOfQueue = VolumeOfQueue;
        this.NumberOfElements = 0;
    }


    @Override
    public Object[] getContainerByIndex(int cIndex) {
        int i = 0;
        for (Container<E> x = first; x != null; x = x.getNext()) {
            if (i == cIndex) {
                return x.getElementConsist();
            }
            i++;
        }
        return null;
    }


//Adds element to the beginning. True if done, exception if not.
    @Override
    public void addFirst(E e) {
        checkNumberOfElements();
        checkNullInputParameter(e);
        if (first.size() < ContCapacity) {
            Container<E> box = new Container<>(ContCapacity, null, first.getNext());
            box.add(e);
            box.addAll(first.getElementConsist());
            if (first.equals(last)) {
                last = box;
            }
            if (first.getNext() != null) {
                first.getNext().setPrev(box);
            }
            first = box;
            NumberOfElements++;
        } else {
            Container<E> box = new Container<>(ContCapacity, null, first);
            first.setPrev(box);
            first = box;
            addFirst(e);
        }
    }


//Adds element to the end. True if added, exception if not.~add().
    @Override
    public void addLast(E e) {
        checkNumberOfElements();
        checkNullInputParameter(e);
        if (last.size() < ContCapacity) {
            last.add(e);
            NumberOfElements++;
        } else {
            Container<E> box = new Container<>(ContCapacity, last, null);
            last.setNext(box);
            last = box;
            addLast(e);
        }
    }


//Adds element to the beginning, without exception(false).~addFirst().
    @Override
    public boolean offerFirst(E e) {
        checkNullInputParameter(e);
        try {
            addFirst(e);
        } catch (IllegalStateException i) {
            return false;
        }
        return true;
    }


//Adds element to the end, without exception(false).~addLast().
    @Override
    public boolean offerLast(E e) {
        checkNullInputParameter(e);
        try {
            addLast(e);
        } catch (IllegalStateException i) {
            return false;
        }
        return true;
    }


//Removes and returns the first element of the deque. Throws an exception if empty.
    @Override
    public E removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E elem = first.get(0);
        if (first.size() == 1 && first.getNext() != null) {
            first.getNext().setPrev(null);
            first = first.getNext();
        } else {
            Container<E> box = new Container<>(ContCapacity, null, first.getNext());
            for (int i = 1; i < first.size(); i++) {
                box.add(first.get(i));
            }
            if (first.getNext() == null) {
                last = box;
            } else {
                first.getNext().setPrev(box);
            }
            first = box;
        }
        NumberOfElements--;
        return elem;
    }


//Removes and returns the last element. Throws an exception if empty.
    @Override
    public E removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E elem = last.get(last.size() - 1);
        if (last.size() == 1 && last.getPrev() != null) {
            last.getPrev().setNext(null);
            last = last.getPrev();
        } else {
            Container<E> box = new Container<>(ContCapacity, last.getPrev(), null);
            for (int i = 0; i < last.size() - 1; i++) {
                box.add(last.get(i));
            }
            if (last.getPrev() == null) {
                first = box;
            } else {
                last.getPrev().setNext(box);
            }
            last = box;
        }
        NumberOfElements--;
        return elem;
    }


//Removes and returnes first element. Returns null if empty.
    @Override
    public E pollFirst() {
        try {
            return removeFirst();
        } catch (NoSuchElementException n) {
            return null;
        }
    }


//Removes and returnes last element. Returns null if empty.
    @Override
    public E pollLast() {
        try {
            return removeLast();
        } catch (NoSuchElementException n) {
            return null;
        }
    }


//Returns the first element without removing. Throws an exception if empty.
    @Override
    public E getFirst() throws NoSuchElementException {
        if (!isEmpty()) {
            return first.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }


//Returns the last element without removing. Throws an exception if empty.

    @Override
    public E getLast() throws NoSuchElementException {
        if (!isEmpty()) {
            return last.get(last.size() - 1);
        } else {
            throw new NoSuchElementException();
        }
    }


//Returns the first element without removing. Null if empty.
    @Override
    public E peekFirst() {
        try {
            return getFirst();
        } catch (NoSuchElementException n) {
            return null;
        }
    }


//Returns the last element without removing. Null if empty.
    @Override
    public E peekLast() {
        try {
            return getLast();
        } catch (NoSuchElementException n) {
            return null;
        }
    }


//Removes the first occurence of element from the front of deque. True if removed and false if not.
    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (isEmpty()) {
            return false;
        }
        checkNullInputParameter(o);
        boolean Stat = true;
        Container<E> box = new Container<>(NumberOfElements, null, null);
        for (Container<E> x = first; x != null; x = x.getNext()) {
            for (E e : x.getElementConsist()) {
                if (o.equals(e) && Stat) {
                    Stat = false;
                    NumberOfElements--;
                } else {
                    box.add(e);
                }
            }
        }
        overwriteElements(box);
        checkLastIsNotNull();
        return !Stat;
    }


//Removes the last occurence of element from the deque. True if removed and false if not.
    @Override
    public boolean removeLastOccurrence(Object o) {
        if (isEmpty()) {
            return false;
        }
        checkNullInputParameter(o);
        boolean Stat = true;
        Container<E> box = new Container<>(NumberOfElements, null, null);
        for (Container<E> x = last; x != null; x = x.getPrev()) {
            for (int i = x.size() - 1; i > -1; i--) {
                if (o.equals(x.getElementConsist()[i]) && Stat) {
                    Stat = false;
                    NumberOfElements--;
                } else {
                    box.add(x.getElementConsist()[i]);
                }
            }
        }
        overwriteElements(box);
        checkLastIsNotNull();
        return !Stat;
    }


    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }


//Adds element to the end. True or false 
    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }


    @Override
    public E remove() {
        return removeFirst();
    }


//Removes and returnes first element. Returns null if empty.~pollFirst().
    @Override
    public E poll() {
        return pollFirst();
    }


    @Override
    public E element() {
        return getFirst();
    }


//Returns the first element without removing. Null if empty.~peekFirst().
    @Override
    public E peek() {
        return peekFirst();
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e: c) {
            addLast(e);
        }
        return true;
    }


//Adds element to the beginning. Throws an exception if cannot.~addFirst().
    @Override
    public void push(E e) {
        addFirst(e);
    }


//Removes and returns first element. Throws an exceptionif empty.~removeFirst().
    @Override
    public E pop() {
        return removeFirst();
    }


//Removes and returns first element. Throws an exceptionif empty.~removeFirst().
    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }


    @Override
    public boolean contains(Object o) {
        checkNullInputParameter(o);
        for (Container<E> x = first; x != null; x = x.getNext()) {
            for (Object obj : x.getElementConsist()) {
                if (o.equals(obj)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public int size() {
        return NumberOfElements;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
//Container's placeholder
            int cursor = 0;
            Container<E> container = first;

            @Override
            public boolean hasNext() {
                if (cursor < container.size()) {
                    return true;
                }
                if (cursor == container.size()) {
                    return container.getNext() != null;
                }
                return false;
            }

            @Override
            public E next() {
                if (cursor == container.size()) {
                    if (container.getNext() != null) {
                        cursor -= container.size();
                        container = container.getNext();
                        return next();
                    } else {
                        throw new NoSuchElementException();
                    }
                } else {
                    cursor++;
                    return container.get(cursor - 1);
                }
            }
        };
    }


    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException("Не реализованный");
    }


    public void printTripletDeque() {
        for (Container<E> x = first; x != null; x = x.getNext()) {
            x.printContainer();
        }
        System.out.println();
    }


    private void overwriteElements(Container<E> box) {
        int j = 0;
        for (Container<E> x = first; x != null; x = x.getNext()) {
            for (int i = 0; i < x.size(); i++) {
                if (i + j < box.size()) {
                    x.getElementConsist()[i] = box.get(i + j);
                } else {
                    x.getElementConsist()[i] = null;
                }
            }
            j += x.size();
        }
    }


    private void checkNullInputParameter(Object o) throws NullPointerException {
        if (o == null) {
            throw new NullPointerException();
        }
    }


    private void checkNumberOfElements() throws IllegalStateException {
        if (NumberOfElements == VolumeOfQueue) {
            throw new IllegalStateException("No more free space!");
        }
    }


    private void checkLastIsNotNull() {
        if (last.size() == 0) {
            Container<E> previous = last.getPrev();
            previous.setNext(null);
            last.setPrev(null);
            last = previous;
        }
    }


//Empty check
    @Override
    public boolean isEmpty() {
        return NumberOfElements == 0;
    }


    @Override
    public void clear() {
        Container<E> container = new Container<>(ContCapacity, null, null);
        first = container;
        last = container;
        NumberOfElements = 0;
    }


//Fillers
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    @Override
    public <E> E[] toArray(E[] a) {
        return null;
    }
}