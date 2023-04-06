
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashSet<NoteBook> all_NoteBooks = new HashSet<>();
        all_NoteBooks.add(new NoteBook("Intel Core i5 6400", "RTX 3090", "HP",
                "Omen 15s3", "Windows 10", 4, 500, 35000));
        all_NoteBooks.add(new NoteBook("Intel Core i5 7400", "RTX 2070", "Acer",
                "Aspire 12377", "Windows 10", 4, 500, 39000));
        all_NoteBooks.add(new NoteBook("Intel Core i5 7400", "RTX 1030", "Acer",
                "Aspire 11s9", "Ubuntu", 4, 1000, 45000));
        all_NoteBooks.add(new NoteBook("Ryzen 5 3600", "RX 5500", "Asus",
                "ROG 1030", "NoOS", 8, 320, 70000));
        all_NoteBooks.add(new NoteBook("Ryzen 5 5500U", "RX 5600", "Asus",
                "ROG 1030", "NoOS", 8, 500, 65000));
        all_NoteBooks.add(new NoteBook("Intel Core i7 8700k", "GTX 1080", "Asus",
                "ROG 1030", "NoOS", 8, 750, 70000));
        all_NoteBooks.add(new NoteBook("Intel Core i9 9900k", "RTX 4090", "Huawei",
                "15.6Hs15", "Windows 11", 8, 1000, 60000));
        all_NoteBooks.add(new NoteBook("Ryzen 3 3500U", "noGPU", "Huawei",
                "15.6Hs15", "Linux", 16, 500, 85000));

        main_menu(all_NoteBooks);
    }

    public static void main_menu(HashSet<NoteBook> all_NoteBooks) {
        System.out.println("====================############=======================");
        System.out.println("Добро пожаловать в наш магазин ноутбуков");
        boolean flag = false;
        while (!flag) {
            System.out.println("====================############=======================");
            System.out.println("""
                    1. Вывести весь каталог
                    2. Отфильтровать каталог
                    3. Покинуть магазин""");
            System.out.print("Выберите необходимый пункт меню: ");
            Scanner scanner = new Scanner(System.in);
            String change = scanner.nextLine();
            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                if (change.equals("1")) {
                    for (NoteBook i : all_NoteBooks) {
                        i.info();
                    }
                } else if (change.equals("2")) {
                    flag = true;
                    filter_menu(all_NoteBooks);
                } else {
                    System.out.println("---------------");
                    System.out.println("Завершаю работу");
                    System.out.println("---------------");
                    flag = true;
                }
            } else {
                System.out.println("----------------------");
                System.out.println("Такого пункта меню нет");
                System.out.println("----------------------");
            }
        }
    }

    public static void filter_menu(HashSet<NoteBook> all_NoteBooks) {
        HashSet<NoteBook> filter = new HashSet<>();
        boolean flag = false;
        while (!flag) {
            System.out.println("====================############=======================");
            System.out.println("""
                    1. Отсортировать по цене
                    2. Отсортировать по количеству ОЗУ
                    3. Отсортировать по объему жесткого диска
                    4. Вывести на экран каталог после сортировки
                    5. Сбросить параметры сортировки
                    6. Покинуть меню сортировки
                    """);
            System.out.print("Выберите необходимый пункт меню: ");
            Scanner scanner = new Scanner(System.in);
            String change = scanner.nextLine();
            if (change.equals("1") || change.equals("2") || change.equals("3") || change.equals("4")
                    || change.equals("5") || change.equals("6")) {
                switch (change) {
                    case "1" -> price_sort(all_NoteBooks, filter);
                    case "2" -> ram_sort(all_NoteBooks, filter);
                    case "3" -> hdd_sort(all_NoteBooks, filter);
                    case "4" -> {
                        if (!filter.isEmpty()) {
                            for (NoteBook i : filter) {
                                i.info();
                            }
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("Нет ноутбуков удовлетворяющих вашим условиям " +
                                    "или не выбраны параметры фильтрации");
                            System.out.println("-----------------------------");
                        }
                    }
                    case "5" -> {
                        filter.clear();
                        System.out.println("-----------------------------");
                        System.out.println("Параметры сортировки сброшены");
                        System.out.println("-----------------------------");
                    }
                    default -> {
                        flag = true;
                        main_menu(all_NoteBooks);
                    }
                }
            } else {
                System.out.println("----------------------");
                System.out.println("Такого пункта меню нет");
                System.out.println("----------------------");
            }
        }
    }

    public static void price_sort(HashSet<NoteBook> all_NoteBooks, HashSet<NoteBook> filter) {
        System.out.println("====================############=======================");
        Scanner s = new Scanner(System.in);
        int min_price_int = 0;
        int max_price_int = 0;
        boolean flag = false;
        while (!flag) {
            System.out.print("Введите минимальную цену: ");
            String min_price = s.next();
            if (isDigit(min_price)) {
                min_price_int = Integer.parseInt(min_price);
                flag = true;
            } else {
                System.out.println("------------------");
                System.out.println("Вы ввели не число!!!");
                System.out.println("------------------");
            }
        }
        flag = false;
        while (!flag) {
            System.out.print("Введите максимальную цену: ");
            String max_price = s.next();
            if (isDigit(max_price)) {
                max_price_int = Integer.parseInt(max_price);
                flag = true;
            } else {
                System.out.println("------------------");
                System.out.println("Вы ввели не число!!!");
                System.out.println("------------------");
            }
        }
        if (filter.isEmpty()) {
            for (NoteBook p : all_NoteBooks) {
                if (p.price <= max_price_int && p.price >= min_price_int) {
                    filter.add(p);
                }
            }
        } else {
            HashSet<NoteBook> support_filter = new HashSet<>(filter);
            filter.clear();
            for (NoteBook p : support_filter) {
                if (p.price <= max_price_int && p.price >= min_price_int) {
                    filter.add(p);
                }
            }
        }
    }

    public static void ram_sort(HashSet<NoteBook> all_NoteBooks, HashSet<NoteBook> filter) {
        System.out.println("====================############=======================");
        Scanner s = new Scanner(System.in);
        int min_ram_int = 0;
        int max_ram_int = 0;
        boolean flag = false;
        while (!flag) {
            System.out.print("Введите минимальный объем оперативной памяти: ");
            String min_ram = s.next();
            if (isDigit(min_ram)) {
                min_ram_int = Integer.parseInt(min_ram);
                flag = true;
            } else {
                System.out.println("------------------");
                System.out.println("Вы ввели не число!!!");
                System.out.println("------------------");
            }
        }
        flag = false;
        while (!flag) {
            System.out.print("Введите максимальный объем оперативной памяти: ");
            String max_ram = s.next();
            if (isDigit(max_ram)) {
                max_ram_int = Integer.parseInt(max_ram);
                flag = true;
            } else {
                System.out.println("------------------");
                System.out.println("Вы ввели не число!!!");
                System.out.println("------------------");
            }
        }
        if (filter.isEmpty()) {
            for (NoteBook p : all_NoteBooks) {
                if (p.ram_size <= max_ram_int && p.ram_size >= min_ram_int) {
                    filter.add(p);
                }
            }
        } else {
            HashSet<NoteBook> support_filter = new HashSet<>(filter);
            filter.clear();
            for (NoteBook p : support_filter) {
                if (p.ram_size <= max_ram_int && p.ram_size >= min_ram_int) {
                    filter.add(p);
                }
            }
        }
    }

    public static void hdd_sort(HashSet<NoteBook> all_NoteBooks, HashSet<NoteBook> filter) {
        System.out.println("====================############=======================");
        Scanner s = new Scanner(System.in);
        int min_hdd_int = 0;
        int max_hdd_int = 0;
        boolean flag = false;
        while (!flag) {
            System.out.print("Введите минимальный объем жесткого диска: ");
            String min_hdd = s.next();
            if (isDigit(min_hdd)) {
                min_hdd_int = Integer.parseInt(min_hdd);
                flag = true;
            } else {
                System.out.println("------------------");
                System.out.println("Вы ввели не число!!!");
                System.out.println("------------------");
            }
        }
        flag = false;
        while (!flag) {
            System.out.print("Введите максимальный объем жесткого диска: ");
            String max_hdd = s.next();
            if (isDigit(max_hdd)) {
                max_hdd_int = Integer.parseInt(max_hdd);
                flag = true;
            } else {
                System.out.println("------------------");
                System.out.println("Вы ввели не число!!!");
                System.out.println("------------------");
            }
        }
        if (filter.isEmpty()) {
            for (NoteBook p : all_NoteBooks) {
                if (p.hdd_size <= max_hdd_int && p.hdd_size >= min_hdd_int) {
                    filter.add(p);
                }
            }
        } else {
            HashSet<NoteBook> support_filter = new HashSet<>(filter);
            filter.clear();
            for (NoteBook p : support_filter) {
                if (p.hdd_size <= max_hdd_int && p.hdd_size >= min_hdd_int) {
                    filter.add(p);
                }
            }
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}