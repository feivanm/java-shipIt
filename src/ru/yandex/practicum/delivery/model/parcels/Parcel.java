package ru.yandex.practicum.delivery.model.parcels;
import java.time.LocalDate;
import java.util.Date;

public abstract class Parcel {
    protected String description;
    protected double weight;
    protected String deliveryAddress;
    protected LocalDate sendDay;
    protected final double basePrice;

    public Parcel(String description,
                  double weight,
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
        System.out.println("Посылка <<" + this.description + ">> упакована");
    };

    public void deliver(){
        System.out.println("Посылка <<" + this.description + ">> доставлена по адресу YYY");
    };

    public double calculateDeliveryCost(){
        return basePrice*weight;
    };
    //добавьте реализацию и другие необходимые классы
}
