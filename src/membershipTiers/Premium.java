package membershipTiers;
public class Premium extends MembershipTier {
    private static final int DISCOUNT = 20;
    private static final double PRICE = 70;

    public Premium(double discount, double price) {
        super(discount, price);
    }
}