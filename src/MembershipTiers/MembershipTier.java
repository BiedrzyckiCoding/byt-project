package MembershipTiers;

import Validation.ValidationUtil;

public abstract class MembershipTier {
    private final double discount;
    private final double price;

    public MembershipTier(double discount, double price) {

        ValidationUtil.nonNegative(discount, "discount");
        ValidationUtil.nonNegative(price, "price");

        this.discount = discount;
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }
}