package MembershipTiers;
public class Basic extends MembershipTier {
    private static final int DISCOUNT = 5;
    private static final double PRICE = 25;

    public Basic() {
        super(DISCOUNT, PRICE);
    }
}