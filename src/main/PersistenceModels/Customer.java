package main.PersistenceModels;

import main.MembershipTiers.MembershipTier;
import main.Person.Person;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Customer extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Customer> extent = new ArrayList<>();

    private String accountName;
    private LocalDate accountCreatedDate;
    private double totalSpent;
    private DebitCard debitCard;

    private HashMap<LocalDateTime, Order> ordersByTimestamp = new HashMap<>();
    private ArrayList<MembershipCard> membershipCards = new ArrayList<>();

    public Customer(String name, List<String> address, String surname, String email, LocalDate birthDate,
                    String accountName, LocalDate accountCreatedDate, double totalSpent, DebitCard debitCard) {
        super(name, address, surname, email, birthDate);

        ValidationUtil.notEmptyString(accountName, "accountName");
        ValidationUtil.notFuture(accountCreatedDate, "accountCreatedDate");
        ValidationUtil.nonNegative(totalSpent, "totalSpent");
        ValidationUtil.notNull(debitCard, "debitCard");

        this.accountName = accountName;
        this.accountCreatedDate = accountCreatedDate;
        this.totalSpent = totalSpent;
        this.debitCard = debitCard;

        addToExtent(this);
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
        MembershipCard membershipCard = new MembershipCard(LocalDate.now(), this, membershipTier);
    }

    public void purchaseMembership(LocalDate dateEnd, MembershipTier membershipTier) {
        MembershipCard membershipCard = new MembershipCard(LocalDate.now(), dateEnd, this, membershipTier);
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
        return membershipTier == this.membershipCards.getLast().getMembershipTier();
    }

    private static void addToExtent(Customer c) {
        if (c == null) throw new IllegalArgumentException("Customer cannot be null");
        extent.add(c);
    }

    public static List<Customer> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<Customer> loaded) {
        extent = new ArrayList<>(loaded);
    }

    public void updateAccountDetails() {
        /* TODO */
    }
}
