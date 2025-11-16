package MembershipTiers;
import java.time.LocalDate;


public class MembershipCard {
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public MembershipCard(LocalDate dateStart, LocalDate dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
