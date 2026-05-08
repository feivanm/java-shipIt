package ru.yandex.practicum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.model.boxes.ParcelBox;
import ru.yandex.practicum.delivery.model.parcels.FragileParcel;
import ru.yandex.practicum.delivery.model.parcels.ParcelType;
import ru.yandex.practicum.delivery.model.parcels.PerishableParcel;
import ru.yandex.practicum.delivery.model.parcels.StandardParcel;

import java.time.LocalDate;

public class ParselBoxTest {
    private static StandardParcel standartParcel;
    private static FragileParcel fragileParcel;
    private static PerishableParcel perishableParcel;
    private static ParcelBox<StandardParcel> parselBox;
    LocalDate dt = LocalDate.now().plusDays(-10);

    @Test
    public void checkAddToBoxMoreThenMaxWeight(){
        parselBox = new ParcelBox<>(ParcelType.STANDARTPARCEL,10);
        Assertions.assertFalse(parselBox.addParcel(new StandardParcel("Test",20,"addr",dt)));
    }
    @Test
    public void checkAddToBoxLessThenMaxWeight(){
        parselBox = new ParcelBox<>(ParcelType.STANDARTPARCEL,10);
        Assertions.assertTrue(parselBox.addParcel(new StandardParcel("Test",5,"addr",dt)));
    }

}
