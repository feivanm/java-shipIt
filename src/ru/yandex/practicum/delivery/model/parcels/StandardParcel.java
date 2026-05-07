package ru.yandex.practicum.delivery.model.parcels;

import java.time.LocalDate;

public class StandardParcel extends Parcel {
    public StandardParcel(String description, double weight, String deliveryAddress, LocalDate sendDay) {
        super(description, weight, deliveryAddress, sendDay, (double) 2);
    }

}
