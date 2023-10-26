package com.mpei;



import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TripletDeque<T> implements Deque<T>, Containerable {

    private MyContainer<T> firstContainer;
    private MyContainer<T> lastContainer;
    public int count_data = 0;

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        MyContainer<T> ref = firstContainer;
        int count_data = 0;
        while (cIndex!=count_data){
            ref = ref.next;
            count_data++;
        }
        return ref.data;
    }

    private class MyContainer<T> {
        MyContainer<T> next;
        MyContainer<T> prev;
        private int capacity, iniIndex, lastIndex;
        private Object[] data;

        public MyContainer(int capacity) {
            this.capacity = capacity;
            this.data = new Object[capacity];
        }

        public MyContainer() {
            this(5);
        }

        public boolean addFirst(T object){
            if(iniIndex == 0){
                return false;
            }
            else{
                data[iniIndex] = object;
                iniIndex--;
                count_data++;
                return true;
            }
            // throw new RuntimeException("not implemented");
        }

        public boolean addLast(T object){
            if (lastIndex == capacity){
                return false;
            } else {
                data[lastIndex] = object;
                lastIndex++;
                count_data++;
                return true;
            }
        }

        public int getDataCount(){
            return lastIndex -iniIndex;
        }
    }

    @Override
    public void addFirst(T t) {
        if (firstContainer == null){
            firstContainer = new MyContainer<>();
            firstContainer = lastContainer;
        }
        boolean res = firstContainer.addFirst(t);
        if (!res){
            MyContainer<T> newFirstContainer = new MyContainer<>();
            newFirstContainer.next = firstContainer;
            firstContainer.prev = newFirstContainer;
            firstContainer = newFirstContainer;
            this.addFirst(t);
        }
    }

    @Override
    public void addLast(T t) {
        if (lastContainer == null){
            firstContainer = new MyContainer<>();
            lastContainer = firstContainer;
        }
        boolean res = lastContainer.addLast(t);
        if (!res){
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
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            return pollFirst();
        }
        // return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T pollFirst() {
        if (isEmpty()){
            return null;
        }
        T poll = firstContainer.data[iniIndex];
        if (firstContainer.capacity == 1 && firstContainer.next != null){
            firstContainer.next.setPrev(null);
            firstContainer = firstContainer.next;
        } else {
            Container<T> box = new MyContainer<>();
            for (int i = 1; i < firstContainer.capacity; i++) {
                box.add(firstContainer.get(i));
            }
            if (firstContainer.next == null) {
                lastContainer = box;
            } else {
                firstContainer.next.setPrev(box);
            }
            firstContainer = box;
        }
        return poll;            
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        Object datum = this.lastContainer.data[this.lastContainer.lastIndex - 1];
        return (T) datum;
    }

    @Override
    public T peekFirst() {
        try {
            return getFirst();
        }catch (NoSuchElementException t){
            return  null;
        }
    }

    @Override
    public T peekLast() {
        try {
            return getLast();
        }catch (NoSuchElementException t){
            return  null;
        }
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        MyContainer<T> checker = firstContainer;
        if(getFirst().equals(o)){
            removeFirst();
            return true;
        }
        else {
        while (checker != null ){
            for (int i = 0; i < checker.array.length; i ++){
                if (checker.array[i] != null) {
                    if (checker.array[i].equals(o)) {
                        checker.array[i] = removeFirst();
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
        if(getFirst().equals(o)){
            removeLast();
            return true;
        }
        else {
        while (checker != null ){
            for (int i = checker.array.length - 1; i >= 0; i --){
                if (checker.array[i] != null) {
                    if (checker.array[i].equals(o)) {
                        checker.array[i] = removeLast();
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
    public boolean add(T t) {
        int d = count_data;
        addLast(t);
        if (d != count_data){
            return true;
        }else {
        return false;
    }
    }

    @Override
    public boolean offer(T t) {
        try {
            addLast(t);
            return true;
        }catch (IllegalStateException e){
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
        }catch (NoSuchElementException t){
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
        }catch (NoSuchElementException e){
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
        lastContainer=null;
        firstContainer=null;
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
        return false;
    }

    @Override
    public int size() {
        return count_data;
    }

    @Override
    public boolean isEmpty() {
        if (count_data == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
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
