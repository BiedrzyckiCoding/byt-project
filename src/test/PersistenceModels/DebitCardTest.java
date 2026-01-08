package test.PersistenceModels;

import main.PersistenceModels.DebitCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DebitCardTest {

    private String validCardNumber;
    private LocalDate validExpirationDate;
    private String validSecurityCode;

    @BeforeEach
    void setUp() {
//        DebitCard.setExtent(Collections.emptyList());
        validCardNumber = "1234567890123456";
        validExpirationDate = LocalDate.now().plusYears(2);
        validSecurityCode = "123";
    }

    @AfterEach
    void tearDown() {
//        DebitCard.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldCreateObject_whenDataIsValid() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);

        assertNotNull(card);
        assertEquals(validCardNumber, card.getCardNumber());
        assertEquals(validExpirationDate, card.getExpirationDate());
        assertEquals(validSecurityCode, card.getSecurityCode());
    }

    @Test
    void constructor_shouldAddToExtent_whenDataIsValid() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);

        assertTrue(DebitCard.getExtent().contains(card));
        assertEquals(1, DebitCard.getExtent().size());
    }

    @Test
    void constructor_shouldThrowException_whenCardNumberIsEmpty() {
        assertThrows(Exception.class, () ->
                new DebitCard("", validExpirationDate, validSecurityCode)
        );
    }

    @Test
    void constructor_shouldThrowException_whenCardNumberIsNull() {
        assertThrows(Exception.class, () ->
                new DebitCard(null, validExpirationDate, validSecurityCode)
        );
    }

    @Test
    void constructor_shouldThrowException_whenExpirationDateIsNull() {
        assertThrows(Exception.class, () ->
                new DebitCard(validCardNumber, null, validSecurityCode)
        );
    }

    @Test
    void constructor_shouldThrowException_whenSecurityCodeIsEmpty() {
        assertThrows(Exception.class, () ->
                new DebitCard(validCardNumber, validExpirationDate, "")
        );
    }

    @Test
    void constructor_shouldThrowException_whenSecurityCodeIsNull() {
        assertThrows(Exception.class, () ->
                new DebitCard(validCardNumber, validExpirationDate, null)
        );
    }

    @Test
    void constructor_shouldNotAddToExtent_whenValidationFails() {
        try {
            new DebitCard("", validExpirationDate, validSecurityCode);
        } catch (Exception ignored) {
        }

        assertTrue(DebitCard.getExtent().isEmpty());
    }

    @Test
    void setCardNumber_shouldUpdateValue_whenValid() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);
        String newNumber = "9876543210987654";

        card.setCardNumber(newNumber);

        assertEquals(newNumber, card.getCardNumber());
    }

    @Test
    void setCardNumber_shouldThrowException_whenEmpty() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);
        assertThrows(Exception.class, () -> card.setCardNumber(""));
    }

    @Test
    void setExpirationDate_shouldUpdateValue_whenValid() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);
        LocalDate newDate = LocalDate.now().plusYears(3);

        card.setExpirationDate(newDate);

        assertEquals(newDate, card.getExpirationDate());
    }

    @Test
    void setExpirationDate_shouldThrowException_whenNull() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);
        assertThrows(Exception.class, () -> card.setExpirationDate(null));
    }

    @Test
    void setSecurityCode_shouldUpdateValue_whenValid() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);
        String newCode = "999";

        card.setSecurityCode(newCode);

        assertEquals(newCode, card.getSecurityCode());
    }

    @Test
    void setSecurityCode_shouldThrowException_whenEmpty() {
        DebitCard card = new DebitCard(validCardNumber, validExpirationDate, validSecurityCode);
        assertThrows(Exception.class, () -> card.setSecurityCode(""));
    }
}