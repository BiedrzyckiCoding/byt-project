package Person;

import MembershipTiers.MembershipCard;
import MembershipTiers.MembershipTier;

import java.time.LocalDate;
import java.util.List;

public class Customer extends Person {
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
        this.accountName = accountName;
        this.accountCreatedDate = accountCreatedDate;
        this.totalSpent = totalSpent;
        this.debitCard = debitCard;
        this.membershipCard = membershipCard;
        this.membershipTier = membershipTier;
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

    public void updateAccountDetails() {
        /* TODO */ }

    public void changeMembershipTier() {
        /* TODO */ }

    public void purchaseMembership() {
        /* TODO */ }
}
