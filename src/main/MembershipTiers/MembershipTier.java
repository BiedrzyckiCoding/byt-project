package main.MembershipTiers;

import main.PersistenceModels.MembershipCard;
import main.Utils.ValidationUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MembershipTier implements Serializable {

    public enum TierType { BASIC, PREMIUM }
    private TierType activeType;
    private TierComponent active;
    private ArrayList<MembershipCard> membershipCards = new ArrayList<>();

    public MembershipTier(TierType initialType) {
        changeTier(initialType);
    }

    public void changeTier(TierType newType) {
        ValidationUtil.notNull(newType, "tierType");

        this.activeType = newType;
        this.active = switch (newType) {
            case BASIC -> new BasicTier();
            case PREMIUM -> new PremiumTier();
        };
    }

    public void addMembershipCard(MembershipCard membershipCard) {
        ValidationUtil.notNull(membershipCard, "membershipCard");
        if (!membershipCards.contains(membershipCard)) {
            membershipCards.add(membershipCard);
        }
    }

    public List<MembershipCard> getMembershipCards() {
        return new ArrayList<>(membershipCards);
    }


    public double getDiscount() {
        return active.getDiscount();
    }

    public double getPrice() {
        return active.getPrice();
    }

    public TierType getActiveType() {
        return activeType;
    }

    private interface TierComponent {
        double getDiscount();
        double getPrice();
    }

    private final class BasicTier implements TierComponent {
        private static final int DISCOUNT = 5;
        private static final double PRICE = 25;

        @Override public double getDiscount() { return DISCOUNT; }
        @Override public double getPrice() { return PRICE; }
    }

    private final class PremiumTier implements TierComponent {
        private static final int DISCOUNT = 20;
        private static final double PRICE = 70;

        @Override public double getDiscount() { return DISCOUNT; }
        @Override public double getPrice() { return PRICE; }
    }
}