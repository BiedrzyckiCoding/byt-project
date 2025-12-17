package main.PersistenceModels;

import main.MembershipTiers.MembershipTier;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Person owner;

    private String accountName;
    private LocalDate accountCreatedDate;
    private double totalSpent;
    private DebitCard debitCard;

    private HashMap<LocalDateTime, Order> ordersByTimestamp = new HashMap<>();
    private ArrayList<MembershipCard> membershipCards = new ArrayList<>();

    public static final class CustomerData implements Serializable {
        public final String accountName;
        public final LocalDate accountCreatedDate;
        public final double totalSpent;
        public final DebitCard debitCard;

        public CustomerData(String accountName, LocalDate accountCreatedDate, double totalSpent, DebitCard debitCard) {
            this.accountName = accountName;
            this.accountCreatedDate = accountCreatedDate;
            this.totalSpent = totalSpent;
            this.debitCard = debitCard;
        }
    }

    Customer(Person owner, CustomerData data) {
        ValidationUtil.notNull(owner, "owner");
        ValidationUtil.notNull(data, "data");

        ValidationUtil.notEmptyString(data.accountName, "accountName");
        ValidationUtil.notFuture(data.accountCreatedDate, "accountCreatedDate");
        ValidationUtil.nonNegative(data.totalSpent, "totalSpent");
        ValidationUtil.notNull(data.debitCard, "debitCard");

        this.owner = owner;
        this.accountName = data.accountName;
        this.accountCreatedDate = data.accountCreatedDate;
        this.totalSpent = data.totalSpent;
        this.debitCard = data.debitCard;
    }

    public Person getOwner() { return owner; }

    void onDetach() {
        for (Order o : new ArrayList<>(ordersByTimestamp.values())) {
            o.removeCustomer();
        }
        ordersByTimestamp.clear();
        membershipCards.clear();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        ValidationUtil.notEmptyString(accountName, "accountName");
        this.accountName = accountName;
    }

    public LocalDate getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(LocalDate accountCreatedDate) {
        ValidationUtil.notFuture(accountCreatedDate, "accountCreatedDate");
        this.accountCreatedDate = accountCreatedDate;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        ValidationUtil.nonNegative(totalSpent, "totalSpent");
        this.totalSpent = totalSpent;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        ValidationUtil.notNull(debitCard, "debitCard");
        this.debitCard = debitCard;
    }

    public MembershipCard getMembershipCard() {
        return membershipCards.getLast();
    }

    public MembershipTier getMembershipTier() {
        return membershipCards.getLast().getMembershipTier();
    }

    public void addMembershipTierToCustomer(MembershipCard membershipCard) {
        if (membershipCards.contains(membershipCard)) {
            throw new IllegalArgumentException("Membership Card already exists for this customer");
        }
            membershipCards.add(membershipCard);
    }

    public ArrayList<MembershipCard> getMembershipTiers() {
        return new ArrayList<>(membershipCards);
    }

    public void changeMembershipTier(MembershipTier newMembershipTier) {
        if (isSameMembershipTier(newMembershipTier)) {
            throw new IllegalArgumentException("Cannot change to same tier !");
        }
        this.membershipCards.getLast().setMembershipTier(newMembershipTier);
    }

    public void purchaseMembership(MembershipTier membershipTier) {
        MembershipCard membershipCard = new MembershipCard(LocalDate.now(), owner, membershipTier);
    }

    public void purchaseMembership(LocalDate dateEnd, MembershipTier membershipTier) {
        MembershipCard membershipCard = new MembershipCard(LocalDate.now(), dateEnd, owner, membershipTier);
    }

    public void addOrder(Order order) {
        LocalDateTime key = order.getTimestamp();
        if (ordersByTimestamp.containsKey(key)) {
            throw new IllegalArgumentException("Order with same timestamp already exists for this customer");
        }

        ordersByTimestamp.put(key, order);
        order.addCustomer(this);
    }

    public void removeOrder(Order order) {
        ordersByTimestamp.remove(order.getTimestamp());
        order.removeCustomer();
    }

    public Order getOrderByTimestamp(LocalDateTime t) {
        return ordersByTimestamp.get(t);
    }

    private boolean isSameMembershipTier(MembershipTier membershipTier) {
        if (membershipCards.isEmpty()) return false;
        return membershipTier == this.membershipCards.getLast().getMembershipTier();
    }

    public void updateAccountDetails() {
        /* TODO */
    }
}
