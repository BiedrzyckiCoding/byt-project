package test.MembershipTiers;

import main.MembershipTiers.MembershipTier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestMembershipTier extends MembershipTier {
    public TestMembershipTier(double discount, double price) {
        super(discount, price);
    }
}

public class MembershipTierTest {

    @Test
    void testValidCreation() {
        MembershipTier tier = new TestMembershipTier(10.0, 100.0);

        Assertions.assertEquals(10.0, tier.getDiscount());
        Assertions.assertEquals(100.0, tier.getPrice());
    }

    @Test
    void testNegativeDiscount() {
        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new TestMembershipTier(-5.0, 100.0)
        );
        Assertions.assertTrue(ex.getMessage().contains("discount"));
    }

    @Test
    void testNegativePrice() {
        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new TestMembershipTier(10.0, -100.0)
        );
        Assertions.assertTrue(ex.getMessage().contains("price"));
    }
}
