package test.PersistenceModels;

import main.MembershipTiers.MembershipTier;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import main.PersistenceModels.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MembershipCardTest {

    private Person validCustomerPerson;
    private Person validNonCustomerPerson;
    private MembershipTier validTier;
    private LocalDate validStart;
    private LocalDate validEnd;

    @BeforeEach
    void setUp() {
//        MembershipCard.setExtent(Collections.emptyList());

        validStart = LocalDate.now();
        validEnd = LocalDate.now().plusYears(1);
        validTier = new MembershipTier(MembershipTier.TierType.BASIC);

        DebitCard debitCard = new DebitCard("1234567890123456", LocalDate.now().plusYears(2), "123");
        Customer.CustomerData customerData = new Customer.CustomerData("john_acc", LocalDate.now(), 0.0, debitCard);
        validCustomerPerson = new Person("John", List.of("Address"), "Doe", "john@test.com", LocalDate.of(1990, 1, 1), customerData);

        main.PersistenceModels.Employee.EmployeeData employeeData = new main.PersistenceModels.Employee.EmployeeData(2000, 0);
        validNonCustomerPerson = new Person("Jane", List.of("Address"), "Doe", "jane@test.com", LocalDate.of(1990, 1, 1), employeeData);
    }

    @AfterEach
    void tearDown() {
//        MembershipCard.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldCreateCard_whenAllDataValid() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertNotNull(card);
    }

    @Test
    void constructor_shouldThrowException_whenDateStartNull() {
        assertThrows(Exception.class, () ->
                new MembershipCard(null, validEnd, validCustomerPerson, validTier)
        );
    }

    @Test
    void constructor_shouldThrowException_whenDateEndNull() {
        assertThrows(Exception.class, () ->
                new MembershipCard(validStart, null, validCustomerPerson, validTier)
        );
    }

    @Test
    void constructor_shouldThrowException_whenDateStartIsFuture() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThrows(Exception.class, () ->
                new MembershipCard(futureDate, validEnd, validCustomerPerson, validTier)
        );
    }

    @Test
    void constructor_shouldThrowException_whenDateEndBeforeDateStart() {
        LocalDate earlyEnd = validStart.minusDays(1);
        assertThrows(Exception.class, () ->
                new MembershipCard(validStart, earlyEnd, validCustomerPerson, validTier)
        );
    }

    @Test
    void constructor_shouldThrowException_whenPersonIsNotCustomer() {
        assertThrows(IllegalArgumentException.class, () ->
                new MembershipCard(validStart, validEnd, validNonCustomerPerson, validTier)
        );
    }

    @Test
    void constructor_shouldAddToExtent_whenValid() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertTrue(MembershipCard.getExtent().contains(card));
    }

    @Test
    void constructor_shouldAddCardToCustomer_whenValid() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertTrue(validCustomerPerson.asCustomer().getMembershipTiers().contains(card));
    }

    @Test
    void constructor_shouldAddCardToTier_whenValid() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertTrue(validTier.getMembershipCards().contains(card));
    }

    @Test
    void setDates_shouldUpdateValues_whenValid() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        LocalDate newStart = LocalDate.now().minusDays(5);
        LocalDate newEnd = LocalDate.now().plusDays(100);

        card.setDates(newStart, newEnd);

        assertEquals(newStart, card.getDateStart());
    }

    @Test
    void setDates_shouldThrowException_whenStartNull() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertThrows(Exception.class, () -> card.setDates(null, validEnd));
    }

    @Test
    void setDates_shouldThrowException_whenStartIsFuture() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertThrows(Exception.class, () -> card.setDates(LocalDate.now().plusDays(1), validEnd));
    }

    @Test
    void setDates_shouldThrowException_whenEndBeforeStart() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertThrows(Exception.class, () -> card.setDates(validStart, validStart.minusDays(1)));
    }

    @Test
    void setMembershipTier_shouldUpdateTier() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        MembershipTier newTier = new MembershipTier(MembershipTier.TierType.PREMIUM);

        card.setMembershipTier(newTier);

        assertEquals(newTier, card.getMembershipTier());
    }

    @Test
    void getPerson_shouldReturnCorrectPerson() {
        MembershipCard card = new MembershipCard(validStart, validEnd, validCustomerPerson, validTier);
        assertEquals(validCustomerPerson, card.getPerson());
    }
}