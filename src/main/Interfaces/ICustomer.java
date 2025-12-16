package main.Interfaces;

import main.MembershipTiers.MembershipTier;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import main.PersistenceModels.Order;
import main.Utils.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ICustomer {

    public String getAccountName();
    public void setAccountName(String accountName);
    public LocalDate getAccountCreatedDate();
    public void setAccountCreatedDate(LocalDate accountCreatedDate);
    public double getTotalSpent();
    public void setTotalSpent(double totalSpent);
    public DebitCard getDebitCard();
    public void setDebitCard(DebitCard debitCard);
    public MembershipCard getMembershipCard();
    public MembershipTier getMembershipTier();
    public void addMembershipTierToCustomer(MembershipCard membershipCard);
    public ArrayList<MembershipCard> getMembershipTiers();
    public void changeMembershipTier(MembershipTier newMembershipTier);
    public void purchaseMembership(MembershipTier membershipTier);
    public void purchaseMembership(LocalDate dateEnd, MembershipTier membershipTier);
    public void addOrder(Order order);
    public void removeOrder(Order order);
    public Order getOrderByTimestamp(LocalDateTime t);
    public void updateAccountDetails();
}
