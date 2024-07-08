package job;

import java.util.*;
import java.util.stream.Collectors;

public class Laptop implements Comparable<Laptop> {
    private List<Brand> brands;
    private String model;
    private float diagonal;
    private String screenResolution;
    private boolean dvdRom;
    private int hardDisk;
    private String operativeMemory;
    private String operatingSystem;
    private String color;

    public Laptop() {
    }

    public Laptop(String model, float diagonal, String screenResolution, boolean dvdRom, int hardDisk,
                  String operativeMemory, String operatingSystem, String color) {
        this.brands = new ArrayList<>();
        this.model = model;
        this.diagonal = diagonal;
        this.screenResolution = screenResolution;
        this.dvdRom = dvdRom;
        this.hardDisk = hardDisk;
        this.operativeMemory = operativeMemory;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public float getDiagonal() {
        return diagonal;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public boolean isDvdRom() {
        return dvdRom;
    }

    public int getHardDisk() {
        return hardDisk;
    }

    public String getOperativeMemory() {
        return operativeMemory;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void addBrand(Brand brand) {
        brands.add(brand);
    }

    public List<Laptop> filter(Set<Laptop> laptops) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Укажите параметры для ноутбука.");
        System.out.println("Размер жёсткого диска в ГБ (300, 500, 600, 700, 1000):");
        int enterHardDisk = Integer.parseInt(scan.nextLine());
        System.out.println("Оперативная память в ГБ (4, 8, 16):");
        String enterOperativeMemory = scan.nextLine();
        System.out.println("Операционная система (Linux, iOS, Windows):");
        String enterOperatingSystem = scan.nextLine();
        System.out.println("Цвет (black, white):");
        String enterColor = scan.nextLine();

        return laptops.stream()
                .filter(laptop -> laptop.getHardDisk() == enterHardDisk)
                .filter(laptop -> laptop.getOperativeMemory().equals(enterOperativeMemory))
                .filter(laptop -> laptop.getOperatingSystem().equals(enterOperatingSystem))
                .filter(laptop -> laptop.getColor().equals(enterColor))
                .sorted()
                .collect(Collectors.toList());
    }

    public String newFilter(Set<Laptop> laptops) {
        Scanner scan = new Scanner(System.in);
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);
        Set<Character> validCriteria = Set.of('1', '2', '3', '4');

        while (true) {
            System.out.println("Укажите номер или номера критериев фильтрации ноутбуков:");
            System.out.println("1. Размер жесткого диска");
            System.out.println("2. Размер ОЗУ");
            System.out.println("3. Операционная система");
            System.out.println("4. Цвет");
            String userRequest = scan.nextLine();

            if (userRequest.chars().allMatch(ch -> validCriteria.contains((char) ch))) {
                for (char criterion : userRequest.toCharArray()) {
                    switch (criterion) {
                        case '1':
                            System.out.println("Укажите размер жёсткого диска в ГБ (300, 500, 600, 700, 1000):");
                            int hardDisk = Integer.parseInt(scan.nextLine());
                            filteredLaptops = filteredLaptops.stream()
                                    .filter(laptop -> laptop.getHardDisk() >= hardDisk)
                                    .collect(Collectors.toSet());
                            break;
                        case '2':
                            System.out.println("Укажите количество оперативной памяти в ГБ (4, 8, 16):");
                            String operativeMemory = scan.nextLine();
                            filteredLaptops = filteredLaptops.stream()
                                    .filter(laptop -> laptop.getOperativeMemory().equals(operativeMemory))
                                    .collect(Collectors.toSet());
                            break;
                        case '3':
                            System.out.println("Укажите операционную систему (Linux, iOS, Windows):");
                            String operatingSystem = scan.nextLine();
                            filteredLaptops = filteredLaptops.stream()
                                    .filter(laptop -> laptop.getOperatingSystem().equals(operatingSystem))
                                    .collect(Collectors.toSet());
                            break;
                        case '4':
                            System.out.println("Укажите цвет ноутбука (black, white):");
                            String color = scan.nextLine();
                            filteredLaptops = filteredLaptops.stream()
                                    .filter(laptop -> laptop.getColor().equals(color))
                                    .collect(Collectors.toSet());
                            break;
                    }
                }
                break;
            } else {
                System.out.println("Неверный критерий. Пожалуйста, введите снова.");
            }
        }

        return filteredLaptops.stream()
                .sorted()
                .map(Laptop::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "Model: " + model +
                "\nBrands: " + brands +
                "\nDiagonal: " + diagonal +
                "\nScreen Resolution: " + screenResolution +
                "\nDVD Rom: " + dvdRom +
                "\nHard Disk: " + hardDisk +
                "\nOperative Memory: " + operativeMemory +
                "\nOperating System: " + operatingSystem +
                "\nColor: " + color +
                "\n";
    }

    @Override
    public int compareTo(Laptop other) {
        return Integer.compare(this.hardDisk, other.hardDisk);
    }
}
