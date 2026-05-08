package ru.yandex.practicum.delivery.model.parcels;

public enum ParcelType {
    STANDARTPARCEL("Стандартная посылка"), FRAGILEPARCEL("Хрупкая посылка"), PERISHABLEPARCEL("Скоропортящаяся посылка");
    private final String name;

    ParcelType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
