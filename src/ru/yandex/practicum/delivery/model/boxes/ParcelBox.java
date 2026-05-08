package ru.yandex.practicum.delivery.model.boxes;

import ru.yandex.practicum.delivery.model.parcels.Parcel;
import ru.yandex.practicum.delivery.model.parcels.ParcelType;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    protected int maxWeight;
    protected ParcelType parcelType;
    protected ArrayList<T> parcelsInBox;
    protected TransportType transportType;

    public ParcelBox(ParcelType parcelType, int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcelType = parcelType;
        this.parcelsInBox = new ArrayList<T>();
        if (parcelType == ParcelType.FRAGILEPARCEL){
            this.transportType = TransportType.AUTO;
        }else {
            this.transportType = TransportType.TRAIN;
        }
    }

    public boolean addParcel(T parcel) {
        //Если новая посылка влезает по весу, то добавляем, иначе предупреждение
        boolean isAdded = false;
        if (getCurrentWeight() + parcel.getWeight() < maxWeight) {
            parcelsInBox.add(parcel);
            isAdded = true;
        } else {
            System.out.println("Не получилось добавить новую посылку в коробку, достигнут максимальный вес коробки");
        }
        return isAdded;
    }

    // подсчет текущего веса коробки
    public int getCurrentWeight() {
        int currentWeight = 0;
        for (T parcel : parcelsInBox) {
            currentWeight = currentWeight + parcel.getWeight();
        }
        return currentWeight;
    }

    public void getAllParcels() {
        System.out.println("Общий вес коробки: " + getCurrentWeight());
        System.out.println("В коробке находятся посылки c типом " + parcelType + " : ");
        int i = 1;
        for (T parcel : parcelsInBox) {
            System.out.println("");
            System.out.println("Посылка " + i);
            System.out.println("Описание посылки: " + parcel.getDescription());
            System.out.println("Адрес доставки: " + parcel.getDeliveryAddress());
            System.out.println("Дата отправки: " + parcel.getSendDay());
            System.out.println("Вес посылки: " + parcel.getWeight());
        }
    }
    public void deliverBox(){
        if (transportType == TransportType.AUTO){
            System.out.println("Коробка с типом посылок "+parcelType+" аккуратно погружена в автомобиль");
        }else {
            System.out.println("Коробка с типом посылок "+parcelType+" загружена в поезд");
        }
    }
    public void clear(){
        parcelsInBox.clear();
    }
}
