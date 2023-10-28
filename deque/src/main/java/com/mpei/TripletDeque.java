package com.mpei;

import java.util.*;

public class TripletDeque<E> implements Deque<E>, Containerable {
    private class Container<E> {
        private Container<E> next;
        private Container<E> prev;
        private Object[] elementData;

        public Container(int initialCapacity, Container<E> prev, Container<E> next) {
            this.elementData = new Object[initialCapacity];
            this.prev = prev;
            this.next = next;
        }

        public int size() {
            int size = 0;
            for (Object o : elementData) {
                if (o != null) {
                    size++;
                }
            }
            return size;
        }

        public void add(Object o) {
            if (o != null) {
                int i = 0;
                boolean flg = true;
                while (flg) {
                    if (elementData[i] == null) {
                        elementData[i] = o;
                        flg = false;
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
            return (E) elementData[index];
        }

        public void printContainer() {
            System.out.print(Arrays.toString(elementData) + " ");
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

        public E[] getElementData() {
            return (E[]) elementData;
        }
    }

    private Container<E> first;
    private Container<E> last;
    private int initialCapacity;
    private int queueVolume;
    private int amountOfElements;

    public TripletDeque() {
        Container<E> container = new Container<>(5, null, null);
        this.first = container;
        this.last = container;
        this.initialCapacity = 5;
        this.queueVolume = 1000;
        this.amountOfElements = 0;
    }

    public TripletDeque(int initialCapacity, int queueVolume) {
        Container<E> container = new Container<>(initialCapacity, null, null);
        this.first = container;
        this.last = container;
        this.initialCapacity = initialCapacity;
        this.queueVolume = queueVolume;
        this.amountOfElements = 0;
    }

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        int i = 0;
        for (Container<E> x = first; x != null; x = x.getNext()) {
            if (i == cIndex) {
                return x.getElementData();
            }
            i++;
        }
        return null;
    }

    @Override
    public void addFirst(E e) {
        checkAmountOfElements();
        checkNullInputParameter(e);
        if (first.size() < initialCapacity) {
            Container<E> box = new Container<>(initialCapacity, null, first.getNext());
            box.add(e);
            box.addAll(first.getElementData());
            if (first.equals(last)) {
                last = box;
            }
            if (first.getNext() != null) {
                first.getNext().setPrev(box);
            }
            first = box;
            amountOfElements++;
        } else {
            Container<E> box = new Container<>(initialCapacity, null, first);
            first.setPrev(box);
            first = box;
            addFirst(e);
        }
    }

    @Override
    public void addLast(E e) {
        checkAmountOfElements();
        checkNullInputParameter(e);
        if (last.size() < initialCapacity) {
            last.add(e);
            amountOfElements++;
        } else {
            Container<E> box = new Container<>(initialCapacity, last, null);
            last.setNext(box);
            last = box;
            addLast(e);
        }
    }

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
            Container<E> box = new Container<>(initialCapacity, null, first.getNext());
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
        amountOfElements--;
        return elem;
    }

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
            Container<E> box = new Container<>(initialCapacity, last.getPrev(), null);
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
        amountOfElements--;
        return elem;
    }

    @Override
    public E pollFirst() {
        try {
            return removeFirst();
        } catch (NoSuchElementException n) {
            return null;
        }
    }

    @Override
    public E pollLast() {
        try {
            return removeLast();
        } catch (NoSuchElementException n) {
            return null;
        }
    }

    @Override
    public E getFirst() throws NoSuchElementException {
        if (!isEmpty()) {
            return first.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E getLast() throws NoSuchElementException {
        if (!isEmpty()) {
            return last.get(last.size() - 1);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E peekFirst() {
        try {
            return getFirst();
        } catch (NoSuchElementException n) {
            return null;
        }
    }

    @Override
    public E peekLast() {
        try {
            return getLast();
        } catch (NoSuchElementException n) {
            return null;
        }
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (isEmpty()) {
            return false;
        }
        checkNullInputParameter(o);
        boolean flg = true;
        Container<E> box = new Container<>(amountOfElements, null, null);
        for (Container<E> x = first; x != null; x = x.getNext()) {
            for (E e : x.getElementData()) {
                if (o.equals(e) && flg) {
                    flg = false;
                    amountOfElements--;
                } else {
                    box.add(e);
                }
            }
        }
        overwriteElements(box);
        checkLastIsNotNull();
        return !flg;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (isEmpty()) {
            return false;
        }
        checkNullInputParameter(o);
        boolean flg = true;
        Container<E> box = new Container<>(amountOfElements, null, null);
        for (Container<E> x = last; x != null; x = x.getPrev()) {
            for (int i = x.size() - 1; i > -1; i--) {
                if (o.equals(x.getElementData()[i]) && flg) {
                    flg = false;
                    amountOfElements--;
                } else {
                    box.add(x.getElementData()[i]);
                }
            }
        }
        overwriteElements(box);
        checkLastIsNotNull();
        return !flg;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

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

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean contains(Object o) {
        checkNullInputParameter(o);
        for (Container<E> x = first; x != null; x = x.getNext()) {
            for (Object obj : x.getElementData()) {
                if (o.equals(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return amountOfElements;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0; //Положение курсора в контейнере
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
        throw new UnsupportedOperationException();
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
                    x.getElementData()[i] = box.get(i + j);
                } else {
                    x.getElementData()[i] = null;
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

    private void checkAmountOfElements() throws IllegalStateException {
        if (amountOfElements == queueVolume) {
            throw new IllegalStateException();
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

    @Override
    public boolean isEmpty() {
        return amountOfElements == 0;
    }

    @Override
    public void clear() {
        Container<E> container = new Container<>(initialCapacity, null, null);
        first = container;
        last = container;
        amountOfElements = 0;
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
}