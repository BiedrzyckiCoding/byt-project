package test.PersistenceModels;

import main.PersistenceModels.MembershipCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MembershipCardTest {

    @Test
    void constructor_WithStartAndEnd_ShouldSetDates() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(10);
        MembershipCard mc = new MembershipCard(start, end);
        assertEquals(start, mc.getDateStart());
    }

    @Test
    void constructor_WithOnlyStart_ShouldSetEndNull() {
        MembershipCard mc = new MembershipCard(LocalDate.now().minusDays(1));
        assertNull(mc.getDateEnd());
    }

    @Test
    void constructor_ShouldRejectNullStart() {
        assertThrows(IllegalArgumentException.class, () -> new MembershipCard(null));
    }

    @Test
    void constructor_WithStartAndEnd_ShouldRejectNullEnd() {
        LocalDate start = LocalDate.now().minusDays(1);

        assertThrows(IllegalArgumentException.class, () -> new MembershipCard(start, null));
    }

    @Test
    void setDates_ShouldUpdateValues() {
        MembershipCard mc = new MembershipCard(LocalDate.now().minusDays(5), LocalDate.now());

        LocalDate newStart = LocalDate.now().minusDays(10);

        LocalDate newEnd = LocalDate.now().plusDays(5);

        mc.setDates(newStart, newEnd);

        assertEquals(newStart, mc.getDateStart());
    }

    @Test
    void setDates_ShouldRejectNullStart() {
        MembershipCard mc = new MembershipCard(LocalDate.now().minusDays(1));

        assertThrows(IllegalArgumentException.class, () -> mc.setDates(null, LocalDate.now()));
    }

    @Test
    void setDates_ShouldRejectNegativeEnd() {
        MembershipCard mc = new MembershipCard(LocalDate.now().minusDays(1), LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> mc.setDates(LocalDate.now().minusDays(1), LocalDate.now().minusDays(10)));
    }

    @Test
    void extent_ShouldReturnCopy() {
        new MembershipCard(LocalDate.now().minusDays(1), LocalDate.now());

        assertNotSame(MembershipCard.getExtent(), MembershipCard.getExtent());
    }
}
