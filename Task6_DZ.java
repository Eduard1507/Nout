import java.util.*;

public class PhoneBook {
    private HashMap<String, List<Integer>> phoneBook = new HashMap<>();

    // Метод для добавления записи в телефонную книгу
    public void add(String name, int phoneNum) {
        // Получаем текущий список номеров для имени
        List<Integer> phones = phoneBook.getOrDefault(name, new ArrayList<>());
        // Добавляем новый номер телефона в список
        phones.add(phoneNum);
        // Обновляем запись в телефонной книге
        phoneBook.put(name, phones);
    }

    // Метод для получения телефонных номеров по имени
    public List<Integer> find(String name) {
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }

    // Метод для получения телефонной книги, отсортированной по убыванию количества телефонных номеров
    public LinkedHashMap<String, List<Integer>> getPhoneBookSortedByPhoneCount() {
        // Создаем список записей из HashMap
        List<Map.Entry<String, List<Integer>>> entries = new ArrayList<>(phoneBook.entrySet());
        
        // Сортируем список по убыванию количества телефонных номеров
        Collections.sort(entries, new Comparator<Map.Entry<String, List<Integer>>>() {
            @Override
            public int compare(Map.Entry<String, List<Integer>> entry1, Map.Entry<String, List<Integer>> entry2) {
                return entry2.getValue().size() - entry1.getValue().size();
            }
        });

        // Создаем LinkedHashMap для хранения отсортированного результата
        LinkedHashMap<String, List<Integer>> sortedPhoneBook = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> entry : entries) {
            sortedPhoneBook.put(entry.getKey(), entry.getValue());
        }

        return sortedPhoneBook;
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Пример добавления записей в телефонную книгу
        phoneBook.add("Alice", 123456);
        phoneBook.add("Alice", 789012);
        phoneBook.add("Bob", 789012);

        // Выводим телефонную книгу
        System.out.println("Phone book:");
        for (Map.Entry<String, List<Integer>> entry : phoneBook.getPhoneBookSortedByPhoneCount().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Пример поиска по имени
        System.out.println("\nFind Alice:");
        List<Integer> alicePhones = phoneBook.find("Alice");
        System.out.println(alicePhones);

        // Пример поиска по несуществующему имени
        System.out.println("\nFind John:");
        List<Integer> johnPhones = phoneBook.find("John");
        System.out.println(johnPhones);
    }
}
