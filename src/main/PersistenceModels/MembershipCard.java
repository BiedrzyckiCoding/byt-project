package main.PersistenceModels;

import main.MembershipTiers.MembershipTier;
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

    private Customer customer;


    private MembershipTier membershipTier;

    public MembershipCard(LocalDate dateStart, LocalDate dateEnd, Customer customer, MembershipTier membershipTier) {
        ValidationUtil.notNull(dateStart, "dateStart");
        ValidationUtil.notNull(dateEnd, "dateEnd");
        ValidationUtil.notFuture(dateStart, "dateStart");
        ValidationUtil.dateOrder(dateStart, dateEnd);

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;

        customer.addMembershipTierToCustomer(this);
        membershipTier.addCustomer(this);

        addToExtent(this);
    }

    public MembershipCard(LocalDate dateStart,Customer customer, MembershipTier membershipTier) {
        ValidationUtil.notNull(dateStart, "dateStart");
        ValidationUtil.notFuture(dateStart, "dateStart");

        this.dateStart = dateStart;
        this.dateEnd = null;

        customer.addMembershipTierToCustomer(this);
        membershipTier.addCustomer(this);

        addToExtent(this);
    }

    public void setDates(LocalDate dateStart, LocalDate dateEnd) {
        ValidationUtil.notNull(dateStart, "dateStart");
        ValidationUtil.notFuture(dateStart, "dateStart");
        ValidationUtil.dateOrder(dateStart, dateEnd);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public MembershipTier getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(MembershipTier membershipTier) {
        this.membershipTier = membershipTier;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public LocalDate getDateStart() { return dateStart; }

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
