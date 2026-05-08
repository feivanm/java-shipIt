package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.interfaces.Trackable;
import ru.yandex.practicum.delivery.model.boxes.ParcelBox;
import ru.yandex.practicum.delivery.model.parcels.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> allParcelsTrackable = new ArrayList<>();
    private static ParcelBox<StandardParcel> standartParcelBox = new ParcelBox<>(ParcelType.STANDARTPARCEL,20);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(ParcelType.FRAGILEPARCEL,10);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(ParcelType.PERISHABLEPARCEL,15);

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
                case 4:
                    printDeliveryStatus();
                    break;
                case 5:
                    printParcelInParcelBox();
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
        System.out.println("4 — Показать статус доставки");
        System.out.println("5 — Показать содержимое коробки");
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
        System.out.println("Введите дату отправки в формате dd.MM.yyyy:");
        String input = scanner.nextLine(); // Чтение строки
        // Создание форматера
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Преобразование строки в дату
        LocalDate sendDay = LocalDate.parse(input, formatter);
        System.out.println("Выберите тип посылки:");
        int choiceType = Integer.parseInt(scanner.nextLine());
        switch (choiceType) {
            case 1:
                StandardParcel newStandardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                if (standartParcelBox.addParcel(newStandardParcel)){
                    allParcels.add(newStandardParcel);
                }
                break;
            case 2:
                FragileParcel newFragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                if (fragileParcelBox.addParcel(newFragileParcel)){
                    allParcelsTrackable.add(newFragileParcel);
                    allParcels.add(newFragileParcel);
                }
                break;
            case 3:
                System.out.println("Введите срок жизни в днях:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel newPerishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                if (perishableParcelBox.addParcel(newPerishableParcel)){
                    allParcels.add(newPerishableParcel);
                }
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова");
        }

    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        // добавил загрузку в транспорт
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
        }
        standartParcelBox.deliverBox();
        fragileParcelBox.deliverBox();
        perishableParcelBox.deliverBox();
        for (Parcel parcel : allParcels) {
            parcel.deliver();
        }
        //Посылки доставлены. Очищаем все коробки и списки
        allParcels.clear();
        standartParcelBox.clear();
        fragileParcelBox.clear();
        perishableParcelBox.clear();

    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        double sum = 0;
        for (Parcel parcel : allParcels) {
            sum = sum + parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + sum);
    }

    private static void printDeliveryStatus() {
        System.out.println("Введите новое местоположение:");
        String newLocation = scanner.nextLine();
        for (Trackable parcel : allParcelsTrackable) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void printParcelInParcelBox() {
        System.out.println("Введите коробку с каким типом посылок хотите показать:");
        int parcelType = Integer.parseInt(scanner.nextLine());
        switch (parcelType) {
            case 1:
                standartParcelBox.getAllParcels();
                break;
            case 2:
                fragileParcelBox.getAllParcels();
                break;
            case 3:
                perishableParcelBox.getAllParcels();
                break;
            default:
                System.out.println("Указан несуществующий тип посылок");
        }
    }

}

