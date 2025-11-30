package test.PersistenceModels;

import main.MembershipTiers.Basic;
import main.MembershipTiers.Premium;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

//    @BeforeEach
//    void resetExtents() {
//        Customer.setExtent(new java.util.ArrayList<>());
//    }

    //for constructor params
    private List<String> address() {
        return List.of("Street 1", "City", "00000");
    }

    private LocalDate birthDate() {
        return LocalDate.of(1990, 1, 1);
    }

    private DebitCard sampleDebitCard() {
        return new DebitCard("1234", LocalDate.now().plusYears(2), "999");
    }

    private MembershipCard sampleMembershipCard() {
        return new MembershipCard(LocalDate.now().minusDays(1), LocalDate.now().plusDays(10));
    }

    //how do i kurwa set new extent?
    @Test
    void fullConstructor_ShouldAddCustomerToExtent() {
        new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "johnAccount", LocalDate.now(), 100, sampleDebitCard(),
                sampleMembershipCard(), new Premium());

        assertEquals(1, Customer.getExtent().size());
    }

    @Test
    void fullConstructor_ShouldSetAccountName() {
        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "johnAccount", LocalDate.now(), 100, sampleDebitCard(),
                sampleMembershipCard(), new Premium());

        assertEquals("johnAccount", c.getAccountName());
    }

    @Test
    void fullConstructor_ShouldSetMembershipTier() {
        Premium tier = new Premium();

        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "johnAcc", LocalDate.now(), 0, sampleDebitCard(),
                sampleMembershipCard(), tier);

        assertEquals(tier, c.getMembershipTier());
    }

    @Test
    void fullConstructor_ShouldSetMembershipCard() {
        MembershipCard card = sampleMembershipCard();

        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "johnAcc", LocalDate.now(), 0, sampleDebitCard(),
                card, new Basic());

        assertEquals(card, c.getMembershipCard());
    }

    @Test
    void fullConstructor_ShouldRejectNullDebitCard() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                        "acc", LocalDate.now(), 10,
                        null,
                        sampleMembershipCard(),
                        new Basic())
        );
    }

    @Test
    void fullConstructor_ShouldRejectNullMembershipCard() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                        "acc", LocalDate.now(), 10,
                        sampleDebitCard(),
                        null,
                        new Basic())
        );
    }

    @Test
    void fullConstructor_ShouldRejectNullTier() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                        "acc", LocalDate.now(), 10,
                        sampleDebitCard(),
                        sampleMembershipCard(),
                        null)
        );
    }

    //lightConstructor is the one with no membership card and memvbership tier
    //again how do i set new clear extent xd?
    @Test
    void lightConstructor_ShouldAddCustomerToExtent() {
        new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "acc", LocalDate.now(), 50, sampleDebitCard());

        assertEquals(1, Customer.getExtent().size());
    }

    @Test
    void lightConstructor_ShouldSetDebitCard() {
        DebitCard card = sampleDebitCard();

        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "acc", LocalDate.now(), 50, card);

        assertEquals(card, c.getDebitCard());
    }

    @Test
    void lightConstructor_ShouldLeaveMembershipCardNull() {
        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "acc", LocalDate.now(), 50, sampleDebitCard());

        assertNull(c.getMembershipCard());
    }

    @Test
    void lightConstructor_ShouldLeaveMembershipTierNull() {
        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "acc", LocalDate.now(), 50, sampleDebitCard());

        assertNull(c.getMembershipTier());
    }

    @Test
    void lightConstructor_ShouldRejectNullDebitCard() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                        "acc", LocalDate.now(), 50,
                        null)
        );
    }
}
