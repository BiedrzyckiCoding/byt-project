package test.Inheritance;

import main.MembershipTiers.MembershipTier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MembershipTierTest {

    // Disjoint and Complete

    @Test
    void constructor_ShouldInitializeAsBasic_WhenTypeIsBasic() {
        var initialType = MembershipTier.TierType.BASIC;

        MembershipTier membership = new MembershipTier(initialType);

        assertEquals(MembershipTier.TierType.BASIC, membership.getActiveType());
    }

    @Test
    void constructor_ShouldInitializeAsPremium_WhenTypeIsPremium() {
        var initialType = MembershipTier.TierType.PREMIUM;

        MembershipTier membership = new MembershipTier(initialType);

        assertEquals(MembershipTier.TierType.PREMIUM, membership.getActiveType());
    }

    @Test
    void getDiscount_ShouldReturnBasicValue_WhenInitializedAsBasic() {
        MembershipTier membership = new MembershipTier(MembershipTier.TierType.BASIC);
        double expectedDiscount = 5.0;

        double actualDiscount = membership.getDiscount();

        assertEquals(expectedDiscount, actualDiscount);
    }

    @Test
    void getPrice_ShouldReturnPremiumValue_WhenInitializedAsPremium() {
        MembershipTier membership = new MembershipTier(MembershipTier.TierType.PREMIUM);
        double expectedPrice = 70.0;

        double actualPrice = membership.getPrice();

        assertEquals(expectedPrice, actualPrice);
    }

    // Dynamic (State Change)

    @Test
    void changeTier_ShouldUpdateType_WhenSwitchingFromBasicToPremium() {
        MembershipTier membership = new MembershipTier(MembershipTier.TierType.BASIC);

        membership.changeTier(MembershipTier.TierType.PREMIUM);

        assertEquals(MembershipTier.TierType.PREMIUM, membership.getActiveType());
    }

    @Test
    void changeTier_ShouldUpdatePriceBehavior_WhenSwitchingFromBasicToPremium() {
        MembershipTier membership = new MembershipTier(MembershipTier.TierType.BASIC);
        double expectedPremiumPrice = 70.0;

        membership.changeTier(MembershipTier.TierType.PREMIUM);

        assertEquals(expectedPremiumPrice, membership.getPrice());
    }

    @Test
    void changeTier_ShouldUpdateDiscountBehavior_WhenSwitchingFromPremiumToBasic() {
        MembershipTier membership = new MembershipTier(MembershipTier.TierType.PREMIUM);
        double expectedBasicDiscount = 5.0;

        membership.changeTier(MembershipTier.TierType.BASIC);

        assertEquals(expectedBasicDiscount, membership.getDiscount());
    }

    @Test
    void changeTier_ShouldThrowException_WhenNewTypeIsNull() {
        MembershipTier membership = new MembershipTier(MembershipTier.TierType.BASIC);

        assertThrows(Exception.class, () -> membership.changeTier(null));
    }
}
