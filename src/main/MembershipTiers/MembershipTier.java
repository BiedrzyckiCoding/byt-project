package main.MembershipTiers;

import main.PersistenceModels.MembershipCard;
import main.Utils.ValidationUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class MembershipTier implements Serializable {
    private final double discount;
    private final double price;

    private ArrayList<MembershipCard> membershipCards = new ArrayList<>();

    public MembershipTier(double discount, double price) {

        ValidationUtil.nonNegative(discount, "discount");
        ValidationUtil.nonNegative(price, "price");

        this.discount = discount;
        this.price = price;
    }

    public void addCustomer(MembershipCard membershipCard){
        membershipCards.add(membershipCard);
    }

    public double getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }
}