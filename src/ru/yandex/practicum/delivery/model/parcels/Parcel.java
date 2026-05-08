package ru.yandex.practicum.delivery.model.parcels;
import java.time.LocalDate;
import java.util.Date;

public abstract class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected LocalDate sendDay;
    protected final double basePrice;

    public Parcel(String description,
                  int weight,
                  String deliveryAddress,
                  LocalDate sendDay,
                  double basePrice
    ) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.basePrice = basePrice;
    }


    public void packageItem(){
        System.out.println("Посылка <<" + description + ">> упакована");
    };

    public void deliver(){
        System.out.println("Посылка <<" + description + ">> доставлена по адресу "+ deliveryAddress);
    };

    public double calculateDeliveryCost(){
        return basePrice*weight;
    };

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getSendDay() {
        return sendDay;
    }

    public void setSendDay(LocalDate sendDay) {
        this.sendDay = sendDay;
    }
    //добавьте реализацию и другие необходимые классы
}
