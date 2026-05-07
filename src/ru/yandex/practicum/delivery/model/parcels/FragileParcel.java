package ru.yandex.practicum.delivery.model.parcels;

import java.time.LocalDate;
import java.util.Date;

public class FragileParcel extends Parcel{

    public FragileParcel(String description, int weight, String deliveryAddress, LocalDate sendDay) {
        super(description, weight, deliveryAddress, sendDay, (double) 4);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + this.description + ">> обёрнута в защитную плёнку");
        super.packageItem();
    }

}
