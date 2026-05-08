package ru.yandex.practicum.delivery.model.parcels;

import ru.yandex.practicum.delivery.interfaces.Trackable;

import java.time.LocalDate;
import java.util.Date;

public class FragileParcel extends Parcel implements Trackable {

    public FragileParcel(String description, int weight, String deliveryAddress, LocalDate sendDay) {
        super(description, weight, deliveryAddress, sendDay, (double) 4);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + this.description + ">> обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <<" + this.description + ">> изменила местоположение на " + newLocation);
    }
}
