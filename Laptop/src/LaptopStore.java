import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Laptop {
    private String brand;
    private String model;
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color;
    private double price; // Цена в USD

    public Laptop(String brand, String model, int ram, int storage, String os, String color, double price) {
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}

public class LaptopStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>(Arrays.asList(
                new Laptop("Dell", "Inspiron", 8, 512, "Windows", "Black", 650),
                new Laptop("HP", "Pavilion", 16, 1024, "Windows", "Silver", 850),
                new Laptop("Apple", "MacBook Air", 8, 256, "macOS", "Gray", 1200),
                new Laptop("Lenovo", "ThinkPad", 16, 512, "Linux", "Black", 950),
                new Laptop("Asus", "ZenBook", 32, 1024, "Windows", "Blue", 1100),
                new Laptop("Acer", "Aspire", 4, 256, "Windows", "White", 400)
        ));

        Scanner scanner = new Scanner(System.in);

        Map<Integer, Predicate<Laptop>> criteria = new HashMap<>();
        criteria.put(1, laptop -> true); // RAM
        criteria.put(2, laptop -> true); // Storage
        criteria.put(3, laptop -> true); // OS
        criteria.put(4, laptop -> true); // Color

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        String input = scanner.nextLine();
        String[] selectedCriteria = input.split(",");

        Map<Integer, String> filterValues = new HashMap<>();

        for (String criterion : selectedCriteria) {
            int key = Integer.parseInt(criterion.trim());
            switch (key) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ (в ГБ): ");
                    int minRam = Integer.parseInt(scanner.nextLine());
                    criteria.put(1, laptop -> laptop.getRam() >= minRam);
                    filterValues.put(1, Integer.toString(minRam));
                    break;
                case 2:
                    System.out.print("Введите минимальный объем ЖД (в ГБ): ");
                    int minStorage = Integer.parseInt(scanner.nextLine());
                    criteria.put(2, laptop -> laptop.getStorage() >= minStorage);
                    filterValues.put(2, Integer.toString(minStorage));
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    String os = scanner.nextLine();
                    criteria.put(3, laptop -> laptop.getOs().equalsIgnoreCase(os));
                    filterValues.put(3, os);
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    String color = scanner.nextLine();
                    criteria.put(4, laptop -> laptop.getColor().equalsIgnoreCase(color));
                    filterValues.put(4, color);
                    break;
                default:
                    System.out.println("Неверный критерий: " + key);
            }
        }

        // Фильтрация ноутбуков на основе выбранных критериев
        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> criteria.entrySet().stream()
                        .allMatch(entry -> entry.getValue().test(laptop)))
                .collect(Collectors.toSet());

        System.out.println("\nНоутбуки, соответствующие выбранным критериям:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}
