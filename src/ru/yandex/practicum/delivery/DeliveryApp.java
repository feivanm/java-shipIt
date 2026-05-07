package ru.yandex.practicum.delivery;
import ru.yandex.practicum.delivery.model.parcels.FragileParcel;
import ru.yandex.practicum.delivery.model.parcels.Parcel;
import ru.yandex.practicum.delivery.model.parcels.PerishableParcel;
import ru.yandex.practicum.delivery.model.parcels.StandardParcel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Введите название посылки:");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки в кг:");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Введите дату отправки :");
        System.out.println("Введите дату в формате dd.MM.yyyy:");
        String input = scanner.nextLine(); // Чтение строки
        // Создание форматера
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Преобразование строки в дату
        LocalDate sendDay = LocalDate.parse(input, formatter);
        System.out.println("Выберите тип посылки:");
        int choiceType = Integer.parseInt(scanner.nextLine());
        switch (choiceType) {
            case 1:
                allParcels.add(new StandardParcel(description,weight,deliveryAddress,sendDay));
                break;
            case 2:
                allParcels.add(new FragileParcel(description,weight,deliveryAddress,sendDay));
                break;
            case 3:
                System.out.println("Введите срок жизни в днях:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                allParcels.add(new PerishableParcel(description,weight,deliveryAddress,sendDay,timeToLive));
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова");
        }

    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        allParcels.clear();
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        double sum=0;
        for (Parcel parcel : allParcels) {
            sum=sum+parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + sum);
    }

}

