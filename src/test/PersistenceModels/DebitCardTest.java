package test.PersistenceModels;

import main.PersistenceModels.DebitCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DebitCardTest {

    @Test
    void constructor_ShouldAddCardToExtent() {
        new DebitCard("1234", LocalDate.now(), "999");
        assertEquals(1, DebitCard.getExtent().size());
    }

    @Test
    void constructor_ShouldSetCardNumber() {
        DebitCard dc = new DebitCard("1234", LocalDate.now(), "999");
        assertEquals("1234", dc.getCardNumber());
    }

    @Test
    void constructor_ShouldSetExpirationDate() {
        LocalDate date = LocalDate.now();
        DebitCard dc = new DebitCard("1234", date, "999");
        assertEquals(date, dc.getExpirationDate());
    }

    @Test
    void constructor_ShouldSetSecurityCode() {
        DebitCard dc = new DebitCard("1234", LocalDate.now(), "999");
        assertEquals("999", dc.getSecurityCode());
    }

    @Test
    void constructor_ShouldRejectEmptyCardNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> new DebitCard("", LocalDate.now(), "999"));
    }

    @Test
    void constructor_ShouldRejectNullExpirationDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new DebitCard("1234", null, "999"));
    }

    @Test
    void constructor_ShouldRejectEmptySecurityCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new DebitCard("1234", LocalDate.now(), ""));
    }

    @Test
    void setCardNumber_ShouldUpdateValue() {
        DebitCard dc = new DebitCard("1111", LocalDate.now(), "999");
        dc.setCardNumber("2222");
        assertEquals("2222", dc.getCardNumber());
    }

    @Test
    void setCardNumber_ShouldRejectEmpty() {
        DebitCard dc = new DebitCard("1111", LocalDate.now(), "999");
        assertThrows(IllegalArgumentException.class, () -> dc.setCardNumber(""));
    }

    @Test
    void setExpirationDate_ShouldUpdateValue() {
        DebitCard dc = new DebitCard("1111", LocalDate.now(), "999");
        LocalDate newDate = LocalDate.now().plusDays(10);
        dc.setExpirationDate(newDate);
        assertEquals(newDate, dc.getExpirationDate());
    }

    @Test
    void setExpirationDate_ShouldRejectNull() {
        DebitCard dc = new DebitCard("1111", LocalDate.now(), "999");
        assertThrows(IllegalArgumentException.class, () -> dc.setExpirationDate(null));
    }

    @Test
    void setSecurityCode_ShouldUpdateValue() {
        DebitCard dc = new DebitCard("1111", LocalDate.now(), "999");
        dc.setSecurityCode("555");
        assertEquals("555", dc.getSecurityCode());
    }

    @Test
    void setSecurityCode_ShouldRejectEmpty() {
        DebitCard dc = new DebitCard("1111", LocalDate.now(), "999");

        assertThrows(IllegalArgumentException.class, () -> dc.setSecurityCode(""));
    }

    @Test
    void extent_ShouldReturnCopy() {
        new DebitCard("111", LocalDate.now(), "999");
        assertNotSame(DebitCard.getExtent(), DebitCard.getExtent());
    }
}
