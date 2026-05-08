package ru.yandex.practicum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.model.parcels.FragileParcel;
import ru.yandex.practicum.delivery.model.parcels.PerishableParcel;
import ru.yandex.practicum.delivery.model.parcels.StandardParcel;

import java.time.LocalDate;

public class DeliveryCostTest {
    private static StandardParcel standartParcel;
    private static FragileParcel fragileParcel;
    private static PerishableParcel perishableParcel;
    LocalDate dt = LocalDate.now().plusDays(-10);

    @Test
    public void checkDeliveryCostStandart(){
        standartParcel= new StandardParcel("test", 10, "deliveryAddress", dt);
        Assertions.assertEquals(20,standartParcel.calculateDeliveryCost());
    }
    @Test
    public void checkDeliveryCostFragile(){
        fragileParcel = new FragileParcel("test", 10, "deliveryAddress", dt);
        Assertions.assertEquals(40,fragileParcel.calculateDeliveryCost());
    }
    @Test
    public void checkDeliveryCostPerishable(){
        perishableParcel = new PerishableParcel("test", 10, "deliveryAddress", dt, 10);
        Assertions.assertEquals(30,perishableParcel.calculateDeliveryCost());
    }
    @Test
    public void checkExpiredPerishable(){
        perishableParcel = new PerishableParcel("test", 10, "deliveryAddress", dt, 10);
        Assertions.assertFalse(perishableParcel.isExpired(dt.plusDays(20)));
    }
    @Test
    public void checkNotExpiredPerishable(){
        perishableParcel = new PerishableParcel("test", 10, "deliveryAddress", dt, 10);
        Assertions.assertTrue(perishableParcel.isExpired(dt));
    }

}
