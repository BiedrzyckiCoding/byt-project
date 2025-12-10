package test.PersistenceModels;

import main.MembershipTiers.Basic;
import main.MembershipTiers.MembershipTier;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import main.PersistenceModels.PersistenceUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MembershipCardTest {

    @BeforeEach
    void setUp() {
        PersistenceUtil.loadAll();
    }

    private DebitCard createDebitCard() {
        return new DebitCard("1234-5678-9012-3456", LocalDate.now().plusYears(2), "123");
    }

    private Customer createCustomer() {
        return new Customer(
                "John",
                List.of("Street", "City", "00000"),
                "Doe",
                "john@example.com",
                LocalDate.of(1990, 1, 1),
                "johnAccount",
                LocalDate.now(),
                0.0,
                createDebitCard()
        );
    }

    private MembershipTier createTier() {
        return new Basic();
    }

    private MembershipCard createMembershipCard(LocalDate start, LocalDate end) {
        return new MembershipCard(start, end, createCustomer(), createTier());
    }

    // --- Constructor Tests ---

    @Test
    void constructor_ShouldSetStartDate() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);

        MembershipCard mc = createMembershipCard(start, end);

        assertEquals(start, mc.getDateStart());
    }

    @Test
    void constructor_ShouldSetEndDate() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);

        MembershipCard mc = createMembershipCard(start, end);

        assertEquals(end, mc.getDateEnd());
    }

    @Test
    void constructor_ShouldSetCustomer() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);
        Customer customer = createCustomer();
        MembershipTier tier = createTier();

        MembershipCard mc = new MembershipCard(start, end, customer, tier);

        assertEquals(customer, mc.getCustomer());
    }

    @Test
    void constructor_ShouldSetMembershipTier() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);
        Customer customer = createCustomer();
        MembershipTier tier = createTier();

        MembershipCard mc = new MembershipCard(start, end, customer, tier);

        assertEquals(tier, mc.getMembershipTier());
    }

    @Test
    void constructor_ShouldRegisterCardWithCustomer() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);
        Customer customer = createCustomer();

        MembershipCard mc = new MembershipCard(start, end, customer, createTier());

        // Verifying bi-directional connection
        assertTrue(customer.getMembershipTiers().contains(mc));
    }

    @Test
    void constructor_ShouldRegisterCardWithTier() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);
        MembershipTier tier = createTier();

        MembershipCard mc = new MembershipCard(start, end, createCustomer(), tier);

        assertEquals(tier, mc.getMembershipTier());
    }

    @Test
    void constructor_ShouldAddToExtent() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);

        MembershipCard mc = createMembershipCard(start, end);

        assertTrue(MembershipCard.getExtent().contains(mc));
    }


    @Test
    void constructor_ShouldRejectNullStartDate() {
        LocalDate end = LocalDate.now().plusDays(10);
        Customer customer = createCustomer();
        MembershipTier tier = createTier();

        assertThrows(IllegalArgumentException.class, () ->
                new MembershipCard(null, end, customer, tier)
        );
    }

    @Test
    void constructor_ShouldRejectNullEndDate() {
        LocalDate start = LocalDate.now().minusDays(1);
        Customer customer = createCustomer();
        MembershipTier tier = createTier();

        assertThrows(IllegalArgumentException.class, () ->
                new MembershipCard(start, null, customer, tier)
        );
    }

    @Test
    void constructor_ShouldRejectFutureStartDate() {
        LocalDate start = LocalDate.now().plusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);
        Customer customer = createCustomer();
        MembershipTier tier = createTier();

        assertThrows(IllegalArgumentException.class, () ->
                new MembershipCard(start, end, customer, tier)
        );
    }

    @Test
    void constructor_ShouldRejectInvalidDateOrder() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().minusDays(5); // End is before Start
        Customer customer = createCustomer();
        MembershipTier tier = createTier();

        assertThrows(IllegalArgumentException.class, () ->
                new MembershipCard(start, end, customer, tier)
        );
    }


    @Test
    void setDates_ShouldUpdateStartDate() {
        MembershipCard mc = createMembershipCard(LocalDate.now().minusDays(5), LocalDate.now());
        LocalDate newStart = LocalDate.now().minusDays(2);
        LocalDate newEnd = LocalDate.now().plusDays(2);

        mc.setDates(newStart, newEnd);

        assertEquals(newStart, mc.getDateStart());
    }

    @Test
    void setDates_ShouldUpdateEndDate() {
        MembershipCard mc = createMembershipCard(LocalDate.now().minusDays(5), LocalDate.now());
        LocalDate newStart = LocalDate.now().minusDays(2);
        LocalDate newEnd = LocalDate.now().plusDays(2);

        mc.setDates(newStart, newEnd);

        assertEquals(newEnd, mc.getDateEnd());
    }

    @Test
    void setDates_ShouldRejectNullStart() {
        MembershipCard mc = createMembershipCard(LocalDate.now().minusDays(5), LocalDate.now());
        LocalDate end = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> mc.setDates(null, end));
    }

    @Test
    void setDates_ShouldRejectInvalidDateOrder() {
        MembershipCard mc = createMembershipCard(LocalDate.now().minusDays(5), LocalDate.now());
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().minusDays(2); // Invalid

        assertThrows(IllegalArgumentException.class, () -> mc.setDates(start, end));
    }


    @Test
    void extentReturnsCopy() {
        MembershipCard mc = createMembershipCard(LocalDate.now().minusDays(1), LocalDate.now());
        List<MembershipCard> copy = MembershipCard.getExtent();

        copy.clear();

        assertTrue(MembershipCard.getExtent().contains(mc));
    }
}
