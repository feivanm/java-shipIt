package ru.yandex.practicum.delivery.model.parcels;
import java.time.LocalDate;
import java.util.Date;

public class PerishableParcel extends Parcel{
    int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, LocalDate sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay, (double) 3);
        this.timeToLive = timeToLive;
    }
    public boolean isExpired(LocalDate currentDay){
        return sendDay.plusDays(timeToLive).isBefore(currentDay);
    }
}
