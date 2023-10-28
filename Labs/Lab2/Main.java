package Labs.Lab2;

public class Main {
    public static void main(String[] args) {
        TripletDeque<String> tripletDeque = new TripletDeque<>();

        System.out.println(tripletDeque.size());      // Вывод: 0

        tripletDeque.addFirst("Добавить в начало 1");
        tripletDeque.addLast("Добавить в конец 1");
        System.out.println(tripletDeque.size());      // Вывод: 2

        tripletDeque.addFirst("Добавить в начало 2");
        tripletDeque.addLast("Добавление в конец 2");

        System.out.println(tripletDeque.getFirst());  // Вывод: Добавить в начало 2
        System.out.println(tripletDeque.getLast());   // Вывод: Добавление в конец 2
        System.out.println(tripletDeque.size());      // Вывод: 4

        System.out.println(tripletDeque.removeFirst());  // Вывод: Добавить в начало 2
        System.out.println(tripletDeque.removeLast());   // Вывод: Добавление в конец 2

        System.out.println(tripletDeque.contains("Добавить в начало 1"));      // Вывод: true
        System.out.println(tripletDeque.contains("Добавление в конец 2")); // Вывод: false
    }
}
