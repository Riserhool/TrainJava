// 1.Разработать собственную реализацию интерфейса Deque "TripletDeque": данные хранятся в контейнерах фиксированной 
// величины (5  элементов в контейнере по умолчанию)
// 2. Контейнер представляет собой массив, а также ряд ссылок: на последующий контейнер и на предыдущий
// 3. При добавлении элемента в TripletDeque с помощью метода addLast() обьект данных добавляется в последний контейнер,
// в первую свободную ячейку слева. Если контейнер оказался полностью заполненным, то добавляется новый контейнер и становится 
// последним.
//4. При добавлении элемента в TripletDeque() с помощью метода addFirst() обьект данных добавляется в первый 
// контейнер. в последнюю свободную ячейку слева.
// Если контейнер оказался полностью заполненным, то добавляется новый контейнер и становится первым.
//5. Реализация д.б. generic
//6. В конструкторе реализация д.б. осуществлена возможность установки обьема очереди, по умолчанию размер = 1000
//7. Необходимо реализовать методы:
// void addFirst(E e)
// void addLast(E e)
// boolean offerFirst(E e)
// boolean offerLast(E e)
// E removeFirst()
// E removeLast()
// E pollFirst()
// E pollLast()
// E getFirst()
// E getLast()
// E peekFirst()
// E peekLast()
// boolean removeFirstOccurence(Object o)
// boolean removeLastOccurence(Object o)
// boolean add(E e)DEFAULT_CAPACITY
// boolean offer(E e)
// E remove()
// E poll()
// E element()
// E peek()
// boolean addAll(Collection<? extends E> c)
// void push(E e)
// E pop()
// boolean remove(Object o)
// boolean contains(Object o)
// int size()
// Iterator<E> iterator();
// 8. Метод descendingIterator() - д.б. не реализован и бросать UnsupportedOperationException (не реализованный)

package Labs.Lab2;
import java.util.*;

public class TripletDeque<E> implements Deque<E> {

    private static final int DEFAULT_CAPACITY = 1000;
    private static final int CONTAINER_SIZE = 5;

    private ArrayList<ArrayList<E>> containers;
    private int size;

    public TripletDeque() {
        this(DEFAULT_CAPACITY);
    }

    public TripletDeque(int capacity) {
        containers = new ArrayList<>();
        containers.add(new ArrayList<>(CONTAINER_SIZE));
        size = 0;
    }

    @Override
    public Iterator descendingIterator() {
        throw new UnsupportedOperationException("не реализованный");
    }


    @Override
    public void addFirst(E e) {
        if (containers.get(0).size() == CONTAINER_SIZE) {
            containers.add(0, new ArrayList<>(CONTAINER_SIZE));
        }
        containers.get(0).add(0, e);
        size++;
    }

    @Override
    public void addLast(E e) {
        int lastIndex = containers.size() - 1;
        ArrayList<E> lastContainer = containers.get(lastIndex);
        if (lastContainer.size() == CONTAINER_SIZE) {
            containers.add(new ArrayList<>(CONTAINER_SIZE));
            lastIndex++;
        }
        lastContainer.add(e);
        size++;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E element = containers.get(0).remove(0);
        if (containers.get(0).isEmpty()) {
            containers.remove(0);
        }
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int lastIndex = containers.size() - 1;
        ArrayList<E> lastContainer = containers.get(lastIndex);
        E element = lastContainer.remove(lastContainer.size() - 1);
        if (lastContainer.isEmpty()) {
            containers.remove(lastIndex);
        }
        size--;
        return element;
    }

    @Override
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        return removeFirst();
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        return removeLast();
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return containers.get(0).get(0);
    }

    @Override
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int lastIndex = containers.size() - 1;
        ArrayList<E> lastContainer = containers.get(lastIndex);
        return lastContainer.get(lastContainer.size() - 1);
    }

    @Override
    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return getFirst();
    }

    @Override
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (size == 0) {
            return false;
        }
        for (ArrayList<E> container : containers) {
            if (container.remove(o)) {
                if (container.isEmpty()) {
                    containers.remove(container);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (size == 0) {
            return false;
        }
        for (int i = containers.size() - 1; i >= 0; i--) {
            ArrayList<E> container = containers.get(i);
            if (container.remove(o)) {
                if (container.isEmpty()) {
                    containers.remove(container);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }



    @Override
    public E poll() {
        return pollFirst();
    }



    @Override
    public boolean contains(Object o) {
        for (ArrayList<E> container : containers) {
            if (container.contains(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Iterator<E> iterator() {
        return new TripletDequeIterator();
    }
    

    private class TripletDequeIterator implements Iterator<E> {

        private int containerIndex;
        private int elementIndex;
        private int count;

        public TripletDequeIterator() {
            containerIndex = 0;
            elementIndex = 0;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = containers.get(containerIndex).get(elementIndex);
            elementIndex++;
            count++;
            if (elementIndex >= containers.get(containerIndex).size()) {
                containerIndex++;
                elementIndex = 0;
            }
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
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
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            addLast(element);
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
}