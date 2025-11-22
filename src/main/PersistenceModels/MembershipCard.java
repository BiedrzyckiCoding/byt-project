package main.PersistenceModels;

import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MembershipCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<MembershipCard> extent = new ArrayList<>();

    private LocalDate dateStart;
    private LocalDate dateEnd;

    public MembershipCard(LocalDate dateStart, LocalDate dateEnd) {
        ValidationUtil.notFuture(dateStart, "dateStart");
        ValidationUtil.dateOrder(dateStart, dateEnd);

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;

        addToExtent(this);
    }

    public LocalDate getDateStart() { return dateStart; }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    private static void addToExtent(MembershipCard mc) {
        if (mc == null) throw new IllegalArgumentException("MembershipCard cannot be null");
        extent.add(mc);
    }

    public static List<MembershipCard> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<MembershipCard> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
