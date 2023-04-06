import java.util.*;

public class NoteBook {
    private String model_cpu;
    private String model_gpu;
    private String manufacturer;
    private String model;
    int ram_size;
    int hdd_size;
    String os_name;
    String color;
    int price;
    String[] color_var = new String[]{"Черный", "Белый", "Красный", "Зеленый"};

    NoteBook(String arg_model_cpu, String arg_model_gpu, String arg_manufacturer,
             String arg_model, String arg_os_name, int arg_ram_size, int arg_hdd_size, int arg_price) {
        Random r = new Random();
        this.model_cpu = arg_model_cpu;
        this.model_gpu = arg_model_gpu;
        this.manufacturer = arg_manufacturer;
        this.model = arg_model;
        this.ram_size = arg_ram_size;
        this.hdd_size = arg_hdd_size;
        this.price = arg_price;
        this.os_name = arg_os_name;
        this.color = color_var[r.nextInt(color_var.length)];
    }

    public void info() {
        System.out.println("====================############=======================");
        System.out.printf("Фирма изготовитель: %s \n", manufacturer);
        System.out.printf("Модель: %s \n", model);
        System.out.printf("Процессор: %s \n", model_cpu);
        System.out.printf("Видеокарта: %s \n", model_gpu);
        System.out.printf("Количество оперативной памяти: %d гб \n", ram_size);
        System.out.printf("Объем жесткого диска: %d гб \n", hdd_size);
        System.out.printf("Операционная система %s \n", os_name);
        System.out.printf("Цвет: %s \n", color);
        System.out.printf("Цена: %d рублей \n", price);
    }
}