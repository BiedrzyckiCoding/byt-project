package Person;

import MembershipTiers.MembershipCard;
import MembershipTiers.MembershipTier;
import Utils.ValidationUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Customer> extent = new ArrayList<>();

    private String accountName;
    private LocalDate accountCreatedDate;
    private double totalSpent;
    private DebitCard debitCard;
    private MembershipCard membershipCard;
    private MembershipTier membershipTier;

    public Customer(String name, List<String> address, String surname, String email, LocalDate birthDate,
                    String accountName, LocalDate accountCreatedDate, double totalSpent, DebitCard debitCard,
                    MembershipCard membershipCard, MembershipTier membershipTier) {
        super(name, address, surname, email, birthDate);

        ValidationUtil.notNull(accountName, "accountName");
        ValidationUtil.notFuture(accountCreatedDate, "accountCreatedDate");
        ValidationUtil.nonNegative(totalSpent, "totalSpent");
        ValidationUtil.notNull(debitCard, "debitCard");
        ValidationUtil.notNull(membershipCard, "membershipCard");
        ValidationUtil.notNull(membershipTier, "membershipTier");

        this.accountName = accountName;
        this.accountCreatedDate = accountCreatedDate;
        this.totalSpent = totalSpent;
        this.debitCard = debitCard;
        this.membershipCard = membershipCard;
        this.membershipTier = membershipTier;

        addToExtent(this);
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public LocalDate getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(LocalDate accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    public MembershipTier getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(MembershipTier membershipTier) {
        this.membershipTier = membershipTier;
    }

    private static void addToExtent(Customer c) {
        if (c == null) throw new IllegalArgumentException("Customer cannot be null");
        extent.add(c);
    }

    public static List<Customer> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Customer> loaded) {
        extent = new ArrayList<>(loaded);
    }

    public void updateAccountDetails() {
        /* TODO */ }

    public void changeMembershipTier() {
        /* TODO */ }

    public void purchaseMembership() {
        /* TODO */ }
}
